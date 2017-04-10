package com.example.aisha.newmaterialsearchview;

import android.app.ActivityOptions;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import br.com.mauker.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity {

    MaterialSearchView searchView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter recyclerAdapter;


    DictionaryWordReceivingClass dictionaryWordReceivingClass;
    TextView wordofthedayInListTextview;
    TextView meaningofthewordoftheday;

    Dictionaryrandomwords dictionaryrandomwords;


    ArrayList<Dictionary> myrandomWordsArraylist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dictionaryrandomwords = new Dictionaryrandomwords(MainActivity.this);
        dictionaryrandomwords.execute();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        wordofthedayInListTextview = (TextView) findViewById(R.id.wordofthedayInListTextview);
        meaningofthewordoftheday = (TextView) findViewById(R.id.meaningofthewordoftheday);

        Date d = new Date();
        String date = "" + (d.getYear() + "-" + d.getMonth() + "-" + d.getDay());
        dictionaryWordReceivingClass = new DictionaryWordReceivingClass(MainActivity.this, date);
        dictionaryWordReceivingClass.execute();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dictionary dictionary = new Dictionary();
                dictionary.fetchWordMeaning("efficient", MainActivity.this);


            }
        });
    }

    public void updateDictionaryText(Dictionary dictionary) {
        Log.d("inmainword", dictionary.getWord());
        Log.d("inmainmeanig", dictionary.getWordMeaning().get(0));
        Log.d("mainpartofspeech", dictionary.getWordPartOfSpeech().get(0));
        Log.d("mainexample", dictionary.getWordExample().get(0));
        Log.d("main antonym", dictionary.getWordAntonym().get(0));
        Log.d("main Synnym", dictionary.getWordSynonms().get(0));
        Log.d("main same contxt", dictionary.getWordSameContext().get(0));

        Intent intent = new Intent(this, ScrollingDictionaryDetailActivity.class);
        intent.putExtra("WordName", dictionary.getWord());
        intent.putExtra("WordMeaning", dictionary.getWordMeaning());
        intent.putExtra("WordExample", dictionary.getWordExample());
        intent.putExtra("WordPartOfSpeech", dictionary.getWordPartOfSpeech());
        intent.putExtra("WordAntonym", dictionary.getWordAntonym());
        intent.putExtra("WordSynonym", dictionary.getWordSynonms());
        intent.putExtra("WordSameContext", dictionary.getWordSameContext());
        startActivity(intent);

        Log.d("main check", dictionary.getWordMeaning().get(0));

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

        myrandomWordsArraylist=randomWordsArraylist;
        recyclerAdapter = new RecyclerAdapter(getBaseContext(), android.R.anim.slide_in_left, myrandomWordsArraylist);
        recyclerView.setAdapter(recyclerAdapter);


    }
    public void updatePronunciation(){

        for (int i=0;i<myrandomWordsArraylist.size();i++){
            if (!myrandomWordsArraylist.get(i).isPronunciationFetched()){
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
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
        }
        if (id == R.id.search_menu) {
            searchView.openSearch();
            searchViewHandlesMethod();
            searchTextHandleMethod();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
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
                Toast.makeText(MainActivity.this, "text is " + query, Toast.LENGTH_SHORT).show();
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

        int id = view.getId();

        Intent intent = new Intent(this, ScrollingActivity.class);

        if (id == R.id.wordofthedayInListTextview) {
            intent.putExtra("Name", wordofthedayInListTextview.getText().toString());
            intent.putExtra("Meaning", dictionaryWordReceivingClass.getNote());
            intent.putExtra("ExampleArraylist", dictionaryWordReceivingClass.getMyexamplelist());
            intent.putExtra("DefinitionTextArraylist", dictionaryWordReceivingClass.getMydefinitiontextlist());
            intent.putExtra("DefinitionPartOfSpeechArraylist", dictionaryWordReceivingClass.getMydefinitionpartofspeechlist());

        } else if (id == R.id.name_of_the_word_tv_cardview) {
            intent.putExtra("Name", RecyclerAdapter.recentWordsNameArraylist.get(3));
        }

        startActivity(intent);
    }
}
