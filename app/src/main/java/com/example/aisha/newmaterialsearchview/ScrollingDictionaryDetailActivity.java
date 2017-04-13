package com.example.aisha.newmaterialsearchview;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ScrollingDictionaryDetailActivity extends AppCompatActivity {


        private ScrollingDictionaryDetailActivity.SectionsPagerAdapter mSectionsPagerAdapter;
        private ViewPager mViewPager;
        ImageView fav_imageview;
        SearchView search_view_srcolling;

        //all tabs java class
        TabbedDetailDefination tabbedDetailDefination;
        TabbedDetailExample tabbedDetailExample;
        TabbedDetailAntonym tabbedDetailAntonym;
        TabbedDetailSynonms tabbedDetailSynonms;
        TabbedDetailSameContext tabbedDetailSameContext;

    private String word;
    TextView pronunciationTextView;

    int requestcode = 1;


    private String pronunciation;
    private MainActivity mainActivity;
    Dictionary dictionary;
    Toolbar toolbar;
    TextToSpeech textToSpeech;

    private static ArrayList<String> wordMeaning = new ArrayList<>();
    private static ArrayList<String> wordPartOfSpeech = new ArrayList<>();
    private static ArrayList<String> wordExample = new ArrayList<>();
    private static ArrayList<String> wordSameContext = new ArrayList<>();
    private static ArrayList<String> wordSynonms = new ArrayList<>();
    private static ArrayList<String> wordAntonym = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_dictionary_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setWord(getIntent().getExtras().getString("WordName"));


        // setWordMeaning(getIntent().getExtras().getStringArrayList("WordMeaning"));
        //setWordExample(getIntent().getExtras().getStringArrayList("WordExample"));
        //setWordPartOfSpeech(getIntent().getExtras().getStringArrayList("WordPartOfSpeech"));
        //setWordAntonym(getIntent().getExtras().getStringArrayList("WordAntonym"));
        //setWordSynonms(getIntent().getExtras().getStringArrayList("WordSynonym"));
        //setWordSameContext(getIntent().getExtras().getStringArrayList("WordSameContext"));
        //setPronunciation(getIntent().getExtras().getString("WordPronunciation"));

//         Log.d("check word meaning", getIntent().getExtras().getStringArrayList("WordMeaning").size()+"");

        toolbar.setTitle(getWord());
        setSupportActionBar(toolbar);


        // primary sections of the activity.
        mSectionsPagerAdapter = new ScrollingDictionaryDetailActivity.SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

      //  Dictionary dictionary = new Dictionary();
        //dictionary.fetchWordMeaning(getWord(), ScrollingDictionaryDetailActivity.this);

        callingDictionaryforResults(getWord());

        fav_imageview = (ImageView) findViewById(R.id.fav_imageview);
        search_view_srcolling = (SearchView) findViewById(R.id.search_view_srcolling);
        pronunciationTextView = (TextView) findViewById(R.id.pronunciationTextView);
        pronunciationTextView.setText(getPronunciation());


        search_view_srcolling.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScrollingDictionaryDetailActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        });

        search_view_srcolling.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

              /*
                setWord(query);
                Dictionary dictionary = new Dictionary();
                dictionary.fetchWordMeaning(query, ScrollingDictionaryDetailActivity.this);
                */
                callingDictionaryforResults(query);

                //mainActivity.gettingWordCallingDictionary(query);
                Toast.makeText(ScrollingDictionaryDetailActivity.this, "Query is " + query, Toast.LENGTH_SHORT).show();
                search_view_srcolling.setQuery("", true);
                search_view_srcolling.clearFocus();
                search_view_srcolling.clearAnimation();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });


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
                //Snackbar.make(view,getWord() , Snackbar.LENGTH_LONG).setAction("Action", null).show();
                textToSpeechcall(getWord());

            }
        });
    }

    public void textToSpeechcall(String word) {


        textToSpeech.speak(word,TextToSpeech.QUEUE_FLUSH,null);

        Log.d("speak",word);
    }

    public void addToFavouriteMethod(View view) {

        DatabaseHandlerBookmark databaseHandlerBookmark=new DatabaseHandlerBookmark(this);
        databaseHandlerBookmark.addToBookMark(dictionary);
        Toast.makeText(this, "Added to Favourite", Toast.LENGTH_SHORT).show();
    }
    public void callingDictionaryforResults(String word){
        setWord(word);
        Dictionary dictionary = new Dictionary();
        dictionary.fetchWordMeaning(word, ScrollingDictionaryDetailActivity.this);
        Log.d("calling dictionary",word);


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
                    tabbedDetailDefination = new TabbedDetailDefination();
                    return tabbedDetailDefination;
                case 1:
                    tabbedDetailExample = new TabbedDetailExample();
                    return tabbedDetailExample;
                case 2:
                    tabbedDetailAntonym = new TabbedDetailAntonym();
                    return tabbedDetailAntonym;
                case 3:
                    tabbedDetailSynonms = new TabbedDetailSynonms();
                    return tabbedDetailSynonms;
                case 4:
                    tabbedDetailSameContext = new TabbedDetailSameContext();
                    return tabbedDetailSameContext;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Meaning";
                case 1:
                    return "Example";
                case 2:
                    return "Antonyms";
                case 3:
                    return "Synonms";
                case 4:
                    return "Same-Context";

            }
            return null;
        }
    }

    public ScrollingDictionaryDetailActivity() {

    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(ArrayList<String> wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public ArrayList<String> getWordPartOfSpeech() {
        return wordPartOfSpeech;
    }

    public void setWordPartOfSpeech(ArrayList<String> wordPartOfSpeech) {
        this.wordPartOfSpeech = wordPartOfSpeech;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ArrayList<String> getWordExample() {
        return wordExample;
    }

    public void setWordExample(ArrayList<String> wordExample) {
        this.wordExample = wordExample;
    }

    public ArrayList<String> getWordSameContext() {
        return wordSameContext;
    }

    public void setWordSameContext(ArrayList<String> wordSameContext) {
        this.wordSameContext = wordSameContext;
    }

    public ArrayList<String> getWordAntonym() {
        return wordAntonym;
    }

    public void setWordAntonym(ArrayList<String> wordAntonym) {
        this.wordAntonym = wordAntonym;
    }

    public ArrayList<String> getWordSynonms() {
        return wordSynonms;
    }

    public void setWordSynonms(ArrayList<String> wordSynonms) {
        this.wordSynonms = wordSynonms;
    }


    public static ArrayList<String> callformeaning() {
//     Log.d("in call", wordMeaning.size()+"");
        return wordMeaning;
    }

    public static ArrayList<String> callforexample() {
        return wordExample;
    }

    public static ArrayList<String> callforantonym() {
        return wordAntonym;
    }

    public static ArrayList<String> callforsynonym() {
        return wordSynonms;
    }

    public static ArrayList<String> callforsamecontext() {
        return wordSameContext;
    }

    public static ArrayList<String> callforpartofspeech() {
        return wordPartOfSpeech;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.stop();
    }

    public void updateDictionaryText(Dictionary dictionary) {

        setWord(dictionary.getWord());
        setWordMeaning(dictionary.getWordMeaning());
        setWordAntonym(dictionary.getWordAntonym());
        setWordSynonms(dictionary.getWordSynonms());
        setWordExample(dictionary.getWordExample());
        setWordPartOfSpeech(dictionary.getWordPartOfSpeech());
        setWordSameContext(dictionary.getWordSameContext());
        setPronunciation(dictionary.getWordPronunciation());
        Log.d("update scrolling", dictionary.getWord());

        this.dictionary=dictionary;

        // toolbar.setTitle(dictionary.getWord());
        getSupportActionBar().setTitle(getWord());
        setSupportActionBar(toolbar);

        pronunciationTextView.setText(getPronunciation());

        mSectionsPagerAdapter = new ScrollingDictionaryDetailActivity.SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }
}
