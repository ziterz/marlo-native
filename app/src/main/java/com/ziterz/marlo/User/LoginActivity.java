package com.ziterz.marlo.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.ziterz.marlo.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private TextView descLogin;
    private Button buttonSignin;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RelativeLayout desc_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        editTextEmail = (EditText) findViewById(R.id.email_signin);
        editTextPassword = (EditText) findViewById(R.id.password_signin);
        desc_login = (RelativeLayout) findViewById(R.id.desc_login);
        buttonSignin = (Button) findViewById(R.id.button_signin);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

//        TextView tv = new TextView(getApplicationContext());
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//        tv.setLayoutParams(lp);
//        tv.setText("Daftar");
//        tv.setTextSize(20);
//        tv.setTextColor(this.getResources().getColor(R.color.colorAccent));
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
//        tv.setTypeface(tf);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(tv);

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: signed_in " + user.getEmail());
                    Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
            }
        };
        desc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), UserRegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void loginUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukan email anda",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Masukan kata sandi anda",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Mencoba untuk masuk...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Berhasil masuk",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"Gagal masuk",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, UserRegisterActivity.class);
                    startActivity(i);
                }
                progressDialog.dismiss();
            }
        });
    }

}
