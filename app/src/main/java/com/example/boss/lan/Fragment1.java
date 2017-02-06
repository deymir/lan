package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr1;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr1 = (ListView) view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr1 = databaseAdapter.getDescriptionsByArea(8);
        if (lvFr1 != null) {
            adapter = new LetterSelectionAdapter(getActivity(), listFr1);
            lvFr1.setAdapter(adapter);
        }

        return view;
    }
}
