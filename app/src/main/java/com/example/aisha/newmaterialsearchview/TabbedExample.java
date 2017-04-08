package com.example.aisha.newmaterialsearchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Aisha on 3/26/2017.
 */

public class TabbedExample extends Fragment {

    TextView word_example_tv;
    ArrayList<String> exampleArraylist;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_example, container, false);
        View tempView=rootView;
        word_example_tv=(TextView)tempView.findViewById(R.id.word_example_tv);

        exampleArraylist=ScrollingActivity.callforSettingExampleTextView();
        for (int i=0;i<exampleArraylist.size();i++){
            String s=exampleArraylist.get(i);
            s=word_example_tv.getText()+"\n "+s;
            word_example_tv.setText(s);

        }
        return tempView;

    }


}
