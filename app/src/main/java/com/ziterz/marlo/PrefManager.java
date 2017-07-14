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
}
