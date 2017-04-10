package com.example.aisha.newmaterialsearchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aisha on 4/10/2017.
 */

public class TabbedDetailDefination extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_detail_meaning, container, false);
        View tempView = rootView;

        TextView word_meaning_detail_tv=(TextView) rootView.findViewById(R.id.word_meaning_detail_tv);
        ArrayList<String> wordMeaning=ScrollingDictionaryDetailActivity.callformeaning();
        for (int i=0;i<wordMeaning.size();i++){
            word_meaning_detail_tv.setText(word_meaning_detail_tv.getText()+"\n * "+wordMeaning.get(i)+"\n");
        }
        Log.d("tabbed word", wordMeaning.get(0));


        return tempView;
    }
}
