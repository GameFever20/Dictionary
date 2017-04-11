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

public class TabbedDetailSameContext extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_detail_samecontext, container, false);
        View tempView=rootView;

        TextView word_samecontext_detail_tv=(TextView) rootView.findViewById(R.id.word_samecontext_detail_tv);

        ArrayList<String> wordSameContext= ScrollingDictionaryDetailActivity.callforsamecontext();
        try {
            for (int i=0;i<wordSameContext.size();i++){

                word_samecontext_detail_tv.setText(word_samecontext_detail_tv.getText()+"\n * "+wordSameContext.get(i)+"\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
       // Log.d("tabbed word samecontext", wordSameContext.get(0));


        return tempView;
    }
}
