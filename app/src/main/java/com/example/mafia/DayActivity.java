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
    private byte turnOfSpeech = 1, forSpeech = 0;
    private long minutes, seconds;
    private String time;
    private String startTime;
    private Button button;
    private Spinner PersonChoice;
    private CountDownTimer timer;

    public DayActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView text = findViewById(R.id.DiePerson);
        if (PlayerManager.getKilledPlayer().equals("никто"))
            text.setText("Никто не" + "\n" + "убит");
        else {
            String fortext = "Убит(а) ";
            fortext += PlayerManager.getKilledPlayer();
            text.setText(fortext);
        }

        whoSpeak = findViewById(R.id.WhoSpeak);
        whoSpeak.setText("Речи игрока " + turnOfSpeech);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(
                DayActivity.this,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames()
        );
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        PersonChoice = findViewById(R.id.PersonChoice);
        PersonChoice.setAdapter(PersonChoiceAdapter);

        button = (Button)findViewById(R.id.Button);

        seconds = PlayerManager.getSpeechTime()%60;
        minutes = (PlayerManager.getSpeechTime()/60)%60;
        startTime = String.format("%d:%d", minutes, seconds);
        textTime = findViewById(R.id.Timer);
        textTime.setText(startTime);

    }
    public void onClickDay(View view) {
        if (turnOfSpeech <= PlayerManager.getPlayersCount()) {
            if(forSpeech == 0){
                startTimer(textTime);
                button.setText("STOP");
                forSpeech++;
            } else if (forSpeech == 1) {
                cancelTimer();
                textTime.setText(startTime);
                button.setText("NEXT");
                forSpeech++;
                turnOfSpeech++;
            } else{
                if (PlayerManager.getNumberOfDay() > 1) {
                    String name = PersonChoice.getSelectedItem().toString();
                    PlayerManager.addWhoVoted(name);
                }
                button.setText("START");
                whoSpeak.setText("Речи игрока " + turnOfSpeech);
                forSpeech = 0;
            }
        }
        else
        {
            if (PlayerManager.getNumberOfDay() == 1){
                Intent goToNight = new Intent(DayActivity.this, NightActivity.class);
                PlayerManager.IncreaseNumberOfDay();
                startActivity(goToNight);
            }
            else{
                Intent goToVote = new Intent(DayActivity.this, VoteActivity.class);
                PlayerManager.IncreaseNumberOfDay();
                startActivity(goToVote);
            }
        }
    }

    private void startTimer(TextView text){
        timer = new CountDownTimer(PlayerManager.getSpeechTime() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                seconds = (l/1000)%60;
                minutes = (l/(1000*60))%60;
                time = String.format("%d:%d", minutes, seconds);
                text.setText(time);
            }
            @Override
            public void onFinish() {
                text.setText("конец речи");
            }
        }.start();
    }
    private void cancelTimer(){
        timer.cancel();
    }
}