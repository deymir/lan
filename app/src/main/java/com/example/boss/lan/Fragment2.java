package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr2;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr2 = (ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr2 = databaseAdapter.getDescriptionsByArea(1);
        if (lvFr2 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr2);
            lvFr2.setAdapter(adapter);
        }
        return view;
    }

}
