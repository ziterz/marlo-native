package com.ziterz.marlo.User;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Fragment.HistoryFragment;

import static android.R.attr.button;

public class UserAddressActivity extends AppCompatActivity implements OnMapReadyCallback,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener   {

    double latitude,longitude;

    TextView text_address,detail_address,text_date,text_time,pesan_laundry;
    RelativeLayout detail_order_btn;
    private GoogleMap mMap;
    List<Address> addresses;
    private UiSettings mUiSettings;
    Geocoder geocoder;


    private static final int MY_LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int LOCATION_LAYER_PERMISSION_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);
        text_address = (TextView) findViewById(R.id.text_address);
        detail_address = (TextView) findViewById(R.id.detail_address);
        text_date = (TextView) findViewById(R.id.text_date);
        text_time = (TextView) findViewById(R.id.text_time);
        detail_order_btn = (RelativeLayout) findViewById(R.id.detail_order_btn);
        pesan_laundry = (TextView) findViewById(R.id.pesan_laundry);
        Typeface medium = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        pesan_laundry.setTypeface(medium);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        detail_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UserAddresActivity", "onClick:" + text_address.getText().toString() + "." + text_address.getText().toString().isEmpty());

                if(text_address.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Masukan alamat",Toast.LENGTH_SHORT).show();
                    text_address.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(text_address,InputMethodManager.SHOW_IMPLICIT);
                } else if(detail_address.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Masukan detail alamat",Toast.LENGTH_SHORT).show();
                    detail_address.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(detail_address,InputMethodManager.SHOW_IMPLICIT);
                } else if(text_date.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Masukan tanggal penjemputan",Toast.LENGTH_SHORT).show();
                    text_date.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(text_date,InputMethodManager.SHOW_IMPLICIT);
                } else if(text_time.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Masukan jam penjemputan",Toast.LENGTH_SHORT).show();
                    text_time.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(text_time,InputMethodManager.SHOW_IMPLICIT);
                }else {
                    Intent intent = new Intent(getBaseContext(),UserSearchActivity.class);
                    intent.putExtra("address",text_address.getText().toString());
                    intent.putExtra("detail",detail_address.getText().toString());
                    intent.putExtra("longitude",longitude);
                    intent.putExtra("latitude",latitude);
                    intent.putExtra("date",text_date.getText().toString());
                    intent.putExtra("time",text_time.getText().toString());
                    startActivity(intent);
                }

            }
        });
        Calendar now = Calendar.getInstance();
        String tgl = "" + now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.MONTH) + "/" + now.get(Calendar.YEAR);
        text_date.setHint(tgl);
        String jam = "" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
        text_time.setHint(jam);
        text_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        UserAddressActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.colorMarlo));
                dpd.setMinDate(now);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        text_time.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        UserAddressActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.setAccentColor(getResources().getColor(R.color.colorMarlo));
                tpd.setMinTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),now.get(Calendar.SECOND));
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        geocoder = new Geocoder(this,Locale.getDefault());
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mUiSettings.setMyLocationButtonEnabled(true);
        } else {
            requestLocationPermission(MY_LOCATION_PERMISSION_REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            PermissionUtils.requestPermission(this, LOCATION_LAYER_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
        mUiSettings.setZoomControlsEnabled(true);
        final double Lat = -6.861294;
        final double Lon = 107.5907615;
        final CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(Lat, Lon))
                .zoom(15)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition arg0) {
                LatLng latvalue = arg0.target;
                latitude = latvalue.latitude;
                longitude = latvalue.longitude;

                try {
                    Geocoder geo = new Geocoder(getBaseContext().getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(latitude, longitude, 1);
                    if (addresses.isEmpty()) {
                        text_address.setText("Menunggu lokasi");
                    }
                    else {
                        if (addresses.size() > 0) {
                            text_address.setText(
                                    addresses.get(0).getAddressLine(0) + ", " +
                                    addresses.get(0).getSubLocality() + ", " +
                                    addresses.get(0).getLocality() +", " +
                                    addresses.get(0).getSubAdminArea()+ ", " +
                                    addresses.get(0).getAdminArea());
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }


            }
        });
    }

    public void requestLocationPermission(int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Display a dialog with rationale.
            PermissionUtils.RationaleDialog
                    .newInstance(requestCode, false).show(
                    getSupportFragmentManager(), "dialog");
        } else {
            // Location permission has not been granted yet, request it.
            PermissionUtils.requestPermission(this, requestCode,
                    Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        text_date.setText(date);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
        String minuteString = minute < 10 ? "0"+minute : ""+minute;
        String time = hourString+":"+minuteString;
        text_time.setText(time);
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
