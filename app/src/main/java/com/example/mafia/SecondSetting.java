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

public class SecondSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_setting);

        Button start = findViewById(R.id.start_button2);
        Button test = findViewById(R.id.test1);

        RecyclerView setRoles = findViewById(R.id.set_roles);


        RolesAdapter roles = new RolesAdapter(SecondSetting.this);
        setRoles.setLayoutManager(new LinearLayoutManager(this));
        setRoles.setAdapter(roles);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roles.setCount();
                setRoles.setLayoutManager(new LinearLayoutManager(SecondSetting.this));
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String mafia = "", don = "", commisar = "";
                for(int i = 0; i < roles.getItemCount(); i++){
                    if((roles.playerRole.get(i).equals("Мафия")))
                        mafia += (roles.names.get(i)) + " ";
                    if((roles.playerRole.get(i).equals("Дон")))
                        don = (roles.names.get(i));
                    if((roles.playerRole.get(i).equals("Комиссар")))
                        commisar = (roles.names.get(i));
                }
                PlayerManager.setRoles(mafia.split(" "), don, commisar);*/
                Intent Set2ToDay = new Intent(SecondSetting.this, DayActivity.class);
                startActivity(Set2ToDay);
            }
        });
    }
}