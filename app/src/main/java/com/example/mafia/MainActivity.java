package com.example.mafia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input_players = findViewById(R.id.input_players);
        EditText input_time = findViewById(R.id.input_time);
        Button next = findViewById(R.id.start_button1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Set1ToSet2 = new Intent(view.getContext(), SecondSetting.class);
                startActivity(Set1ToSet2);
                PlayerManager.setPlayers(input_players.getText().toString());
                PlayerManager.setSpeechTime(Long.parseLong(input_time.getText().toString()));
            }
        });
    }
}