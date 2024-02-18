package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SecondSetting extends AppCompatActivity {

    private Spinner donSelect, comSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);

        ArrayAdapter<String> players = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames());
        donSelect = findViewById(R.id.don_name);
        donSelect.setAdapter(players);
        comSelect = findViewById(R.id.com_name);
        comSelect.setAdapter(players);

    }
    public void onClickStartGame(View view) {
        String don, commissar;
        EditText editMafia = findViewById(R.id.EnterMafia);

        don = donSelect.getSelectedItem().toString();
        commissar = comSelect.getSelectedItem().toString();
        String[] mafias = (editMafia.getText().toString()).split(",");

        if(!PlayerManager.checkRightNames(mafias)){
            PlayerManager.setRoles(mafias, don, commissar);
            Intent set2ToNight = new Intent(view.getContext(), DayActivity.class);
            startActivity(set2ToNight);
        }
        else{
            String text = "Ошибка в имени игрока";
            Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}