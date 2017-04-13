package com.example.aisha.newmaterialsearchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aisha on 4/10/2017.
 */

public class TabbedSameContextBookmark extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tabbed_samecontext_bookmark, container, false);
        View tempView=rootView;


//        Log.d("tabbed word Antonym", wordAntonym.get(0));


        return tempView;
    }
}
