package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondSetting extends AppCompatActivity {

    private Spinner donSelect, comSelect;
    private TextView don, comissar;
    private ListView mafiaSelect;
    private ArrayList<String> mafias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);
        don = findViewById(R.id.don);
        comissar = findViewById(R.id.comissar);
        donSelect = findViewById(R.id.don_name);
        comSelect = findViewById(R.id.com_name);
        isVisible(false);

        mafiaSelect = findViewById(R.id.mafias_name);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                GameManager.getPlayersNames()
        );
        mafiaSelect.setAdapter(arrayAdapter);
        mafiaSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ableToSelectDon();
            }
        });
    }
    public void onClickStartGame(View view) {
        String don, commissar;
        don = donSelect.getSelectedItem().toString();
        commissar = comSelect.getSelectedItem().toString();

        if ((GameManager.getPlayersCount() / 3) > mafias.size()) {
            Toast toast = Toast.makeText(this, "Недостаточно мафий", Toast.LENGTH_LONG);
            toast.show();
        } else{
            GameManager.setRoles(mafias, don, commissar);
            Intent set2ToNight = new Intent(view.getContext(), DayActivity.class);
            startActivity(set2ToNight);
        }
    }
    public void ableToSelectDon(){
        mafias = new ArrayList<>();
        SparseBooleanArray selected = mafiaSelect.getCheckedItemPositions();
        for(int i = 0; i < selected.size(); i++){
            if(selected.get(i))
                mafias.add(GameManager.getPlayersNames().get(i));
        }
        if((GameManager.getPlayersCount() / 3) <= mafias.size()){
            isVisible(true);
            donSelect.setAdapter(setArrayForAdapter(mafias));
            comSelect.setAdapter(setArrayForAdapter(namesWithoutMafias()));
        }
    }

    private ArrayList<String> namesWithoutMafias(){
        ArrayList<String> names = GameManager.getPlayersNames();
        for(String name: mafias)
            names.remove(name);
        return names;
    }
    private void isVisible(boolean isVisible){
        if(isVisible){
            don.setVisibility(View.VISIBLE);
            comissar.setVisibility(View.VISIBLE);
            donSelect.setVisibility(View.VISIBLE);
            comSelect.setVisibility(View.VISIBLE);
        }else {
            don.setVisibility(View.INVISIBLE);
            comissar.setVisibility(View.INVISIBLE);
            donSelect.setVisibility(View.INVISIBLE);
            comSelect.setVisibility(View.INVISIBLE);
        }
    }

    private ArrayAdapter<String> setArrayForAdapter(ArrayList<String> list){
        return (new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                list));
    }
}