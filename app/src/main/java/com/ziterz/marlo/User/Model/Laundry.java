package com.ziterz.marlo.User.Model;

/**
 * Created by ziterz on 7/12/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laundry implements Parcelable{

    @SerializedName("id_laundry")
    @Expose
    private Integer idLaundry;
    @SerializedName("id_provinsi")
    @Expose
    private Integer idProvinsi;
    @SerializedName("id_kota")
    @Expose
    private Integer idKota;
    @SerializedName("nama_laundry")
    @Expose
    private String namaLaundry;
    @SerializedName("no_telfon")
    @Expose
    private String noTelfon;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("directory_foto")
    @Expose
    private String directoryFoto;
    @SerializedName("nohp")
    @Expose
    private String nohp;
    @SerializedName("biaya_antar_jemput")
    @Expose
    private Integer biayaAntarJemput;

    public Integer getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(Integer idLaundry) {
        this.idLaundry = idLaundry;
    }

    public Integer getIdProvinsi() {
        return idProvinsi;
    }

    public void setIdProvinsi(Integer idProvinsi) {
        this.idProvinsi = idProvinsi;
    }

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public String getNoTelfon() {
        return noTelfon;
    }

    public void setNoTelfon(String noTelfon) {
        this.noTelfon = noTelfon;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDirectoryFoto() {
        return directoryFoto;
    }

    public void setDirectoryFoto(String directoryFoto) {
        this.directoryFoto = directoryFoto;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public Integer getBiayaAntarJemput() {
        return biayaAntarJemput;
    }

    public void setBiayaAntarJemput(Integer biayaAntarJemput) {
        this.biayaAntarJemput = biayaAntarJemput;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idLaundry);
        dest.writeValue(this.idProvinsi);
        dest.writeValue(this.idKota);
        dest.writeString(this.namaLaundry);
        dest.writeString(this.noTelfon);
        dest.writeString(this._long);
        dest.writeString(this.lat);
        dest.writeString(this.alamat);
        dest.writeString(this.namaPemilik);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.directoryFoto);
        dest.writeString(this.nohp);
        dest.writeValue(this.biayaAntarJemput);
    }

    public Laundry() {
    }

    protected Laundry(Parcel in) {
        this.idLaundry = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idProvinsi = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idKota = (Integer) in.readValue(Integer.class.getClassLoader());
        this.namaLaundry = in.readString();
        this.noTelfon = in.readString();
        this._long = in.readString();
        this.lat = in.readString();
        this.alamat = in.readString();
        this.namaPemilik = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.directoryFoto = in.readString();
        this.nohp = in.readString();
        this.biayaAntarJemput = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Laundry> CREATOR = new Creator<Laundry>() {
        @Override
        public Laundry createFromParcel(Parcel source) {
            return new Laundry(source);
        }

        @Override
        public Laundry[] newArray(int size) {
            return new Laundry[size];
        }
    };
}