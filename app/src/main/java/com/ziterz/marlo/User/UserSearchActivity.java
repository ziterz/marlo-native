package com.ziterz.marlo.User;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.ziterz.marlo.PrefManager;
import com.ziterz.marlo.User.API.ApiInterface;
import com.ziterz.marlo.User.API.LaundryAPI;
import com.ziterz.marlo.User.Item.LaundryData;
import com.ziterz.marlo.User.Adapter.LaundryAdapter;
import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Model.Laundries;
import com.ziterz.marlo.User.Model.Laundry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchActivity extends AppCompatActivity {

    private static final String TAG = UserSearchActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Laundry> laundryDataList;
    private LaundryAdapter adapter;
    FloatingActionButton floating;
    String address,detail,date,time;
    double longitude,latitude;

    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate ");
        recyclerView = (RecyclerView) findViewById(R.id.searchRecycle);
        address = getIntent().getStringExtra("address");
        detail = getIntent().getStringExtra("detail");
        longitude = getIntent().getDoubleExtra("longitude",0);
        latitude = getIntent().getDoubleExtra("latitude",0);
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");

        prefManager = new PrefManager(this);
        prefManager.setPrimaryAddress(address);
        prefManager.setDetailAddress(detail);
        prefManager.setDateOrder(date);
        prefManager.setTimeOrder(time);

        laundryDataList = new ArrayList<>();
        adapter = new LaundryAdapter(this, laundryDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
//        prepareLaundry();

        ApiInterface apiService =
                LaundryAPI.getClient().create(ApiInterface.class);

        Call<Laundries> call = apiService.getLaundry("l5cqGoxDxxtLHpo69vsOz3XCBA5vqSfzT1fptJWgP7HQ6MLcnCu3kAsgdN1P");
        call.enqueue(new Callback<Laundries>() {
            @Override
            public void onResponse(Call<Laundries> call, Response<Laundries> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                    Log.d("UserSearchActivity", "Gagal");
                }

                List<Laundry> laundryData = response.body().getLaundries();
                for (Laundry laundry:laundryData) {
                    double jarak = CalculationByDistance(longitude,latitude,Double.parseDouble(laundry.getLong()),Double.parseDouble(laundry.getLat()));
                    laundry.setJarak(jarak);
                }

                getJarak(laundryData);
                laundryDataList.addAll(laundryData);
                adapter.notifyDataSetChanged();
                Log.d("UserSearchActivity", "Nama Laundry: " + laundryData.get(0).getNamaLaundry());
            }

            @Override
            public void onFailure(Call<Laundries> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });

    }

    public double CalculationByDistance(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = latitudeA;
        double lat2 = latitudeB;
        double lon1 = latitudeA;
        double lon2 = latitudeB;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    private void getJarak(List<Laundry> laundryData) {
        // cari jarak

    }

    private void prepareLaundry() {
//        laundryDataList.add(new LaundryData("Twenty One LaundryData","Jl. Gegerkalong Tengah No.77 Bandung",4.4,"http://www.utsc.utoronto.ca/tcardplus/sites/utsc.utoronto.ca.tcardplus/files/styles/page-content-wide/public/LaundryData%20Live%20Page%20Image%20%282%29-01.png?itok=yppKcx_6","25"));
//        laundryDataList.add(new LaundryData("Khalifah LaundryData","Jl. Gegerkalong Girang No.71 Bandung",4.9,"https://i1.wp.com/www.seoblogaf.com/wp-content/uploads/2016/05/Tren-Baru-Bisnis-LaundryData-Syariah.jpg","61"));
//        laundryDataList.add(new LaundryData("Raja LaundryData","Jl. Gegerkalong Girang No.41 Bandung",4.5,"http://www.rd.com/wp-content/uploads/sites/2/2016/11/08_suprising_facts_laundry_macgyver_KasiaJanus.jpg","9"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_search,menu);
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
