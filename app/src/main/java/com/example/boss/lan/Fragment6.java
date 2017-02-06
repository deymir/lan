package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment6 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr6;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr6 = (ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr6 = databaseAdapter.getDescriptionsByArea(5);
        if (listFr6 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr6);
            lvFr6.setAdapter(adapter);
        }
        return view;
    }

}
