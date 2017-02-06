package com.example.boss.lan;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends Activity {
    FragmentManager manager;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        manager = getFragmentManager();

        mActivity = this;

        final ArrayList<String> checkedLetters = CheckedLetters.get(getApplicationContext()).getLetters();

        Button nextStep = (Button) findViewById(R.id.nextStep);
        Button clearAllButton = (Button) findViewById(R.id.clearAll);

        //Action to perform when nextstep is clicked
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ResultActivity.class));
            }
        });

        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedLetters.clear();
                finish();
                startActivity(getIntent());
            }
        });

        displayFr1(findViewById(android.R.id.content));

    }


    public void displayFr1(View v) {
        Fragment1 fragment1 = new Fragment1();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment1, "FR1");
        transaction.commit();
    }

    public void displayFr2(View v) {
        Fragment2 fragment2 = new Fragment2();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment2, "FR2");
        transaction.commit();

    }

    public void displayFr3(View v) {
        Fragment3 fragment3 = new Fragment3();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment3, "FR3");
        transaction.commit();
    }

    public void displayFr4(View v) {
        Fragment4 fragment4 = new Fragment4();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment4, "FR4");
        transaction.commit();
    }

    public void displayFr5(View v) {
        Fragment5 fragment5 = new Fragment5();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment5, "FR5");
        transaction.commit();
    }

    public void displayFr6(View v) {
        Fragment6 fragment6 = new Fragment6();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment6, "FR6");
        transaction.commit();
    }

    public void displayFr7(View v) {
        Fragment7 fragment7 = new Fragment7();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment7, "FR7");
        transaction.commit();
    }

    public void displayFr8(View v) {
        Fragment8 fragment8 = new Fragment8();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.group, fragment8, "FR8");
        transaction.commit();
    }
}
