package com.ziterz.marlo.User;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziterz.marlo.R;

public class FeedbackActivity extends AppCompatActivity {
    RelativeLayout feedback_button;
    TextView pesan_laundry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Typeface medium = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        pesan_laundry = (TextView) findViewById(R.id.pesan_laundry);
        pesan_laundry.setTypeface(medium);
        feedback_button = (RelativeLayout) findViewById(R.id.feedback_button);
        feedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackActivity.super.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.finish();
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
