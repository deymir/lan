package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment4 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr4;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr4 = (ListView) view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr4 = databaseAdapter.getDescriptionsByArea(3);
        if (listFr4 != null) {
            adapter = new LetterSelectionAdapter(getActivity(), listFr4);
            lvFr4.setAdapter(adapter);
        }
        return view;
    }

}
