package com.example.boss.lan;

import android.content.Context;

import java.util.ArrayList;

@SuppressWarnings("ALL")

public class CheckedLetters {

    private ArrayList<String> mLetters;

    private static CheckedLetters sCheckedLetters;
    private Context mAppContext;

    private CheckedLetters(Context appContext) {
        mAppContext = appContext;
        mLetters = new ArrayList<>();
    }

    public static CheckedLetters get(Context context) {
        if (sCheckedLetters == null) {
            sCheckedLetters = new CheckedLetters(context.getApplicationContext());
        }
        return sCheckedLetters;
    }

    public ArrayList<String> getLetters() {
        return mLetters;
    }

    public void addLetter(String letter) {
        mLetters.add(letter);
    }

    public void removeLetter(String letter) {
        mLetters.remove(letter);
    }

    public void clearLetters() {
        mLetters.clear();
    }
}
