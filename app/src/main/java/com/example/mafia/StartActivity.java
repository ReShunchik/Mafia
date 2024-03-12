package com.example.mafia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private EditText inputTime;
    private ListView players;
    private PlayersAdapter playersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        players = findViewById(R.id.names_list);

        if(GameManager.getIsNewGame())
            playersAdapter = new PlayersAdapter(this, GameManager.oldPlayers());
        else
            playersAdapter = new PlayersAdapter(this);
        players.setAdapter(playersAdapter);

        inputTime = findViewById(R.id.input_time);
    }

    public void onClickMain(View view){
        if(playersAdapter.getNames().size() < 5){
            Toast.makeText(this, "Не хватает игроков",Toast.LENGTH_LONG).show();
        } else if (inputTime.getText().toString().equals("")) {
            Toast.makeText(this, "Не записано время на речь",Toast.LENGTH_LONG).show();
        }else if(playersAdapter.getNames().indexOf("") != -1){
            Toast.makeText(this, "Не все поля с именами заполнены",Toast.LENGTH_LONG).show();
        }
        else if(GameManager.namesCheck(playersAdapter.getNames())){
            Toast.makeText(this, "Есть одинаковые имена",Toast.LENGTH_LONG).show();
        }
        else{
            Intent Set1ToSet2 = new Intent(this, SetRoles.class);
            GameManager.setSpeechTime(Long.parseLong(inputTime.getText().toString()));
            startActivity(Set1ToSet2);
        }
    }
    public void onClickAdd(View view) {
        playersAdapter = new PlayersAdapter(this, playersAdapter.addNames());
        players.setAdapter(playersAdapter);
    }
}