package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VoteActivity extends AppCompatActivity {
    private int maxVote, numberVoteReplays = 0;
    private boolean isDivision = false;
    private VoteAdapter voteAdapter;
    private RadioGroup division;
    private ListView voteList;
    private HashMap<String, Integer> vote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        division = findViewById(R.id.for_division);
        division.setVisibility(View.GONE);

        voteList = findViewById(R.id.player_list);
        voteAdapter = new VoteAdapter(this, GameManager.getAllVoted(), true);
        voteList.setAdapter(voteAdapter);
    }

    public void onClickVote(View view) {
        vote = voteAdapter.getEditTextValues();
        if (isDivision) {
            RadioButton kickAll = findViewById(R.id.kick);
            if (kickAll.isChecked()) {
                kickAllPlayers();
            }
            startActivity(end());
        } else if (checkCountVoted()) {
            maxVote = 0;
            ArrayList<Integer> values = new ArrayList<>(vote.values());
            if (maxVoteCount(values) == 1) {
                kickPlayers();
                startActivity(end());
            } else {
                numberVoteReplays++;
                if (numberVoteReplays == 2) {
                    isDivision = true;
                    division.setVisibility(View.VISIBLE);
                }
                voteAdapter = new VoteAdapter(this, newVoteList(), !isDivision);
                voteList.setAdapter(voteAdapter);
            }
        } else {
            Toast toast = Toast.makeText(this, "Слишком много голосов", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void kickPlayers() {
        for (Map.Entry<String, Integer> player :
                vote.entrySet())
            if (player.getValue() == maxVote)
                GameManager.kickPlayer(player.getKey());
    }

    private void kickAllPlayers() {
        for (String name : GameManager.getAllVoted())
            GameManager.kickPlayer(name);
    }

    private int maxVoteCount(ArrayList<Integer> count) {
        int maxCount = 0;
        for (int n : count) {
            if (n > maxVote) {
                maxVote = n;
                maxCount = 1;
            } else if (n == maxVote)
                maxCount++;
        }
        return maxCount;
    }

    private boolean checkCountVoted() {
        int check = 0;
        for (Map.Entry<String, Integer> player :
                vote.entrySet())
            check += player.getValue();
        if (check > GameManager.getPlayersCount())
            return false;
        else {
            check = GameManager.getPlayersCount() - check;
            int index = GameManager.getAllVoted().size() - 1;
            String name = GameManager.getAllVoted().get(index);
            vote.put(name, check);
            return true;
        }
    }

    private ArrayList<String> newVoteList() {
        ArrayList<String> newList = new ArrayList<>();
        for (Map.Entry<String, Integer> player :
                vote.entrySet())
            if (player.getValue() == maxVote)
                newList.add(player.getKey());
        GameManager.setWhoVoted(newList);
        return newList;
    }

    private Intent end() {
        Intent intent;
        if (GameManager.isEnd())
            intent = new Intent(this, EndActivity.class);
        else
            intent = new Intent(this, NightActivity.class);
        return intent;
    }
}