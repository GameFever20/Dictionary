package com.example.aisha.newmaterialsearchview;

/**
 * Created by Aisha on 4/9/2017.
 */

import android.os.AsyncTask;


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


public class Dictionaryrandomwords extends AsyncTask<Void, Void, String>  {

    MainActivity mainActivityclass;
    ArrayList<String> myrandomWordsArraylist=new ArrayList<>();
    ArrayList<Dictionary> randomwordDictionaryAL=new ArrayList<>();

    String wordname;

    public void setMyrandomWordsArraylist(ArrayList<String> myrandomWordsArraylist) {
        this.myrandomWordsArraylist = myrandomWordsArraylist;
    }


    public ArrayList<String> getMyrandomWordsArraylist() {
        return myrandomWordsArraylist;
    }



    public Dictionaryrandomwords(MainActivity mymainActivityclass) {
        mainActivityclass = mymainActivityclass;
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


        HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCorpusCount=0&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=-1&limit=8&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");

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
                JSONArray jsonArray=new JSONArray(results);


                for (int i=0;i<jsonArray.length();i++){
                    Log.d("wordname",jsonArray.getJSONObject(i).getString("word"));
                    myrandomWordsArraylist.add(jsonArray.getJSONObject(i).getString("word"));

                  //Dictionary class object
                    Dictionary dictionary=new Dictionary(mainActivityclass);
                    dictionary.setWord(jsonArray.getJSONObject(i).getString("word"));
                    dictionary.fetchWordPronunciation();
                    randomwordDictionaryAL.add(dictionary);

                    //setWordname(jsonArray.getJSONObject(i).getString("word"));
                  //  Log.d("wordname func",getWordname());
                }

            } catch (JSONException je) {
                je.printStackTrace();
            }

            Log.d("my text", "onPostExecute meaning: Executed");
            Log.d("dictionary",myrandomWordsArraylist.size()+"");

        } else {
            Log.d("my text", "onPostExecute meaning: No data");

        }
        setMyrandomWordsArraylist(myrandomWordsArraylist);
        callmainforpassingresults();

    }




    public void callmainforpassingresults(){
        mainActivityclass.listenandgetrandomword( randomwordDictionaryAL);

    }

}
