package com.ziterz.marlo.User.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziterz.marlo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteFragment extends Fragment {

    TextView desc_blank;
    public CompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_complete, container, false);

        desc_blank = (TextView) view.findViewById(R.id.desc_blank);
        return view;
    }

}
