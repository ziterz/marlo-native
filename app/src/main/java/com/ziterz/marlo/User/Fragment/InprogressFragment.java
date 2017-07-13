package com.ziterz.marlo.User.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Adapter.InprogressAdapter;
import com.ziterz.marlo.User.Item.Inprogress;

/**
 * A simple {@link Fragment} subclass.
 */
public class InprogressFragment extends Fragment {
    TextView harga_order,title1;
    private ListView listView;
    ArrayList<Inprogress> inprogressArrayList;

    public InprogressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_inprogress, container, false);

        listView = (ListView) view.findViewById(R.id.inprogress_list);
        inprogressArrayList = new ArrayList<>();
        inprogressArrayList.add(new Inprogress("Khalifah LaundryData",24000,"Pesanan Diambil","Kiloan Biasa (4kg)"));

        InprogressAdapter adapter = new InprogressAdapter(getContext(),inprogressArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    };

}
