package com.ziterz.marlo.User.Model;

/**
 * Created by ziterz on 7/12/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laundries {

    @SerializedName("data")
    @Expose
    private List<Laundry> data = null;

    public List<Laundry> getLaundries() {
        return data;
    }

    public void setLaundries(List<Laundry> data) {
        this.data = data;
    }

}
