package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr3;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr3 = (ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr3 = databaseAdapter.getDescriptionsByArea(2);
        if (listFr3 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr3);
            lvFr3.setAdapter(adapter);
        }
        return view;
    }

}
