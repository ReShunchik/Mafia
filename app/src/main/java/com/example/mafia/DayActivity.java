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

import java.util.Locale;

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
        if(PlayerManager.getNumberOfDay() == 1){
            text.setVisibility(View.INVISIBLE);
            PersonChoice.setVisibility(View.GONE);
        }
        else if (PlayerManager.getKilledPlayer().equals("никто"))
            text.setText(R.string.no_kill);
        else {
            String fortext = "Убит(а) " + PlayerManager.getKilledPlayer();
            text.setText(fortext);
        }

        whoSpeak = findViewById(R.id.WhoSpeak);
        whoSpeak.setText(R.string.player_speech + turnOfSpeech);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(
                DayActivity.this,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames()
        );
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PersonChoice.setAdapter(PersonChoiceAdapter);

        button = findViewById(R.id.Button);

        textTime = findViewById(R.id.Timer);
        setStartTime();

    }
    public void onClickDay(View view) {
        if(button.getText().toString().equals("START")){
            button.setText(R.string.stop);
            startTimer();
        }
        else if(button.getText().toString().equals("STOP")){
            cancelTimer();
            setStartTime();
            button.setText(R.string.next);
        }
        else{
            turnOfSpeech++;
            if(PlayerManager.getNumberOfDay() != 1)
                PlayerManager.addWhoVoted(PersonChoice.getSelectedItem().toString());
            if(turnOfSpeech <= PlayerManager.getPlayersCount()){
                whoSpeak.setText(R.string.player_speech + turnOfSpeech);
                button.setText(R.string.start);
            }
            else{
                Intent intent;
                if(PlayerManager.getNumberOfDay() == 1)
                    intent = new Intent(this, NightActivity.class);
                else{
                    intent = new Intent(this, VoteActivity.class);
                    intent.putExtra("isDivision", false);
                }
                PlayerManager.IncreaseNumberOfDay();
                startActivity(intent);
            }
        }
    }

    private void startTimer(){
        timer = new CountDownTimer(PlayerManager.getSpeechTime() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                seconds = (l/1000)%60;
                minutes = (l/(1000*60))%60;
                time = String.format(Locale.ENGLISH, "%d:%d", minutes, seconds);
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
        seconds = PlayerManager.getSpeechTime()%60;
        minutes = (PlayerManager.getSpeechTime()/60)%60;
        String startTime = String.format(Locale.ENGLISH,"%d:%d", minutes, seconds);
        textTime.setText(startTime);
    }
}