package com.example.mafia;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class VoteAdapter extends BaseAdapter {

    private final Context context;
    public ArrayList<String> names;
    private HashMap<String, Integer> EditTextValues = new HashMap<>();
    private boolean editTextVisible;

    public VoteAdapter(Context context, ArrayList<String> names, boolean editTextVisible) {

        this.context = context;
        this.names = names;
        this.editTextVisible = editTextVisible;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.vote_list, null, true);

            holder.editText = convertView.findViewById(R.id.count_vote);
            TextView name = convertView.findViewById(R.id.player_name);
            name.setText(names.get(position));
            if(position == names.size()-1)
                holder.editText.setVisibility(View.GONE);
            if(!editTextVisible)
                holder.editText.setVisibility(View.GONE);
            convertView.setTag(holder);

            //convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = holder.editText.getText().toString();
                if(value.equals(""))
                    value = "0";
                EditTextValues.put(names.get(position), Integer.parseInt(value));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return convertView;
    }
    public HashMap<String, Integer> getEditTextValues(){
        return EditTextValues;
    }

    private class ViewHolder {
        protected EditText editText;
    }
}