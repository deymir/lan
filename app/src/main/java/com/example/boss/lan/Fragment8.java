package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment8 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr8;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr8 =(ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr8 = databaseAdapter.getDescriptionsByArea(7);
        if (listFr8 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr8);
            lvFr8.setAdapter(adapter);
        }
        return view;
    }

}
