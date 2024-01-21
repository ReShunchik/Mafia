package com.example.mafia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RolesAdapter extends RecyclerView.Adapter<RolesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    ArrayList<String> names, playerRole;
    public int count = 0;
    RolesAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.items, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArrayAdapter<String> NamesPlayerAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                PlayerManager.getPlayersNames()
        );
        ArrayAdapter<String> AllRolesAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                PlayerManager.getRoles()
        );

        NamesPlayerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner1.setAdapter(NamesPlayerAdapter);
        names.add(holder.spinner1.getSelectedItem().toString());
        AllRolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner2.setAdapter(AllRolesAdapter);
        playerRole.add(holder.spinner2.getSelectedItem().toString());

        holder.spinner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    names.add(holder.spinner1.getSelectedItem().toString());
                }
                catch (Exception e){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return count+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Spinner spinner1, spinner2;
        public ViewHolder(View view) {
            super(view);
            spinner1 = view.findViewById(R.id.spinner1);
            spinner2 = view.findViewById(R.id.spinner2);
        }
    }

    public void setCount() {
        this.count++;
    }
}
