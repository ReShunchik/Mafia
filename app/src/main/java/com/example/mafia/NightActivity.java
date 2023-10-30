package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        Button next = findViewById(R.id.EndNight);
        Intent NightToDay = new Intent(NightActivity.this, VoteActivity.class);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<String>(NightActivity.this,
                android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner Mafiachoice = findViewById(R.id.MafiaChoice);
        Mafiachoice.setAdapter(PersonChoiceAdapter);

        TextView playersRoles = findViewById(R.id.playersRole);
        playersRoles.setText(PlayerManager.getRole(4));
        /*playersRoles.setText(
                "Мафии:" + "\n" + PlayerManager.getMafiasNames() + "\n" +
                        "Дон:" + "\n" + PlayerManager.getDonName() + "\n" +
                        "Комиссар:" + "\n" + PlayerManager.getComissarName()
        );*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerManager.killPlayer(Mafiachoice.getSelectedItem().toString());
                startActivity(NightToDay);
            }
        });
    }
}