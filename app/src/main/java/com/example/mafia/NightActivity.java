package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class NightActivity extends AppCompatActivity {
    private int i = 2;
    private TextView wakeUp;
    private Spinner mafiaChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        TextView kickedPlayer = findViewById(R.id.vote_info);
        if(GameManager.getNumberOfDay() == 1)
            kickedPlayer.setVisibility(View.GONE);
        else
            kickedPlayer.setText(GameManager.getKickedPlayers());

        wakeUp = findViewById(R.id.wake_up_in_night);
        TextView info = findViewById(R.id.info);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(NightActivity.this,
                android.R.layout.simple_spinner_item, GameManager.getPlayersNames());
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mafiaChoice = findViewById(R.id.MafiaChoice);
        mafiaChoice.setAdapter(PersonChoiceAdapter);

        info.setText(GameManager.getInfo());
        wakeUp.setText("просыпается мафия");
    }

    public void onClickNight(View view) {
        if(i < GameManager.roles.length){
            StringBuilder info = new StringBuilder();
            info.append("Просыпается ").append(GameManager.roles[i]);
            wakeUp.setText(info);
            if(i == 2){
                mafiaChoice.setVisibility(View.INVISIBLE);
            }
            i++;
        }
        else{
            GameManager.killPlayer(mafiaChoice.getSelectedItem().toString());
            GameManager.IncreaseNumberOfDay();
            Intent NightToDay = new Intent(NightActivity.this, DayActivity.class);
            startActivity(NightToDay);
        }
    }
}