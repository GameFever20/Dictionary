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

public class TabbedDetailSynonms extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_detail_synonym, container, false);
        View tempView=rootView;

        TextView word_synonym_detail_tv=(TextView) rootView.findViewById(R.id.word_synonym_detail_tv);

        ArrayList<String> wordSynonym=ScrollingDictionaryDetailActivity.callforsynonym();
        try {
            for (int i=0;i<wordSynonym.size();i++){

                word_synonym_detail_tv.setText(word_synonym_detail_tv.getText()+"\n * "+wordSynonym.get(i)+"\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
         // Log.d("tabbed word Synonym", wordSynonym.get(0));


        return tempView;
    }
}
