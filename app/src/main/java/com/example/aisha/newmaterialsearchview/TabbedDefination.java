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
 * Created by Aisha on 3/26/2017.
 */

public class TabbedDefination extends Fragment {

    TextView word_defination_tv;
    ArrayList<String> definitiontextarraylist;
    ArrayList<String> definitionpartofspeecharraylist;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_defination, container, false);

        View tempView=rootView;
        word_defination_tv=(TextView)tempView.findViewById(R.id.word_defination_tv);

        definitiontextarraylist=ScrollingActivity.callforSettingDefinitionTextTextView();
        definitionpartofspeecharraylist=ScrollingActivity.callforSettingDefinitionPartofspeechTextView();

        for (int i=0;i<definitiontextarraylist.size();i++){

            word_defination_tv.setText(word_defination_tv.getText()+"\n"+definitiontextarraylist.get(i)+"\n");
            if (definitionpartofspeecharraylist.size()>0){
                word_defination_tv.setText(word_defination_tv.getText()+"\n part of speech :"+definitionpartofspeecharraylist.get(i)+"\n");
            }else {
                Log.d("definition set","checking");
            }
        }

        return tempView;

    }
   }
