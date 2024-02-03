package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VoteActivity extends AppCompatActivity {
    private  int itteration = 0;
    private TextView whoOnVoting;
    private EditText votingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        whoOnVoting = findViewById(R.id.whoOnVoting);
        votingNumber = findViewById(R.id.votingNumber);

        whoOnVoting.setText(PlayerManager.getWhoVoted(itteration));
    }

    public void onClickVote(View view) {
        itteration++;
        if(itteration < PlayerManager.getCountVoted()){
            whoOnVoting.setText(PlayerManager.getWhoVoted(itteration));
            PlayerManager.setVote(PlayerManager.getWhoVoted(itteration), Integer.parseInt(votingNumber.getText().toString()));
        }
        else
        {
            kick();
        }
    }

    private void kick(){
        PlayerManager.kickPlayer();
        if(PlayerManager.isEnd())
        {
            Intent goToEnd = new Intent(VoteActivity.this, EndActivity.class);
            startActivity(goToEnd);
        }
        else
        {
            Intent goToNight = new Intent(VoteActivity.this, NightActivity.class);
            startActivity(goToNight);
        }
    }
}