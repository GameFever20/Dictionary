package com.example.aisha.newmaterialsearchview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.id.message;

/**
 * Created by Aisha on 4/13/2017.
 */

public class DatabaseHandlerBookmark extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BookMarkSystem";

    // Contacts table name
    private static final String TABLE_BOOKMARK = "BookMark";

    // Contacts Table Columns names

    private static final String KEY_DICTIONARY_WORD_NAME = "name";
    private static final String KEY_DICTIONARY_WORD_MEANING = "meaning";
    private static final String KEY_DICTIONARY_WORD_EXAMPLE = "example";
    private static final String KEY_DICTIONARY_WORD_PARTOFSPEECH = "partofspeech";
    private static final String KEY_DICTIONARY_WORD_ANTONYM = "antonym";
    private static final String KEY_DICTIONARY_WORD_SYNONYM = "synonym";
    private static final String KEY_DICTIONARY_WORD_SAMECONTEXT = "samecontext";
    private static final String KEY_DICTIONARY_WORD_PRONUNCIATION = "pronunciation";

    private String[] allColumns = {DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_NAME, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_MEANING, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_PARTOFSPEECH, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_EXAMPLE, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_ANTONYM, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_SYNONYM, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_SAMECONTEXT, DatabaseHandlerBookmark.KEY_DICTIONARY_WORD_PRONUNCIATION};


    public DatabaseHandlerBookmark(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("In Databse Constructor", "Passed in");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_BOOKMARKS_TABLE = "CREATE TABLE " + TABLE_BOOKMARK + "("

                + KEY_DICTIONARY_WORD_NAME + " TEXT,"
                + KEY_DICTIONARY_WORD_MEANING + " TEXT,"
                + KEY_DICTIONARY_WORD_PARTOFSPEECH + " TEXT,"
                + KEY_DICTIONARY_WORD_EXAMPLE + " TEXT,"
                + KEY_DICTIONARY_WORD_ANTONYM + " TEXT,"
                + KEY_DICTIONARY_WORD_SYNONYM + " TEXT,"
                + KEY_DICTIONARY_WORD_SAMECONTEXT + " TEXT,"
                + KEY_DICTIONARY_WORD_PRONUNCIATION + " TEXT "

                + ")";
        sqLiteDatabase.execSQL(CREATE_BOOKMARKS_TABLE);

        Log.d("On create", "Created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);

        Log.d("On upgrade", "Drop old Table");
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addToBookMark(Dictionary dictionary) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_DICTIONARY_WORD_NAME, dictionary.getWord());
        values.put(KEY_DICTIONARY_WORD_MEANING, arrayListToString(dictionary.getWordMeaning()));
        values.put(KEY_DICTIONARY_WORD_PARTOFSPEECH, arrayListToString(dictionary.getWordPartOfSpeech()));
        values.put(KEY_DICTIONARY_WORD_EXAMPLE, arrayListToString(dictionary.getWordExample()));
        values.put(KEY_DICTIONARY_WORD_ANTONYM, arrayListToString(dictionary.getWordAntonym()));
        values.put(KEY_DICTIONARY_WORD_SYNONYM, arrayListToString(dictionary.getWordSynonms()));
        values.put(KEY_DICTIONARY_WORD_SAMECONTEXT, arrayListToString(dictionary.getWordSameContext()));
        values.put(KEY_DICTIONARY_WORD_PRONUNCIATION, dictionary.getWordPronunciation());


        // Inserting Row

        long insertId = db.insert(TABLE_BOOKMARK, null, values);

        //retrieve data
        Cursor cursor = db.query(DatabaseHandlerBookmark.TABLE_BOOKMARK, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        /*
        while (!cursor.isAfterLast()) {
            String s = getDatabaseItems(cursor);
            Log.d("Retrieve data", s);
            cursor.moveToNext();
        }*/

        String s = cursor.getString(2);
        Log.d("Retrieve data", s);
        Log.d("Values in databse", dictionary.getWord());
        db.close(); // Closing database connection
    }

    public String arrayListToString(ArrayList<String> temp) {
        String storing = "";
        for (int i = 0; i < temp.size(); i++) {
            storing = storing + "\n * " + temp.get(i);
        }
        Log.d("Arraylist To String", storing);
        return storing;


    }

    public String getDatabaseItems(Cursor cursor) {
        String s = cursor.getString(2);
        // String p=cursor.getString(2);
        return s;

    }
}

