package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        TextView endtext = findViewById(R.id.EndText);
        StringBuilder endInfo = new StringBuilder();

        endInfo.append("Победа ").append(GameManager.whoWin()).append("\n");
        endInfo.append(GameManager.getInfo()).append("\n");
        endInfo.append("Прошло ").append(GameManager.getNumberOfDay()).append(" Дней");

        endtext.setText(endInfo);
    }

    public void onClickNewGame(View  view){
        GameManager.resetGame();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}