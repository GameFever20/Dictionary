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

public class Dictionary {


    private String word;
    private String wordPronunciation;
    private ArrayList<String> wordMeaning;


    private boolean isCalledForPronunciation;
    private boolean calledbyScrollingWordoftheday;

    private boolean meaningFetched;
    private boolean antonymFetched;
    private boolean isPronunciationFetched;
    private boolean exampleFetched;

    public boolean isPronunciationFetchedCheck() {
        return pronunciationFetchedCheck;
    }

    public void setPronunciationFetchedCheck(boolean pronunciationFetchedCheck) {
        this.pronunciationFetchedCheck = pronunciationFetchedCheck;
    }

    private boolean pronunciationFetchedCheck;
   // private boolean synonymFetched;

    private MainActivity mainActivity;
    private ScrollingDictionaryDetailActivity scrollingDictionaryDetailActivity;
    private ScrollingActivity scrollingActivity;

    private ArrayList<String> wordExample;
    private ArrayList<String> wordSameContext;
    private ArrayList<String> wordSynonms;
    private ArrayList<String> wordAntonym;
    private ArrayList<String> wordPartOfSpeech;


    public boolean isCalledForPronunciation() {
        return isCalledForPronunciation;
    }

    public void setCalledForPronunciation(boolean calledForPronunciation) {
        isCalledForPronunciation = calledForPronunciation;
    }

    public boolean isPronunciationFetched() {
        return isPronunciationFetched;
    }

    public void setPronunciationFetched(boolean pronunciationFetched) {
        isPronunciationFetched = pronunciationFetched;
    }


    public String getWordPronunciation() {
        return wordPronunciation;
    }

    public void setWordPronunciation(String wordPronunciation) {
        this.wordPronunciation = wordPronunciation;
    }


    public Dictionary(String word) {
        //intializemeaning();
        this.word = word;

    }

    public Dictionary(MainActivity m) {
        mainActivity = m;
    }

    public Dictionary() {

    }


    public ArrayList<String> getWordSameContext() {
        return wordSameContext;
    }

    public void setWordSameContext(ArrayList<String> wordSameContext) {
        this.wordSameContext = wordSameContext;
    }


    public ArrayList<String> getWordSynonms() {
        return wordSynonms;
    }

    public void setWordSynonms(ArrayList<String> wordSynonms) {
        this.wordSynonms = wordSynonms;
    }

    public ArrayList<String> getWordAntonym() {
        return wordAntonym;
    }

    public void setWordAntonym(ArrayList<String> wordAntonym) {
        this.wordAntonym = wordAntonym;
    }

    public ArrayList<String> getWordExample() {
        return wordExample;
    }

    public void setWordExample(ArrayList<String> wordExample) {
        this.wordExample = wordExample;
        Log.d("setExample", wordExample.get(0));
    }

    public ArrayList<String> getWordPartOfSpeech() {
        return wordPartOfSpeech;
    }

    public void setWordPartOfSpeech(ArrayList<String> wordPartOfSpeech) {
        this.wordPartOfSpeech = wordPartOfSpeech;
    }

