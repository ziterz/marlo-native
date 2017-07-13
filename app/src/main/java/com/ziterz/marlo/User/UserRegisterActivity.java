package com.ziterz.marlo.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.ziterz.marlo.R;

public class UserRegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText editTextEmail;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private Button buttonSignup;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.email_signup);
        editTextPassword1 = (EditText) findViewById(R.id.password_signup);
        editTextPassword2 = (EditText) findViewById(R.id.password_signup2);
        buttonSignup = (Button) findViewById(R.id.button_signup);


        progressDialog = new ProgressDialog(this);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();
        String password1 = editTextPassword2.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukan email anda",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password) && TextUtils.isEmpty(password1)){
            Toast.makeText(this,"Masukan kata sandi anda",Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.equals(password1)){
            Toast.makeText(this,"Ulangi kata sandi anda",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Sedang mendaftarkan...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserRegisterActivity.this,"Berhasil daftar akun",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(UserRegisterActivity.this, UserHomeActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(UserRegisterActivity.this,"Gagal daftar akun",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.finish();
        }
        return true;
    }
}
