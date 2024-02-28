package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class DayActivity extends AppCompatActivity {

    private TextView textTime, whoSpeak;
    private int turnOfSpeech = 1;
    private long minutes, seconds;
    private String time;
    private Button button;
    private Spinner PersonChoice;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView text = findViewById(R.id.DiePerson);

        PersonChoice = findViewById(R.id.PersonChoice);
        if(GameManager.getNumberOfDay() == 1){
            text.setVisibility(View.INVISIBLE);
            PersonChoice.setVisibility(View.GONE);
        }
        else if (GameManager.getKilledPlayer().equals("никто"))
            text.setText("");
        else {
            String fortext = "Убит(а) " + GameManager.getKilledPlayer();
            text.setText(fortext);
        }

        whoSpeak = findViewById(R.id.WhoSpeak);
        whoSpeak.setText("Речь игрока " + turnOfSpeech);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(
                DayActivity.this,
                android.R.layout.simple_spinner_item,
                GameManager.getPlayersNames()
        );
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PersonChoice.setAdapter(PersonChoiceAdapter);

        button = findViewById(R.id.Button);

        textTime = findViewById(R.id.Timer);
        setStartTime();

    }
    public void onClickDay(View view) {
        if(button.getText().toString().equals("START")){
            button.setText("STOP");
            startTimer();
        }
        else if(button.getText().toString().equals("STOP")){
            cancelTimer();
            setStartTime();
            button.setText(R.string.next);
        }
        else{
            turnOfSpeech++;
            if(GameManager.getNumberOfDay() != 1)
                GameManager.addWhoVoted(PersonChoice.getSelectedItem().toString());
            if(turnOfSpeech <= GameManager.getPlayersCount()){
                whoSpeak.setText("Речь игрока " + turnOfSpeech);
                button.setText(R.string.start);
            }
            else{
                Intent intent;
                if(GameManager.getNumberOfDay() == 1)
                    intent = new Intent(this, NightActivity.class);
                else{
                    intent = new Intent(this, VoteActivity.class);
                }
                startActivity(intent);
            }
        }
    }

    private void startTimer(){
        timer = new CountDownTimer(GameManager.getSpeechTime() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                seconds = (l/1000)%60;
                minutes = (l/(1000*60))%60;
                time = String.format("%d:%d", minutes, seconds);
                textTime.setText(time);
            }
            @Override
            public void onFinish() {
                textTime.setText("конец речи");
            }
        }.start();
    }
    private void cancelTimer(){
        timer.cancel();
    }
    private void setStartTime(){
        seconds = GameManager.getSpeechTime()%60;
        minutes = (GameManager.getSpeechTime()/60)%60;
        String startTime = String.format("%d:%d", minutes, seconds);
        textTime.setText(startTime);
    }
}