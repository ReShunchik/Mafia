package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        TextView endtext = findViewById(R.id.EndText);
        String fortext = "Победа" + "\n" + PlayerManager.whoWin();
        endtext.setText(fortext);
    }
}