package com.example.boss.lan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class LetterResultAdapter extends ArrayAdapter<User> {

    public static char EMPTY_SELECTED_LETTER = '$';

    public static char selectedLetterFromResult = EMPTY_SELECTED_LETTER;

    public LetterResultAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_customize, parent, false);
        }

        TextView userLetter = (TextView) convertView.findViewById(R.id.userDescription);
        CheckBox userCheck = (CheckBox) convertView.findViewById(R.id.userCheck);

        userLetter.setText(user.letter);
        userCheck.setChecked(false);

        userCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLetterFromResult = user.letter.charAt(0);
            }
        });

        return convertView;
    }
}
