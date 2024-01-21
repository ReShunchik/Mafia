package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;

public class DayActivity extends AppCompatActivity {

    Button NextPerson;
    byte turnOfSpeech = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        TextView text = findViewById(R.id.DiePerson);
        if(PlayerManager.getKilledPlayer().equals("никто"))
           text.setText("Никто не убит");
        else {
            String fortext = "Убит(а)";
            fortext += PlayerManager.getKilledPlayer();
            text.setText(fortext);
        }

        ArrayAdapter<String> PersonChoiceAdapter = new ArrayAdapter<>(
                DayActivity.this,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames()
        );
        PersonChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner PersonChoice = findViewById(R.id.PersonChoice);
        PersonChoice.setAdapter(PersonChoiceAdapter);

        Intent GoToVote = new Intent(DayActivity.this, VoteActivity.class);

        NextPerson = findViewById(R.id.NextPersonButton);
        NextPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turnOfSpeech == PlayerManager.getPlayersCount()){
                    PlayerManager.addWhoVoted(PersonChoice.getSelectedItem().toString());
                    startActivity(GoToVote);
                }
                turnOfSpeech++;
            }
        });
    }
}