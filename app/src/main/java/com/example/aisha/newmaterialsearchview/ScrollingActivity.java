package com.example.aisha.newmaterialsearchview;

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

public class ScrollingActivity extends AppCompatActivity {

    private ScrollingActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ImageView fav_imageview;
    SearchView search_view_srcolling;

    TabbedMeaning tabbedMeaning;
    TabbedDefination tabbedDefination;
    TabbedExample tabbedExample;

    static String meaningoftabbed="";
    static ArrayList<String> exapmleslistoftabbed;
    static ArrayList<String> definitiontextlistoftabbed;
    static ArrayList<String> definitionpartofspeechlistoftabbed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String nameSet=getIntent().getExtras().getString("Name");
        meaningoftabbed=getIntent().getExtras().getString("Meaning");
        exapmleslistoftabbed=getIntent().getExtras().getStringArrayList("ExampleArraylist");
        definitiontextlistoftabbed=getIntent().getExtras().getStringArrayList("DefinitionTextArraylist");
        definitionpartofspeechlistoftabbed=getIntent().getExtras().getStringArrayList("DefinitionPartOfSpeechArraylist");
        Log.d("arraylist example",exapmleslistoftabbed.get(0));
        Log.d("arraylistexample",definitiontextlistoftabbed.get(0));

        toolbar.setTitle(nameSet);
        setSupportActionBar(toolbar);

        // primary sections of the activity.
        mSectionsPagerAdapter = new ScrollingActivity.SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        fav_imageview=(ImageView)findViewById(R.id.fav_imageview);
        search_view_srcolling=(SearchView)findViewById(R.id.search_view_srcolling);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        search_view_srcolling.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScrollingActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        });

        search_view_srcolling.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ScrollingActivity.this, "Query is"+query, Toast.LENGTH_SHORT).show();
                search_view_srcolling.setQuery("",true);
                search_view_srcolling.clearFocus();
                search_view_srcolling.clearAnimation();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;            }
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

    public void addToFavouriteMethod(View view){
        Toast.makeText(this, "Added to Favourite", Toast.LENGTH_SHORT).show();
    }

    public static String callforSettingMeaningTextView(){
        return meaningoftabbed;
    }

    public static ArrayList<String> callforSettingExampleTextView(){
        return exapmleslistoftabbed;
    }

    public static ArrayList<String> callforSettingDefinitionTextTextView(){
        return definitiontextlistoftabbed;
    }

    public static ArrayList<String> callforSettingDefinitionPartofspeechTextView(){
        return definitionpartofspeechlistoftabbed;
    }


}
