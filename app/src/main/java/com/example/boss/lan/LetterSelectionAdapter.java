package com.example.boss.lan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class LetterSelectionAdapter extends ArrayAdapter<User> {

    public LetterSelectionAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_customize, parent, false);
        }

        TextView userDescription = (TextView) convertView.findViewById(R.id.userDescription);
        CheckBox userCheck = (CheckBox) convertView.findViewById(R.id.userCheck);

        userDescription.setText(user.letter);

        /* Provjeri ArrayList u CheckedLetters CheckedLetters class. Ako user.letter postoji
        * u array-u, tada označi checkbox kao selected. Ako ne, ne označuj ga. */
        if (CheckedLetters.get(getContext()).getLetters().contains(user.letter)) {
            userCheck.setChecked(true);
        } else {
            userCheck.setChecked(false);
        }

        /* Ako user označi CheckBox, djelovat će u skladu s tim -- dodaje simptom
        * ako on ne postoji, i uklanja simptom ako on postoji. */
        userCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckedLetters.get(getContext()).getLetters().contains(user.letter)) {
                    CheckedLetters.get(getContext()).removeLetter(user.letter);
                } else {
                    CheckedLetters.get(getContext()).addLetter(user.letter);
                }
            }
        });

        return convertView;
    }
}
