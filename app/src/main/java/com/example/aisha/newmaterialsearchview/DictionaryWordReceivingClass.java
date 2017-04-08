package com.example.aisha.newmaterialsearchview;

import android.os.AsyncTask;

/**
 * Created by Aisha on 4/7/2017.
 */

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;


public class DictionaryWordReceivingClass extends AsyncTask<Void, Void, String> {

    String word;
    String note;
    ArrayList<String> myexamplelist = new ArrayList<String>();
    ArrayList<String> mydefinitiontextlist = new ArrayList<String>();
    ArrayList<String> mydefinitionpartofspeechlist = new ArrayList<String>();

    MainActivity mainActivityclass;
    String date;

    public DictionaryWordReceivingClass(MainActivity mymainActivityclass, String mydate) {
        mainActivityclass = mymainActivityclass;
        date = mydate;
    }

    public DictionaryWordReceivingClass() {

    }


    public ArrayList<String> getMydefinitionpartofspeechlist() {
        return mydefinitionpartofspeechlist;
    }


    public ArrayList<String> getMydefinitiontextlist() {
        return mydefinitiontextlist;
    }



    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getMyexamplelist() {
        return myexamplelist;
    }



    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        Log.d("My TAg", "doInBackground: done calling rest");


        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);


            if (n > 0) out.append(new String(b, 0, n));
        }


        return out.toString();
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d("My TAg", "doInBackground: calling rest");
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        // HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCorpusCount=0&minLength=5&maxLength=15&limit=1&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");


        HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/words.json/wordOfTheDay?date=" + date + "&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");

        String text = null;

        try {
            Log.d("My TAg", "doInBackground: going to call rest");
            HttpResponse response = httpClient.execute(httpGet, localContext);

            Log.d("My TAg", "doInBackground: done calling rest");
            HttpEntity entity = response.getEntity();
            text = getASCIIContentFromEntity(entity);

        } catch (Exception e) {
            Log.d("My TAg", "doInBackground: in catch");
            return e.getLocalizedMessage();
        }
        return text;
    }

    protected void onPostExecute(String results) {
        if (results != null) {
            try {
                Log.d("Tag", "onPostExecute: " + results);
                JSONObject jsonObj = new JSONObject(results);

                setWord(jsonObj.getString("word"));
                setNote(jsonObj.getString("note"));
                Log.d("My TAg", "word of the day is " + jsonObj.getString("word"));
                Log.d("My TAg", "Note about word " + jsonObj.getString("note"));

                for (int i = 0; i < 3; i++) {

                    if (jsonObj.getJSONArray("examples").isNull(i) == false) {

                        myexamplelist.add(jsonObj.getJSONArray("examples").getJSONObject(i).getString("text"));
                        Log.d("My TAg", "examples are "+jsonObj.getJSONArray("examples").getJSONObject(i).getString("text"));


                    } else {
                        Log.d("My TAg", "examples are only this much ");
                    }

                }
                for (int i = 0; i < 3; i++) {

                    JSONObject tempstoringdefinationjsonobj;
                    String definationtext;
                    String definationpartofspeech;

                    if (jsonObj.getJSONArray("definitions").isNull(i) != true) {
                        tempstoringdefinationjsonobj = jsonObj.getJSONArray("definitions").getJSONObject(i);

                        if (tempstoringdefinationjsonobj.isNull("text") != true) {

                            definationtext = tempstoringdefinationjsonobj.getString("text");
                            mydefinitiontextlist.add(definationtext);
                            Log.d("My TAg", "definition text are " + definationtext);

                            if (tempstoringdefinationjsonobj.isNull("partOfSpeech")!=true) {

                                    definationpartofspeech = tempstoringdefinationjsonobj.getString("partOfSpeech");
                                    mydefinitionpartofspeechlist.add(definationpartofspeech);
                                    Log.d("My TAg", "definition   part of speech are " + definationpartofspeech);

                            }else{
                                Log.d("My TAg", "definition   part of speech are not found" );

                            }


                        } else {
                            Log.d("My TAg", "definition text  are not found");

                        }

                    }


                }

            } catch (JSONException je) {
                je.printStackTrace();
            }
            //et.setText(allKey);

            Log.d("my text", "onPostExecute meaning: Executed");
        } else {
            Log.d("my text", "onPostExecute meaning: No data");

        }

        callmainactivity();


    }

    public void callmainactivity() {
        mainActivityclass.listenerandgetterwords();

    }
}
