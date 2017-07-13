package com.ziterz.marlo.User.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.ziterz.marlo.User.FeedbackActivity;
import com.ziterz.marlo.User.SyaratActivity;
import com.ziterz.marlo.User.LoginActivity;
import com.ziterz.marlo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    RelativeLayout detail_logout,feedback,syarat;
    TextView title_profil,title_icon_name,title_icon_email,title_icon_hp,title_icon_password,title_terms,title_syarat,title_feedback,logout;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_account, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        detail_logout = (RelativeLayout) view.findViewById(R.id.detail_logout);
        feedback = (RelativeLayout) view.findViewById(R.id.feedback);
        syarat = (RelativeLayout) view.findViewById(R.id.syarat);

        title_profil = (TextView) view.findViewById(R.id.title_profil);
        title_icon_name = (TextView) view.findViewById(R.id.title_icon_name);
        title_icon_email = (TextView) view.findViewById(R.id.title_icon_email);
        title_icon_hp = (TextView) view.findViewById(R.id.title_icon_hp);
        title_icon_password = (TextView) view.findViewById(R.id.title_icon_password);
        title_terms = (TextView) view.findViewById(R.id.title_terms);
        Typeface medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        title_profil.setTypeface(medium);
        title_terms.setTypeface(medium);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Pengaturan");
        detail_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Berhasil keluar",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        syarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SyaratActivity.class);
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),FeedbackActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.actionbar_akun, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
