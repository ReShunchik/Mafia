package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VoteActivity extends AppCompatActivity {
    private int countOfVoting = PlayerManager.getCountVoted();
    private  int itteration = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        TextView whoOnVoting = findViewById(R.id.whoOnVoting);
        EditText votingNumber = findViewById(R.id.votingNumber);
        Button next = findViewById(R.id.next);

        whoOnVoting.setText(PlayerManager.getWhoVoted(0));
        ArrayList<Integer> vote = new ArrayList<>();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itteration > countOfVoting) {
                    int maxVote = Collections.max(vote);
                    if(equalsNumber(vote, maxVote)){
                        ArrayList<Integer> indexes = new ArrayList<>();
                        for(int index = 0; index < vote.size(); index++){
                            if(vote.get(index) == maxVote)
                                indexes.add(index);
                        }
                        Intent goToNewVote = new Intent(view.getContext(), VoteActivity.class);
                        startActivity(goToNewVote);
                    }
                    Intent goToNight = new Intent(view.getContext(), NightActivity.class);
                    startActivity(goToNight);
                }
                else {
                    itteration++;
                    vote.add(Integer.parseInt(votingNumber.getText().toString()));
                }
            }
        });
    }
    private boolean equalsNumber(ArrayList<Integer> forCheck, int max){
        int check = 0;
        boolean isMore = false;
        for(int num: forCheck) {
            if (num == max)
                check++;
            if(check == 2)
                isMore = true;
        }
        return isMore;
    }
}