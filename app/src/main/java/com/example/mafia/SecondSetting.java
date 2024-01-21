package com.example.mafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);

        Button start = findViewById(R.id.start_button2);
        EditText editMafia = findViewById(R.id.EnterMafia);
        EditText editDon = findViewById(R.id.EnterDon);
        EditText editCommissar = findViewById(R.id.EnterCommissar);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] mafias = (editMafia.getText().toString()).split(",");
                String don = editDon.getText().toString();
                String comissar = editCommissar.getText().toString();
                String forcheck = (editMafia.getText().toString()).split(",") + don + "," + comissar;
                if(PlayerManager.checkRightNames(forcheck) == true){
                    PlayerManager.setRoles(mafias, don, comissar);
                    Intent set2ToNight = new Intent(view.getContext(), DayActivity.class);
                }
                else{
                    String text = "Ошибка в имени игрока";
                    Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}