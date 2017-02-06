package com.example.boss.lan;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment7 extends Fragment {

    DatabaseAdapter databaseAdapter;
    ListView lvFr7;
    LetterSelectionAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.global_fragment, container, false);

        databaseAdapter = new DatabaseAdapter(getActivity());
        databaseAdapter.open();

        lvFr7 = (ListView)view.findViewById(R.id.level_fragment);
        ArrayList<User> listFr7 = databaseAdapter.getDescriptionsByArea(6);
        if (listFr7 != null){
            adapter = new LetterSelectionAdapter(getActivity(), listFr7);
            lvFr7.setAdapter(adapter);
        }
        return view;
    }

}
