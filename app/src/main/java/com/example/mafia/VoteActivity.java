package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VoteActivity extends AppCompatActivity {
    private int maxVote = 0;
    private String kickName;
    private VoteAdapter voteAdapter;
    private RadioGroup division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        division = findViewById(R.id.for_division);
        division.setVisibility(View.GONE);
        ListView voteList = findViewById(R.id.player_list);
        voteAdapter = new VoteAdapter(this, PlayerManager.getAllVoted(), true);
        voteList.setAdapter(voteAdapter);
    }
    public void onClickVote(View view) {
        boolean isDivision = getIntent().getBooleanExtra("isDivision", false);
        if(isDivision)
            Divison();
        else
            commonVote();
        if(getIntent().getBooleanExtra("isDivision", false)){
            RadioButton radio = findViewById(R.id.kick);
            if(radio.isChecked()){
                for(String name: PlayerManager.getAllVoted())
                    PlayerManager.kickPlayer(name);
            }
            Intent intent = End();
            startActivity(intent);
        }
    }

    private void commonVote(){
        HashMap<String, Integer> vote = voteAdapter.getEditTextValues();
        Intent intent;
        if(checkMaxVote(vote))
            intent = End();
        else{
            MapToArray(vote);
            intent = new Intent(this, DayActivity.class);
            intent.putExtra("isDivision", true);
        }
        startActivity(intent);
    }
    private void Divison(){
        HashMap<String, Integer> vote = voteAdapter.getEditTextValues();
        Intent intent;
        if(checkMaxVote(vote)){
            intent = End();
        }
        else{
            division.setVisibility(View.VISIBLE);
            MapToArray(vote);
            voteAdapter = new VoteAdapter(this, PlayerManager.getAllVoted(), false);
            intent = new Intent();
        }
        startActivity(intent);
    }


    private Intent End(){
        Intent intent;
        PlayerManager.kickPlayer(kickName);
        if(PlayerManager.isEnd())
            intent = new Intent(this, EndActivity.class);
        else
            intent = new Intent(this, NightActivity.class);
        return intent;
    }
    private boolean checkMaxVote(HashMap<String, Integer> values){
        int maxCount = 0;
        for(Map.Entry<String, Integer> set :
                values.entrySet()){
            if(set.getValue() > maxVote){
                kickName = set.getKey();
                maxVote = set.getValue();
                maxCount = 1;
            }
            else if (set.getValue() == maxVote) {
                maxCount++;
            }
        }
        return (maxCount == 1);
    }
    private void MapToArray(HashMap<String, Integer> hashMap){
        ArrayList<String> newVoted = new ArrayList<>();
        for(Map.Entry<String, Integer> set :
                hashMap.entrySet()){
            if(set.getValue() == maxVote)
                newVoted.add(set.getKey());
        }
        PlayerManager.listToArray(newVoted);
    }
}