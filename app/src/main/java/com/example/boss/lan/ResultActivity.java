package com.example.boss.lan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends Activity {

    ListView mLetterListView;
    ArrayList<String> mTheRestChars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Button backToStep1 = (Button) findViewById(R.id.btnBack);
        Button showButton = (Button) findViewById(R.id.btnShow);
        mLetterListView = (ListView) findViewById(R.id.final_list);

        backToStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, WCLActivity.class);
                //Put the selected descriptions and pass to another activity
                i.putStringArrayListExtra("restchars", mTheRestChars);
                startActivity(i);
            }
        });

        showLetters();
    }

    private void showLetters() {

        ArrayList<String> checkedLetters = CheckedLetters.get(getApplicationContext()).getLetters();
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < checkedLetters.size(); i++) {
            User u = new User(checkedLetters.get(i), false);
            userArrayList.add(u);

            mTheRestChars.add(checkedLetters.get(i));
        }

        mLetterListView.setAdapter(new LetterResultAdapter(this, userArrayList));
    }
}
