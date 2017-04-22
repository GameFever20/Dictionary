package com.example.aisha.newmaterialsearchview;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.mauker.materialsearchview.MaterialSearchView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    MaterialSearchView searchView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter recyclerAdapter;


    DictionaryWordReceivingClass dictionaryWordReceivingClass;
    TextView wordofthedayInListTextview;
    TextView meaningofthewordoftheday;

    Dictionaryrandomwords dictionaryrandomwords;
    TextToSpeech textToSpeech;
    ImageButton textToSpeechbtnWordOftheday;

    //adview for banner add
    private AdView mAdView;


    ArrayList<Dictionary> myrandomWordsArraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (isInternetOn()) {
            dictionaryrandomwords = new Dictionaryrandomwords(MainActivity.this);
            dictionaryrandomwords.execute();
            Log.d("Internet check",true+"");

        }else{
            Toast.makeText(this, "CONNECT TO INTERNET", Toast.LENGTH_LONG).show();
      }

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        wordofthedayInListTextview = (TextView) findViewById(R.id.wordofthedayInListTextview);
        meaningofthewordoftheday = (TextView) findViewById(R.id.meaningofthewordoftheday);
        textToSpeechbtnWordOftheday = (ImageButton) findViewById(R.id.textToSpeechbtnWordOftheday);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        String sDate = year + "-" + month + "-" + day;
        Log.d("Todays date", sDate);
        if (isInternetOn()) {
            dictionaryWordReceivingClass = new DictionaryWordReceivingClass(MainActivity.this, sDate);
            dictionaryWordReceivingClass.execute();
            Log.d("Internet check",true+"");

        }else{
            Toast.makeText(this, "CONNECT TO INTERNET", Toast.LENGTH_LONG).show();
        }

        //Load adds in dictioanry
        loadMyAds();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Dictionary dictionary = new Dictionary();
                //dictionary.fetchWordMeaning("efficient", MainActivity.this);


                searchView.openSearch();
                searchTextHandleMethod();

                //    gettingWordCallingDictionary("efficient");

            }
        });

        textToSpeechbtnWordOftheday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeechcall(String.valueOf(wordofthedayInListTextview.getText()));
            }
        });

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.UK);

            }

        });
    }

    public void loadMyAds(){
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    public void textToSpeechcall(String word) {


        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);

        Log.d("speak", word);
    }

    public void gettingWordCallingDictionary(String Word) {
       /* Dictionary dictionary = new Dictionary();
        dictionary.fetchWordMeaning(Word, MainActivity.this);
        */

        Intent intent = new Intent(this, ScrollingDictionaryDetailActivity.class);
        intent.putExtra("WordName", Word);
        startActivity(intent);

    }

    public void updateDictionaryText(Dictionary dictionary) {
        Log.d("inmainword", dictionary.getWord());
        //Log.d("inmainmeanig", dictionary.getWordMeaning().get(0));
        //  Log.d("mainpartofspeech", dictionary.getWordPartOfSpeech().get(0));
        // Log.d("mainexample", dictionary.getWordExample().get(0));
//        Log.d("main antonym", dictionary.getWordAntonym().get(0));
        //      Log.d("main Synnym", dictionary.getWordSynonms().get(0));
        //    Log.d("main same contxt", dictionary.getWordSameContext().get(0));

        Intent intent = new Intent(this, ScrollingDictionaryDetailActivity.class);
        intent.putExtra("WordName", dictionary.getWord());
        intent.putExtra("WordMeaning", dictionary.getWordMeaning());
        intent.putExtra("WordExample", dictionary.getWordExample());
        intent.putExtra("WordPartOfSpeech", dictionary.getWordPartOfSpeech());
        intent.putExtra("WordAntonym", dictionary.getWordAntonym());
        intent.putExtra("WordSynonym", dictionary.getWordSynonms());
        intent.putExtra("WordSameContext", dictionary.getWordSameContext());
        intent.putExtra("WordPronunciation", dictionary.getWordPronunciation());
        startActivity(intent);

        Log.d("main check", dictionary.getWordPronunciation());

    }


    public void listenerandgetterwords() {

        Log.d("Tag", "onGettingWord " + dictionaryWordReceivingClass.getWord());

        wordofthedayInListTextview.setText(dictionaryWordReceivingClass.getWord());
        if (dictionaryWordReceivingClass.getNote().length() < 25) {
            meaningofthewordoftheday.setText(dictionaryWordReceivingClass.getNote());

        } else {
            meaningofthewordoftheday.setText(dictionaryWordReceivingClass.getNote().substring(0, 25));
        }
    }

    public void listenandgetrandomword(ArrayList<Dictionary> randomWordsArraylist) {

        myrandomWordsArraylist = randomWordsArraylist;
        recyclerAdapter = new RecyclerAdapter(this, android.R.anim.slide_in_left, myrandomWordsArraylist);
        recyclerView.setAdapter(recyclerAdapter);


    }

    public void updatePronunciation() {

        for (int i = 0; i < myrandomWordsArraylist.size(); i++) {
            if (!myrandomWordsArraylist.get(i).isPronunciationFetched()) {
                return;
            }
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_menu) {
            searchView.openSearch();
            // searchViewHandlesMethod();
            searchTextHandleMethod();
        }
        if (id==R.id.share_menu){
            shareAppViaMedia();
        }

        return super.onOptionsItemSelected(item);
    }
    public void shareAppViaMedia(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "https://play.google.com/store/apps/details?id=app.craftystudio.dictionary";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
        Log.d("Share app","Done!");

    }

    @Override
    public void onBackPressed() {
        if (searchView.isOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
        textToSpeech.stop();
    }

    public void searchViewHandlesMethod() {
        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                Toast.makeText(MainActivity.this, "opened", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSearchViewClosed() {
                Toast.makeText(MainActivity.this, "closed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void searchTextHandleMethod() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                /*
                Intent intent = new Intent(MainActivity.this, ScrollingDictionaryDetailActivity.class);
                intent.putExtra("WordName", query);
                startActivity(intent);
                */
                gettingWordCallingDictionary(query);
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();

                searchView.clearAll();
                searchView.closeSearch();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void callScrollingActivity(View view) {

        Intent intent = new Intent(this, ScrollingActivity.class);
        intent.putExtra("Name", dictionaryWordReceivingClass.getWord());
        intent.putExtra("Meaning", dictionaryWordReceivingClass.getNote());
        intent.putExtra("ExampleArraylist", dictionaryWordReceivingClass.getMyexamplelist());
        intent.putExtra("DefinitionTextArraylist", dictionaryWordReceivingClass.getMydefinitiontextlist());
        intent.putExtra("DefinitionPartOfSpeechArraylist", dictionaryWordReceivingClass.getMydefinitionpartofspeechlist());
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        textToSpeech.stop();
        textToSpeech.shutdown();
        super.onDestroy();
    }

    public boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {


            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


            return false;
        }
        return false;
    }
}

