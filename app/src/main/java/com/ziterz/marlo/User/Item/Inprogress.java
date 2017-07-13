package com.ziterz.marlo.User.Item;

/**
 * Created by ziterz on 6/14/2017.
 */

public class Inprogress {
    String namaLaundry;
    Integer hargaOrder;
    String statusOrder;
    String detailOrder;

    public Inprogress(String namaLaundry, Integer hargaOrder, String statusOrder, String detailOrder) {
        this.namaLaundry = namaLaundry;
        this.hargaOrder = hargaOrder;
        this.statusOrder = statusOrder;
        this.detailOrder = detailOrder;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public Integer getHargaOrder() {
        return hargaOrder;
    }

    public void setHargaOrder(Integer hargaOrder) {
        this.hargaOrder = hargaOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getDetailOrder() {
        return detailOrder;
    }

    public void setDetailOrder(String detailOrder) {
        this.detailOrder = detailOrder;
    }
}
