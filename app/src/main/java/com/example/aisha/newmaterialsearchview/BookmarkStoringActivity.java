package com.example.aisha.newmaterialsearchview;

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

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

public class BookmarkStoringActivity extends AppCompatActivity {

    private BookmarkStoringActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ImageView fav_imageview;
    SearchView search_view_srcolling;

    private String pronunciation;
    TextToSpeech textToSpeech;
    TextView pronunciationTextView;


    //all tabs java class
    TabbedMeaningBookmark tabbedMeaningBookmark;
    TabbedExampleBookmark tabbedExampleBookmark;
    TabbedAntonymBookmark tabbedAntonymBookmark;
    TabbedSnonymBookmark tabbedSnonymBookmark;
    TabbedSameContextBookmark tabbedSameContextBookmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_storing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new BookmarkStoringActivity.SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        pronunciationTextView = (TextView) findViewById(R.id.pronunciationTextView);

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
                DatabaseHandlerBookmark databaseHandlerBookmark=new DatabaseHandlerBookmark(BookmarkStoringActivity.this);
              // ArrayList<String> s= databaseHandlerBookmark.getAllBookmarkDetailByName();

                Snackbar.make(view, "hey", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void textToSpeechcall(String word) {


        textToSpeech.speak(word,TextToSpeech.QUEUE_FLUSH,null);

        Log.d("speak",word);
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
                    tabbedMeaningBookmark = new TabbedMeaningBookmark();
                    return tabbedMeaningBookmark;
                case 1:
                    tabbedExampleBookmark = new TabbedExampleBookmark();
                    return tabbedExampleBookmark;
                case 2:
                    tabbedAntonymBookmark = new TabbedAntonymBookmark();
                    return tabbedAntonymBookmark;
                case 3:
                    tabbedSnonymBookmark = new TabbedSnonymBookmark();
                    return tabbedSnonymBookmark;
                case 4:
                    tabbedSameContextBookmark = new TabbedSameContextBookmark();
                    return tabbedSameContextBookmark;
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

}
