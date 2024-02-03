package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class SecondSetting extends AppCompatActivity {

    private EditText editMafia;
    private EditText editDon;
    private EditText editCommissar;
    private String[] mafias;
    private String don, comissar, forcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);
    }
    public void onClickStartGame(View view) {
        editMafia = findViewById(R.id.EnterMafia);
        editDon = findViewById(R.id.EnterDon);
        editCommissar = findViewById(R.id.EnterCommissar);

        mafias = (editMafia.getText().toString()).split(",");
        don = editDon.getText().toString();
        comissar = editCommissar.getText().toString();
        forcheck = Arrays.toString((editMafia.getText().toString()).split(",")) + don + "," + comissar;

        if(!PlayerManager.checkRightNames(forcheck)){
            PlayerManager.setRoles(mafias, don, comissar);
            Intent set2ToNight = new Intent(view.getContext(), DayActivity.class);
            startActivity(set2ToNight);
        }
        else{
            String text = "Ошибка в имени игрока";
            Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}