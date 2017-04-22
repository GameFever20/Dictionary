package com.example.aisha.newmaterialsearchview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class ScrollingActivity extends AppCompatActivity {

    private ScrollingActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ImageView fav_imageview;
    SearchView search_view_srcolling;
    TextView scrollingpronunciationtv;

    TabbedMeaning tabbedMeaning;
    TabbedDefination tabbedDefination;
    TabbedExample tabbedExample;

    TextToSpeech textToSpeech;
    String nameSet;


    static String meaningoftabbed = "";
    static ArrayList<String> exapmleslistoftabbed;
    static ArrayList<String> definitiontextlistoftabbed;
    static ArrayList<String> definitionpartofspeechlistoftabbed;

    //adview for banner add
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        nameSet = getIntent().getExtras().getString("Name");
        meaningoftabbed = getIntent().getExtras().getString("Meaning");
        exapmleslistoftabbed = getIntent().getExtras().getStringArrayList("ExampleArraylist");
        definitiontextlistoftabbed = getIntent().getExtras().getStringArrayList("DefinitionTextArraylist");
        definitionpartofspeechlistoftabbed = getIntent().getExtras().getStringArrayList("DefinitionPartOfSpeechArraylist");
        Log.d("arraylist example", exapmleslistoftabbed.get(0));
        Log.d("arraylistexample", definitiontextlistoftabbed.get(0));

        toolbar.setTitle(nameSet);
        setSupportActionBar(toolbar);

        // primary sections of the activity.
        mSectionsPagerAdapter = new ScrollingActivity.SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        fav_imageview = (ImageView) findViewById(R.id.fav_imageview);
        search_view_srcolling = (SearchView) findViewById(R.id.search_view_srcolling_word_of_the_day);
        scrollingpronunciationtv = (TextView) findViewById(R.id.scrollingpronunciationtv);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Dictionary dictionary = new Dictionary();
        dictionary.fetchWordPronunciation(this);



        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.UK);

            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                textToSpeechcall(nameSet);

                //search_view_srcolling.setQueryHint("search here");


            }
        });
        loadMyAds();

        search_view_srcolling.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchWord(query);
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    public void loadMyAds(){
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void SearchWord(String word){

        Intent intent = new Intent(ScrollingActivity.this, ScrollingDictionaryDetailActivity.class);
        intent.putExtra("WordName", word);
        startActivity(intent);


    }
    public void textToSpeechcall(String word) {


        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);

        Log.d("speak", word);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    tabbedMeaning = new TabbedMeaning();
                    return tabbedMeaning;
                case 1:
                    tabbedDefination = new TabbedDefination();
                    return tabbedDefination;
                case 2:
                    tabbedExample = new TabbedExample();
                    return tabbedExample;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Meaning";
                case 1:
                    return "Defination";
                case 2:
                    return "Example";
            }
            return null;
        }
    }

    public void addToFavouriteMethod(View view) {
        Toast.makeText(this, "Added to Favourite", Toast.LENGTH_SHORT).show();
    }

    public static String callforSettingMeaningTextView() {
        return meaningoftabbed;
    }

    public static ArrayList<String> callforSettingExampleTextView() {
        return exapmleslistoftabbed;
    }

    public static ArrayList<String> callforSettingDefinitionTextTextView() {
        return definitiontextlistoftabbed;
    }

    public static ArrayList<String> callforSettingDefinitionPartofspeechTextView() {
        return definitionpartofspeechlistoftabbed;
    }

    public void getpronunciationofwordoftheday(Dictionary dictionary) {

        scrollingpronunciationtv.setText(dictionary.getWordPronunciation());
        Log.d("received pronun", dictionary.getWordPronunciation());


    }

    @Override
    public void onBackPressed() {

        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        */
        super.onBackPressed();


    }

    @Override
    protected void onPause() {
        textToSpeech.stop();
        textToSpeech.shutdown();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        textToSpeech.stop();
        textToSpeech.shutdown();
        super.onDestroy();
    }
}
