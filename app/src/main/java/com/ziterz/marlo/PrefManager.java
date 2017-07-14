package com.ziterz.marlo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ziterz on 6/24/2017.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "marlo";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String NAMA_LENGKAP = "nama_lengkap";
    private static final String NO_HP = "no_hp";

    private static final String PRIMARY_ADDRESS = "address";
    private static final String DETAIL_ADDRESS = "detail";
    private static final String DATE_ORDER = "date";
    private static final String TIME_ORDER = "time";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setNamaLengkap(String name) {
        editor.putString(NAMA_LENGKAP, name);
        editor.commit();
    }

    public String getNamaLengkap() {
        return pref.getString(NAMA_LENGKAP, "-");
    }

    public void setNoHp(String hp) {
        editor.putString(NO_HP, hp);
        editor.commit();
    }

    public String getNoHp() {
        return pref.getString(NO_HP, "-");
    }

    public void setPrimaryAddress(String primaryAddress) {
        editor.putString(PRIMARY_ADDRESS, primaryAddress);
        editor.commit();
    }
    public String getPrimaryAddress() { return pref.getString(PRIMARY_ADDRESS, "-");}

    public void setDetailAddress(String primaryAddress) {
        editor.putString(DETAIL_ADDRESS, primaryAddress);
        editor.commit();
    }
    public String getDetailAddress() { return pref.getString(DETAIL_ADDRESS, "-");}

    public void setDateOrder(String primaryAddress) {
        editor.putString(DATE_ORDER, primaryAddress);
        editor.commit();
    }
    public String getDateOrder() { return pref.getString(DATE_ORDER, "-");}

    public void setTimeOrder(String primaryAddress) {
        editor.putString(TIME_ORDER, primaryAddress);
        editor.commit();
    }
    public String getTimeOrder() { return pref.getString(TIME_ORDER, "-");}
}
