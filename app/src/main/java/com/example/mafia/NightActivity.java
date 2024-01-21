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

    String fortext = "Мафии:" + "\n" + PlayerManager.getMafiasNames() + "\n" +
                     "Дон:" + "\n" + PlayerManager.getDonName() + "\n" +
                     "Комиссар:" + "\n" + PlayerManager.getComissarName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        Button next = findViewById(R.id.EndNight);
        TextView info = findViewById(R.id.info);
        Intent NightToDay = new Intent(NightActivity.this, DayActivity.class);

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(NightActivity.this,
                android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner Mafiachoice = findViewById(R.id.MafiaChoice);
        Mafiachoice.setAdapter(PersonChoiceAdapter);

        TextView information = findViewById(R.id.info);
        information.setText(PlayerManager.getInfo());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerManager.killPlayer(Mafiachoice.getSelectedItem().toString());
                startActivity(NightToDay);
            }
        });
    }
}