    public ArrayList<String> getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(ArrayList<String> wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public boolean isMeaningFetched() {
        return meaningFetched;
    }
    public boolean isExampleFetched() {
        return exampleFetched;
    }

    public void setMeaningFetched(boolean meaningFetched) {
        this.meaningFetched = meaningFetched;
        if (!meaningFetched) {
       //     intializemeaning();
        }

    }

    public void setExampleFetched(boolean exampleFetched) {
        this.exampleFetched = exampleFetched;
        if (!exampleFetched) {
            setWordExample(wordExample);
        }

    }

    public void setAntonymFetched(boolean antonymFetched) {
        this.antonymFetched = antonymFetched;
        if (!antonymFetched) {
            setWordAntonym(wordAntonym);
        }
    }

    public boolean isAntonymFetched() {
        return antonymFetched;
    }



    private void intializemeaning() {
        setWord(word);
        setWordMeaning(wordMeaning);

    }


    @Override
    public String toString() {
        return "Dictionary{" +
                "word='" + word + '\'' +
                ", wordMeaning='" + wordMeaning + '\'' +
                ", wordPartOfSpeech='" + wordPartOfSpeech + '\'' +
                ", wordExamoles='" + wordExample + '\'' +
                ", wordAntonyms='" + wordAntonym + '\'' +
                ", wordSynonms='" + wordSynonms + '\'' +
                ", wordSameContext='" + wordSameContext + '\'' +
                '}';
    }


    public void fetchWordMeaning(String mword, MainActivity activity) {
        this.setWord(mword.trim());
        mainActivity = activity;
        new DictionarywordsmeaningGetting().execute();
        new DictionarywordsExampleGetting().execute();
        new DictionarywordsRelatedWordsGetting().execute();
        new DictionarywordsPronunciationGetting().execute();

        Log.d("My TAg", "after Getwordmeaning call");

    }

    public void fetchWordMeaning(String mword, ScrollingDictionaryDetailActivity activity) {

        String s=mword;
        s=s.toLowerCase().trim();
        this.setWord(s);
        scrollingDictionaryDetailActivity = activity;
        new DictionarywordsExampleGetting().execute();

        new DictionarywordsmeaningGetting().execute();
        new DictionarywordsRelatedWordsGetting().execute();
        new DictionarywordsPronunciationGetting().execute();

        Log.d("My TAg", "after Getwordmeaning call");

    }

    public void fetchWordPronunciation() {
        setCalledForPronunciation(true);
        new DictionarywordsPronunciationGetting().execute();
        Log.d("My TAg", "after Getwordmeaning call");

    }

    public void fetchWordPronunciation(ScrollingActivity scrollingActivity) {
        calledbyScrollingWordoftheday=true;
        this.scrollingActivity=scrollingActivity;
        new DictionarywordsPronunciationGetting().execute();
        Log.d("wordofthedaypro","after receivingpronun");
    }


    public void processWordMeaning() {
    }

    public void completeFetching() {

        if (isMeaningFetched()&&isExampleFetched()&&isAntonymFetched()&&isPronunciationFetchedCheck()) {

            if (mainActivity != null) {
                mainActivity.updateDictionaryText(this);

            } else if (scrollingDictionaryDetailActivity != null) {
                scrollingDictionaryDetailActivity.updateDictionaryText(this);
            }
            Log.d("Tag", "completeFetching: " + toString());

            /*call activity method and inform dictionary mening done fetching*/
        }

    }


    private class DictionarywordsmeaningGetting extends AsyncTask<Void, Void, String> {


        public DictionarywordsmeaningGetting() {
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
            String text = null;

            // HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/words.json/randomWords?hasDictionaryDef=true&minCorpusCount=0&minLength=5&maxLength=15&limit=1&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");
            try {

                HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/word.json/" + getWord() + "/definitions?limit=10&includeRelated=true&useCanonical=false&includeTags=false&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");


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
                    JSONArray jsonArray = new JSONArray(results);
                    ArrayList<String> normalwordmeaningarr = new ArrayList<>();
                    ArrayList<String> normalwordpartofspeecharr = new ArrayList<>();

                   // setWord(jsonArray.getJSONObject(0).getString("word"));

                    for (int i = 0; i < jsonArray.length(); i++) {


                        if (jsonArray.getJSONObject(i).isNull("text") == false) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            normalwordmeaningarr.add(jsonObj.getString("text"));

                            if (jsonArray.getJSONObject(i).isNull("partOfSpeech") == false) {
                                normalwordpartofspeecharr.add(jsonObj.getString("partOfSpeech"));

                            }
                        }

                    }

                    setWordMeaning(normalwordmeaningarr);
                    //                  Log.d("wordMeaning", normalwordmeaningarr.get(0));
                    setWordPartOfSpeech(normalwordpartofspeecharr);
//                    Log.d("wordMeaning", normalwordpartofspeecharr.get(0));


                } catch (JSONException je) {
                    je.printStackTrace();
                }
                //et.setText(allKey);
                setMeaningFetched(true);
                completeFetching();
                Log.d("my text", "onPostExecute normal word meaning : Executed");
            } else {
                setMeaningFetched(false);
                completeFetching();
                // completeFetching();
                Log.d("my text", "onPostExecute meaning: failed to fetch meaning");
            }

        }


    }

    private class DictionarywordsExampleGetting extends AsyncTask<Void, Void, String> {


        public DictionarywordsExampleGetting() {
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
            String text = null;

            try {

                HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/word.json/" + getWord() + "/examples?includeDuplicates=false&useCanonical=false&skip=0&limit=5&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");

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
                    JSONObject jsonObject = new JSONObject(results);
                    JSONArray jsonArray = jsonObject.getJSONArray("examples");
                    setWord(jsonArray.getJSONObject(0).getString("word"));
                    ArrayList<String> mynormalwordExamplearrl = new ArrayList<>();


                    for (int i = 0; i < jsonArray.length(); i++) {

                        if (jsonArray.getJSONObject(i).isNull("text") == false) {
                            mynormalwordExamplearrl.add(jsonArray.getJSONObject(i).getString("text"));
                            Log.d("check example", jsonArray.getJSONObject(i).getString("text"));
                        }

                    }

                    setWordExample(mynormalwordExamplearrl);
                    Log.d("wordexample", mynormalwordExamplearrl.get(0));


                } catch (JSONException je) {
                    je.printStackTrace();
                }
                //et.setText(allKey);
                setExampleFetched(true);
                completeFetching();
                Log.d("my text", "onPostExecute normal word meaning : Executed");
            } else {
                setExampleFetched(false);
                 completeFetching();
                Log.d("my text", "onPostExecute synonyms: failed to fetch synonym");
            }

        }


    }

    private class DictionarywordsRelatedWordsGetting extends AsyncTask<Void, Void, String> {


        public DictionarywordsRelatedWordsGetting() {
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
            String text = null;

            try {
                HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/word.json/" + getWord() + "/relatedWords?useCanonical=false&limitPerRelationshipType=7&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");


                Log.d("My TAg", "doInBackground: going to call rest");
                HttpResponse response = httpClient.execute(httpGet, localContext);

                Log.d("My TAg", "doInBackground: done calling rest");
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
                Log.d("test text",text);

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

                    JSONArray jsonArray = new JSONArray(results);

                    ArrayList<String> mynormalRelatedwordAntonymarrl = new ArrayList<>();
                    ArrayList<String> mynormalRelatedwordSynonymarrl = new ArrayList<>();
                    ArrayList<String> mynormalRelatedwordSameContextarrl = new ArrayList<>();


                    for (int i = 0; i < jsonArray.length(); i++) {


                        JSONArray jsonArray1;
                        if (jsonArray.getJSONObject(i).getString("relationshipType").equalsIgnoreCase("antonym")) {

                            jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("words");
                            for (int k = 0; k < jsonArray1.length(); k++) {
                                mynormalRelatedwordAntonymarrl.add(jsonArray1.get(k) + "");
                            }
                            Log.d("check related words", jsonArray.getJSONObject(i).getJSONArray("words") + "");
                        }
                        if (jsonArray.getJSONObject(i).getString("relationshipType").equalsIgnoreCase("synonym")) {

                            jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("words");
                            for (int k = 0; k < jsonArray1.length(); k++) {
                                mynormalRelatedwordSynonymarrl.add(jsonArray1.get(k) + "");
                            }

                            Log.d("check related words", jsonArray.getJSONObject(i).getJSONArray("words") + "");
                        }
                        if (jsonArray.getJSONObject(i).getString("relationshipType").equalsIgnoreCase("same-context")) {

                            jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("words");
                            for (int k = 0; k < jsonArray1.length(); k++) {
                                mynormalRelatedwordSameContextarrl.add(jsonArray1.get(k) + "");
                            }

                            Log.d("check Samecontext", jsonArray.getJSONObject(i).getJSONArray("words") + "");
                        }


                    }

                    setWordAntonym(mynormalRelatedwordAntonymarrl);
                    setWordSynonms(mynormalRelatedwordSynonymarrl);
                    setWordSameContext(mynormalRelatedwordSameContextarrl);


                } catch (JSONException je) {
                    je.printStackTrace();
                }
                //et.setText(allKey);
                setAntonymFetched(true);
                 completeFetching();
                Log.d("my text", "onPostExecute normal word antonym : Executed");
            } else {
                setAntonymFetched(false);
                completeFetching();
                Log.d("my text", "onPostExecute antonym: failed to fetch antonym");
            }

        }


    }

    private class DictionarywordsPronunciationGetting extends AsyncTask<Void, Void, String> {


        public DictionarywordsPronunciationGetting() {
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
            String text = null;

            try {
                HttpGet httpGet = new HttpGet("http://api.wordnik.com:80/v4/word.json/" + getWord() + "/pronunciations?useCanonical=false&limit=1&api_key=8d93a189fb620cfa578070b02f8056778a640192bd39b10a4");


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

            isPronunciationFetched = true;
            if (results != null) {
                try {
                    Log.d("Tag", "onPostExecute: " + results);
                    JSONArray jsonArray = new JSONArray(results);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getJSONObject(i).isNull("raw") == false) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            setWordPronunciation(jsonObj.getString("raw"));
                        }

                    }
                    Log.d("prolist check", getWordPronunciation() + "");
                } catch (JSONException je) {
                    je.printStackTrace();
                }
                //et.setText(allKey);
                setPronunciationFetchedCheck(true);
                completeFetching();
                Log.d("my text pro", "onPostExecute normal word pronun : Executed");
            } else {
                setPronunciationFetchedCheck(false);
                completeFetching();
                Log.d("my text", "onPostExecute synonyms: failed to fetch synonym");
            }
            if (isCalledForPronunciation()) {

                callingMAin();
                isCalledForPronunciation = false;


            } else if (!isCalledForPronunciation()) {
                completeFetching();
                Log.d("compleexcution to main", "true");

            }else if(calledbyScrollingWordoftheday){
                calledbyScrollingWordoftheday=false;
                callingScrolling();

            }
        }


    }

    public void callingMAin() {
        mainActivity.updatePronunciation();
    }

    public void callingScrolling( ){
        scrollingActivity.getpronunciationofwordoftheday(this);
    }
}
