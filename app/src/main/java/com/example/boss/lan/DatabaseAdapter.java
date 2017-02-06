package com.example.boss.lan;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {


    private static final String TAG = "DatabaseAdapter";
    private DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    private static String DB_PATH = "/data/data/com.example.boss.lan/databases/";
    private static final String DATABASE_NAME = "Test.sqlite";
    private static final int DATABASE_VERSION = 1;

    //Table TB_DESCRIPTIONS contains: ID , letter , ID_area (primary key from table TB_AREAS)
    private static final String TB_DESCRIPTIONS = "descriptions";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_ID_DESC = "ID_desc";
    //and column COL_ID_AREA


    //Table TB_AREAS contains: ID_area , area
    private static final String TB_AREAS = "areas";

    //table TB_RESULTS contains: ID_result , result
    private static final String TB_RESULTS = "results";
    private static final String COL_ID_RESULT = "ID_result";
    private static final String COL_RESULT = "result";

    //table TB_RELATION contains: ID_result, ID_desc, and weight
    private static final String TB_RELATION = "relation";



    //table TB_AREAS
    private static final String COL_ID_AREA = "ID_area";
    private static final String COL_AREA = "area";




    private final Context adapterContext;

    public DatabaseAdapter(Context context) {
        this.adapterContext = context;
    }

    public DatabaseAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(adapterContext);

        try {
            mDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            mDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        return this;
    }


    //GET DWSCRIPTIONS BY AREA, method that is used to populate ListView level_fragment in global_fragment.xml
    public ArrayList<User> getDescriptionsByArea(int area) {

        ArrayList<User> listByArea = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] col = {COL_DESCRIPTION};
        String condition = COL_ID_AREA + "='" + area + "'";

        Cursor cursor = db.query(TB_DESCRIPTIONS, col, condition, null, null, null, null);

        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(COL_DESCRIPTION);
            User myuser = new User();
            myuser.letter = cursor.getString(index);
            listByArea.add(myuser);
        }

        db.close();
        return listByArea;
    }




    public ArrayList<Integer> getResultLetterIDs(ArrayList<String> resultLetters) {

        String resultLettersString = "";
        for (int i = 0; i < resultLetters.size(); i++) {
            resultLettersString += resultLetters.get(i);
        }

        Log.d("Boss", "getResultLetterIDs() called with letters: " + resultLettersString);

        ArrayList<Integer> resultLettersIDArray = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        for (int i = 0; i < resultLetters.size(); i++) {
            String globString = resultLetters.get(i);

            String[] col = {COL_ID_DESC};
            String strParam = globString.replace("'", "''");
            String condition = COL_DESCRIPTION + " LIKE ('%" + strParam + "%')";
            Cursor cursor = db.query(TB_DESCRIPTIONS, col, condition, null, null, null, null);

            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(COL_ID_DESC);
                resultLettersIDArray.add(cursor.getInt(index));
            }

            cursor.close();
        }
        Log.d("id",resultLettersIDArray.toString());
        return resultLettersIDArray;
    }





    public ArrayList<String> getWords(ArrayList<Integer> wordIDs) {

        Log.d("FLC", "getWords() called");

        ArrayList<String> wordsArray = new ArrayList<>();
        ArrayList<String> wordsDescArray = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String wordIDcontainer = "("; // prefiks
        for (int i = 0; i < wordIDs.size(); i++) {
            Log.d("FLC", "WordID = " + wordIDs.get(i));
            wordIDcontainer += "'" + wordIDs.get(i) + "', ";
        }
        Log.d("wordIdcon",wordIDcontainer);

        if (wordIDcontainer.length() > 2) {
            wordIDcontainer = wordIDcontainer.substring(0, wordIDcontainer.length() - 2);
        }

        wordIDcontainer += ")"; // sufiks

        String[] col = {COL_ID_RESULT};
        String condition = COL_ID_DESC + " IN " + wordIDcontainer;
        Cursor cursor = db.query(TB_RELATION, col, condition, null, null, null, null);

        while (cursor.moveToNext()) {
            int indexOfWordColumn = cursor.getColumnIndex(COL_ID_RESULT);
            if(!wordsArray.contains(cursor.getString(indexOfWordColumn))) {
                wordsArray.add(cursor.getString(indexOfWordColumn));
            }
            Log.d("FLC", "Got the word ID: " + cursor.getString(indexOfWordColumn));
        }

        for (int j=0;j<wordsArray.size();j++){
            String[] col1 = {COL_RESULT};
            String condition1 = COL_ID_RESULT + " = " + wordsArray.get(j);
            cursor = db.query(TB_RESULTS, col1, condition1, null, null, null, null);

            while (cursor.moveToNext()) {
                int indexOfWordColumn = cursor.getColumnIndex(COL_RESULT);
                wordsDescArray.add(cursor.getString(indexOfWordColumn));
                Log.d("FLC", "Got the word description: " + cursor.getString(indexOfWordColumn));
            }
        }
        return wordsDescArray;
    }


    //On first run copy database onto device
    private static class DatabaseHelper extends SQLiteOpenHelper {

        Context helperContext;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            helperContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


        public void createDataBase() throws IOException {
            boolean dbExist = checkDataBase();
            if (dbExist) {
            } else {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    throw new Error("Error copying database");
                }
            }
        }


        public SQLiteDatabase getDatabase() {
            String myPath = DB_PATH + DATABASE_NAME;
            return SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        }

        private boolean checkDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                String myPath = DB_PATH + DATABASE_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
            } catch (SQLiteException ignored) {
            }
            if (checkDB != null) {
                checkDB.close();
            }
            return checkDB != null;
        }

        private void copyDataBase() throws IOException {

            // Open your local db as the input stream
            InputStream myInput = helperContext.getAssets().open(DATABASE_NAME);

            // Path to the just created empty db
            String outFileName = DB_PATH + DATABASE_NAME;

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }

        public void openDataBase() throws SQLException {
            // Open the database
            String myPath = DB_PATH + DATABASE_NAME;
            mDb = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public synchronized void close() {
            if (mDb != null)
                mDb.close();
            super.close();
        }

    }
}
