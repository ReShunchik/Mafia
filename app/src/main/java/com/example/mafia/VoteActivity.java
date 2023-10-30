package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class VoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Button next = findViewById(R.id.EndVote);
        Intent GoToNight = new Intent(VoteActivity.this, NightActivity.class);
        Intent GoToEnd = new Intent(VoteActivity.this, EndActivity.class);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*ArrayAdapter<String> EnterDon = new ArrayAdapter<String>(VoteActivity.this,
                        android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
                EnterDon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner SetDon = findViewById(R.id.EnterDon);
                SetDon.setAdapter(EnterDon);

                ArrayAdapter<String> EnterCom = new ArrayAdapter<String>(VoteActivity.this,
                        android.R.layout.simple_spinner_item, PlayerManager.getPlayersNames());
                EnterCom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner SetCom = findViewById(R.id.EnterCom);
                SetCom.setAdapter(EnterCom);*/

                if(PlayerManager.isContinue())
                    startActivity(GoToEnd);
                else
                    startActivity(GoToNight);
            }
        });
    }
}