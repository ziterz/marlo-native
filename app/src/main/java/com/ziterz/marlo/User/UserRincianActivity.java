package com.ziterz.marlo.User;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziterz.marlo.PrefManager;
import com.ziterz.marlo.R;

public class UserRincianActivity extends AppCompatActivity {

    PrefManager prefManager;
    RelativeLayout selesai;
    TextView nama,hari,tanggal,jam,alamat,detail,title_nama,title_hari,title_tanggal,title_jam,title_alamat,title_detail,title_pesanan,total_harga,button_next_rincian;
    Button payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rincian);
        selesai = (RelativeLayout) findViewById(R.id.selesai);
        payment = (Button) findViewById(R.id.payment);
        button_next_rincian = (TextView) findViewById(R.id.button_next_rincian);
        nama = (TextView) findViewById(R.id.nama);
        hari = (TextView) findViewById(R.id.hari);
        tanggal = (TextView) findViewById(R.id.tanggal);
        jam = (TextView) findViewById(R.id.jam);
        alamat = (TextView) findViewById(R.id.alamat);
        detail = (TextView) findViewById(R.id.detail);
        title_nama = (TextView) findViewById(R.id.title_nama);
        title_hari = (TextView) findViewById(R.id.title_hari);
        title_tanggal = (TextView) findViewById(R.id.title_tanggal);
        title_jam = (TextView) findViewById(R.id.title_jam);
        title_alamat = (TextView) findViewById(R.id.title_alamat);
        title_detail = (TextView) findViewById(R.id.title_detail);
        title_pesanan = (TextView) findViewById(R.id.title_pesanan);
        total_harga = (TextView) findViewById(R.id.total_harga);
        Typeface medium = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        payment.setTypeface(medium);
        button_next_rincian.setTypeface(medium);
        title_nama.setTypeface(medium);
        title_hari.setTypeface(medium);
        title_tanggal.setTypeface(medium);
        title_jam.setTypeface(medium);
        title_alamat.setTypeface(medium);
        title_detail.setTypeface(medium);
        title_pesanan.setTypeface(medium);
        total_harga.setTypeface(medium);
        prefManager = new PrefManager(this);
        nama.setText(prefManager.getNamaLengkap());
        tanggal.setText(prefManager.getDateOrder());
        jam.setText(prefManager.getTimeOrder()+ "WIB");
        alamat.setText(prefManager.getPrimaryAddress());
        detail.setText(prefManager.getDetailAddress());
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),UserHomeActivity.class);
                intent.putExtra("viewpager_position", 0);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
