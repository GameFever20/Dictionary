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

public class TabbedDetailExample extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_detail_example, container, false);
        View tempView = rootView;
        TextView word_example_detail_tv = (TextView) rootView.findViewById(R.id.word_example_detail_tv);

        ArrayList<String> wordExample = ScrollingDictionaryDetailActivity.callforexample();
        for (int i = 0; i < wordExample.size(); i++) {

            word_example_detail_tv.setText(word_example_detail_tv.getText() + "\n * " + wordExample.get(i) + "\n");
        }
        Log.d("tabbed word example", wordExample.get(0));

        return tempView;
    }
}
