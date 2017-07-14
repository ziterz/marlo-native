package com.ziterz.marlo.User;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Model.Laundry;

public class UserDetailActivity extends AppCompatActivity {

    ImageView background;
    RelativeLayout order_detail;
    Button min1, plus1, min2, plus2, min3, plus3;
    TextView value1, value2, value3, total,total_title,pesan_sekarang,title_profil,text_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Laundry laundry = getIntent().getParcelableExtra("laundry");
        min1 = (Button) findViewById(R.id.button_min_1);
        plus1 = (Button) findViewById(R.id.button_plus_1);
        value1 = (TextView) findViewById(R.id.text_value_1);
        min2 = (Button) findViewById(R.id.button_min_2);
        plus2 = (Button) findViewById(R.id.button_plus_2);
        value2 = (TextView) findViewById(R.id.text_value_2);
        min3 = (Button) findViewById(R.id.button_min_3);
        plus3 = (Button) findViewById(R.id.button_plus_3);
        value3 = (TextView) findViewById(R.id.text_value_3);
        total = (TextView) findViewById(R.id.total);
        total_title = (TextView) findViewById(R.id.total_title);
        pesan_sekarang = (TextView) findViewById(R.id.pesan_sekarang);
        title_profil = (TextView) findViewById(R.id.title_profil);
        Typeface medium = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        total.setTypeface(medium);
        total.setText("0");
        total_title.setTypeface(medium);
        pesan_sekarang.setTypeface(medium);
        title_profil.setTypeface(medium);
        getSupportActionBar().setTitle(laundry.getNamaLaundry());
        order_detail = (RelativeLayout) findViewById(R.id.order_detail_button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        background = (ImageView) findViewById(R.id.background_detail);
        Picasso.with(this).load("http://marloapp.com"+laundry.getDirectoryFoto()).into(background);

        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total.toString().equals("0")){
                    Toast.makeText(getBaseContext(),"Pilih layanan",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getBaseContext(), UserRincianActivity.class);
                    startActivity(intent);
                }
            }
        });

        min1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value1.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if (nilai_awal == 0){
                    value1.setText("0");
                } else {
                    value1.setText(String.valueOf(nilai_awal-1));
                    total.setText(String.valueOf(total_harga-6000));
                }
            }
        });
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value1.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if(nilai_awal > 14){
                    Toast.makeText(getBaseContext(),"Maksimal 15 kg",Toast.LENGTH_SHORT).show();
                } else {
                    value1.setText(String.valueOf(nilai_awal+1));
                    total.setText(String.valueOf(total_harga+6000));
                }
            }
        });
        min2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value2.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if (nilai_awal == 0){
                    value2.setText("0");
                } else {
                    value2.setText(String.valueOf(nilai_awal-1));
                    total.setText(String.valueOf(total_harga-12000));
                }
            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value2.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if(nilai_awal > 14){
                    Toast.makeText(getBaseContext(),"Maksimal 15 kg",Toast.LENGTH_SHORT).show();
                } else {
                    value2.setText(String.valueOf(nilai_awal+1));
                    total.setText(String.valueOf(total_harga+12000));
                }
            }
        });
        min3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value3.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if (nilai_awal == 0){
                    value3.setText("0");
                } else {
                    value3.setText(String.valueOf(nilai_awal-1));
                    total.setText(String.valueOf(total_harga-15000));
                }
            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilai_awal = Integer.parseInt(value3.getText().toString());
                int total_harga = Integer.parseInt(total.getText().toString());
                if(nilai_awal > 14){
                    Toast.makeText(getBaseContext(),"Maksimal 15 kg",Toast.LENGTH_SHORT).show();
                } else {
                    value3.setText(String.valueOf(nilai_awal+1));
                    total.setText(String.valueOf(total_harga+15000));
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_detail,menu);
        return super.onCreateOptionsMenu(menu);
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
