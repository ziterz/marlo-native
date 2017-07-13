package com.ziterz.marlo.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Fragment.AccountFragment;
import com.ziterz.marlo.User.Fragment.HistoryFragment;
import com.ziterz.marlo.User.Fragment.HomeFragment;

public class UserHomeActivity extends AppCompatActivity {
    private static final String TAG = UserHomeActivity.class.getSimpleName();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, homeFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    HistoryFragment historyFragment = new HistoryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, historyFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    AccountFragment accountFragment = new AccountFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, accountFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, homeFragment).addToBackStack(null).commit();

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);

        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.setTextVisibility(false);
        navigation.setIconsMarginTop(32);
        navigation.setIconSize(26,26);
        navigation.setItemHeight(144);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bundle extras = getIntent().getExtras();
        int position=1;
        if(extras != null) {
            Log.d(TAG, "onCreate: extras null");
            position = extras.getInt("viewpager_position");
        }
        if(position==0){
            Log.d(TAG, "onCreate: position 0");
            HistoryFragment historyFragment = new HistoryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, historyFragment)
                    .addToBackStack(null)
                    .commit();
            navigation.getMenu().getItem(1).setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
