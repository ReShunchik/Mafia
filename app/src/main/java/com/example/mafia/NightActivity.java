package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NightActivity extends AppCompatActivity {
    private int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        Button next = findViewById(R.id.EndNight);
        TextView wakeUp = findViewById(R.id.wake_up_in_night);
        TextView info = findViewById(R.id.info);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(NightActivity.this,
                android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner Mafiachoice = findViewById(R.id.MafiaChoice);
        Mafiachoice.setAdapter(PersonChoiceAdapter);

        info.setText(PlayerManager.getInfo());
        wakeUp.setText("просыпается мафия");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i < PlayerManager.roles.length){
                    wakeUp.setText("Просыпается " + PlayerManager.roles[i]);
                    i += 1;
                }
                else{
                    PlayerManager.killPlayer(Mafiachoice.getSelectedItem().toString());
                    Intent NightToDay = new Intent(NightActivity.this,
                            DayActivity.class);
                    startActivity(NightToDay);
                }
            }
        });
    }
}