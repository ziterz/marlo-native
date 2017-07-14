package com.ziterz.marlo.User.Fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.ziterz.marlo.PrefManager;
import com.ziterz.marlo.User.FeedbackActivity;
import com.ziterz.marlo.User.SyaratActivity;
import com.ziterz.marlo.User.UserLoginActivity;
import com.ziterz.marlo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private PrefManager prefManager;
    private FirebaseAuth firebaseAuth;
    RelativeLayout detail_logout,feedback,syarat;
    TextView title_profil, textViewName,title_icon_email, textViewHp,title_icon_password,title_terms,title_syarat,title_feedback,logout;
    EditText editTextName, editTextHp;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        prefManager = new PrefManager(getContext());

        View view = inflater.inflate(R.layout.fragment_user_account, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        detail_logout = (RelativeLayout) view.findViewById(R.id.detail_logout);
        feedback = (RelativeLayout) view.findViewById(R.id.feedback);
        syarat = (RelativeLayout) view.findViewById(R.id.syarat);

        title_profil = (TextView) view.findViewById(R.id.title_profil);
        textViewName = (TextView) view.findViewById(R.id.tv_name);
        title_icon_email = (TextView) view.findViewById(R.id.title_icon_email);
        textViewHp = (TextView) view.findViewById(R.id.tv_hp);
        title_icon_password = (TextView) view.findViewById(R.id.title_icon_password);
        title_terms = (TextView) view.findViewById(R.id.title_terms);
        editTextHp = (EditText) view.findViewById(R.id.edit_hp);
        editTextName = (EditText) view.findViewById(R.id.edit_name);

        Typeface medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        title_profil.setTypeface(medium);
        title_terms.setTypeface(medium);

        // Ambil data dari PrefManager
        textViewName.setText(prefManager.getNamaLengkap());
        textViewHp.setText(prefManager.getNoHp());

        textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewName.setVisibility(View.GONE);

                String namaLengkap = prefManager.getNamaLengkap();
                if (namaLengkap.equals("-")) {
                    namaLengkap = "";
                }

                editTextName.setText(namaLengkap);
                editTextName.setVisibility(View.VISIBLE);
            }
        });

        textViewHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewHp.setVisibility(View.GONE);

                String hp = prefManager.getNoHp();
                if (hp.equals("-"))
                    hp = "";

                editTextHp.setText(hp);
                editTextHp.setVisibility(View.VISIBLE);
            }
        });

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefManager.setNamaLengkap(s.toString());
            }
        });

        editTextHp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefManager.setNoHp(s.toString());
            }
        });

        editTextName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    editTextName.clearFocus();
                }

                return true;
            }
        });

        editTextHp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER)
                    editTextHp.clearFocus();

                return true;
            }
        });

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v.setVisibility(View.GONE);
                    textViewName.setText(prefManager.getNamaLengkap());
                    textViewName.setVisibility(View.VISIBLE);
                }
            }
        });

        editTextHp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v.setVisibility(View.GONE);
                    textViewHp.setText(prefManager.getNoHp());
                    textViewHp.setVisibility(View.VISIBLE);
                }
            }
        });

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Pengaturan");
        detail_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Berhasil keluar",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), UserLoginActivity.class);
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
