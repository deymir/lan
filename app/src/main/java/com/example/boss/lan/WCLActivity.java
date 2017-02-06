package com.example.boss.lan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WCLActivity extends Activity {

    DatabaseAdapter dbAdapter;
    ListView wclListView;

    ArrayList<String> mResultLetters;
    Character mSelectedLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wcl_activity);

        wclListView = (ListView) findViewById(R.id.final_result_list);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        mResultLetters = intent.getStringArrayListExtra("restchars");
        Log.d("result",mResultLetters.toString());
        showLetters();
    }

    private void showLetters() {
        mSelectedLetter =  LetterResultAdapter.selectedLetterFromResult;
        Log.d("msg",mSelectedLetter.toString());

        //Dohvati ID_desc simptoma sa final_list
        ArrayList<Integer> resultDescriptionsIDs = dbAdapter.getResultLetterIDs(mResultLetters);

        //Dohvati ID_result sa odgovarajućim ID_desc i tada prikaži rezultat
        ArrayList<String> results = dbAdapter.getWords(resultDescriptionsIDs);

        if (results.size() == 0) {
            results.add("No matching results");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
        wclListView.setAdapter(adapter);
    }

}
