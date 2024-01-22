package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    private Button button;
    private TextView time;
    private ProgressBar pb;
    private byte turnOfSpeech = 1;
    private byte forSpeech = 0;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView text = findViewById(R.id.DiePerson);
        if (PlayerManager.getKilledPlayer().equals("никто"))
            text.setText("Никто не убит");
        else {
            String fortext = "Убит(а) ";
            fortext += PlayerManager.getKilledPlayer();
            text.setText(fortext);
        }

        TextView whoSpeak = findViewById(R.id.WhoSpeak);
        whoSpeak.setText("Речи игрока " + turnOfSpeech);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(
                DayActivity.this,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames()
        );
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner PersonChoice = findViewById(R.id.PersonChoice);
        PersonChoice.setAdapter(PersonChoiceAdapter);

        time = findViewById(R.id.Timer);
        pb = findViewById(R.id.progressBar);


        button = findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turnOfSpeech <= PlayerManager.getPlayersCount()) {
                    if(forSpeech == 0){
                        startTimer(time, pb);
                        button.setText("STOP");
                        forSpeech++;
                    } else if (forSpeech == 1) {
                        cancelTimer();
                        button.setText("NEXT");
                        forSpeech++;
                        turnOfSpeech++;
                    } else if (forSpeech == 2) {
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
        });
    }

    private void startTimer(TextView text, ProgressBar progress){
        timer = new CountDownTimer(PlayerManager.getSpeechTime() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                text.setText("" + l/1000);
                progress.setProgress((int) l/1000);
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