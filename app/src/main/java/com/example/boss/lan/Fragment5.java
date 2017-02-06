package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment5 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr5;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr5 = (ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr5 = databaseAdapter.getDescriptionsByArea(4);
        if (listFr5 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr5);
            lvFr5.setAdapter(adapter);
        }
        return view;
    }

}
