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

        wakeUp = findViewById(R.id.wake_up_in_night);
        TextView info = findViewById(R.id.info);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(NightActivity.this,
                android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mafiaChoice = findViewById(R.id.MafiaChoice);
        mafiaChoice.setAdapter(PersonChoiceAdapter);

        info.setText(PlayerManager.getInfo());
        wakeUp.setText("просыпается мафия");
    }

    public void onClickNight(View view) {
        if(i < PlayerManager.roles.length){
            StringBuilder info = new StringBuilder(R.string.wake_up + PlayerManager.roles[i]);
            wakeUp.setText(info);
            i += 1;
            if(i == 1){
                mafiaChoice.setVisibility(View.INVISIBLE);
            }
        }
        else{
            PlayerManager.killPlayer(mafiaChoice.getSelectedItem().toString());
            Intent NightToDay = new Intent(NightActivity.this, DayActivity.class);
            startActivity(NightToDay);
        }
    }
}