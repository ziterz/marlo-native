package com.ziterz.marlo.User.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class HistoryPagerAdapter extends FragmentStatePagerAdapter {

    int num;

    public HistoryPagerAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InprogressFragment();

            case 1:
                return new CompleteFragment();

            default:
                return new InprogressFragment();
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
