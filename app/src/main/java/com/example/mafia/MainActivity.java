package com.example.mafia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputPlayers, inputTime;
    private TextView playersInfo;
    private String name, info, playersNames = "";
    private Toast yetAdd, emptyAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPlayers = findViewById(R.id.input_players);
        playersInfo = (TextView)findViewById(R.id.players_info1);
        yetAdd = Toast.makeText(this, "Такой игрок уже есть", Toast.LENGTH_LONG);
        emptyAdd = Toast.makeText(this, "Вы не ввели имя", Toast.LENGTH_LONG);
    }

    public void onClickMain(View view){
        if(PlayerManager.getPlayersCount() < 10){
            Toast notEnough = Toast.makeText(this, "Не хватает игроков",Toast.LENGTH_LONG);
            notEnough.show();
        }
        else{
            inputTime = findViewById(R.id.input_time);
            Intent Set1ToSet2 = new Intent(this, SecondSetting.class);
            PlayerManager.setSpeechTime(Long.parseLong(inputTime.getText().toString()));
            startActivity(Set1ToSet2);
        }
    }
    public void onClickAdd(View view){
        name = inputPlayers.getText().toString();
        if(PlayerManager.CheckSameName(name))
            yetAdd.show();
        else if (PlayerManager.checkEmptyName(name)){
            emptyAdd.show();
        } else{
            PlayerManager.addPlayers(name);
            info = String.format("%d.%s", PlayerManager.getPlayersCount(), name);
            playersNames += (info + "\n");
            playersInfo.setText(playersNames);
            inputPlayers.setText("");
        }
        if(PlayerManager.getPlayersCount() == 10){
            playersNames = "";
            playersInfo = (TextView)findViewById(R.id.players_info2);
        }
    }
}