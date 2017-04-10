package com.example.aisha.newmaterialsearchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ScollingDetailsActivity extends AppCompatActivity {


    private ScollingDetailsActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    ImageView fav_imageview;
    SearchView search_view_srcolling;

    //all tabs java class
    TabbedDetailDefination tabbedDetailDefination;
    TabbedDetailExample tabbedDetailExample;
    TabbedDetailAntonym tabbedDetailAntonym;
    TabbedDetailSynonms tabbedDetailSynonms;
    TabbedDetailSameContext tabbedDetailSameContext;


    //all requirements details
    private String word;
    private MainActivity mainActivity;
    private ArrayList<String> wordMeaning;
    private ArrayList<String> wordPartOfSpeech;
    private ArrayList<String> wordExample;
    private ArrayList<String> wordSameContext;
    private ArrayList<String> wordSynonms;
    private ArrayList<String> wordAntonym;


    public ScollingDetailsActivity(){

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scolling_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setWord(getIntent().getExtras().getString("WordName"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordMeaning"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordExample"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordPartOfSpeech"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordAntonym"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordSynonym"));
        setWordMeaning(getIntent().getExtras().getStringArrayList("WordSameContext"));

        toolbar.setTitle(getWord());
        setSupportActionBar(toolbar);

        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        fav_imageview = (ImageView) findViewById(R.id.fav_imageview);
        search_view_srcolling = (SearchView) findViewById(R.id.search_view_srcolling);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        search_view_srcolling.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScollingDetailsActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        });

        search_view_srcolling.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ScollingDetailsActivity.this, "Query is" + query, Toast.LENGTH_SHORT).show();
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //search_view_srcolling.setQueryHint("search here");


            }
        });

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
                    return "Defination";
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

