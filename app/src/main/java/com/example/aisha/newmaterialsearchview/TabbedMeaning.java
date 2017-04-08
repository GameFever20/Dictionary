package com.example.aisha.newmaterialsearchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Aisha on 3/26/2017.
 */

public class TabbedMeaning extends Fragment {

    TextView word_meaning_tv;
    String meaning="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_meaning, container, false);
        View tempView = rootView;
        meaning = ScrollingActivity.callforSettingMeaningTextView();
        Log.d("check meaning in tabbed",meaning+"");

        word_meaning_tv = (TextView) tempView.findViewById(R.id.word_meaning_tv);
        word_meaning_tv.setText(meaning);
        return tempView;

    }


}
