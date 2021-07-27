package com.example.koperasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAngsuran {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_anggota")
    @Expose
    private Integer idAnggota;
    @SerializedName("jumlah_angsuran")
    @Expose
    private String jumlahAngsuran;
    @SerializedName("tanggal_bayar")
    @Expose
    private String tanggalBayar;
    @SerializedName("periode_angsuran")
    @Expose
    private String periodeAngsuran;
    @SerializedName("sisa")
    @Expose
    private String sisa;
    @SerializedName("denda")
    @Expose
    private String denda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(Integer idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getJumlahAngsuran() {
        return jumlahAngsuran;
    }

    public void setJumlahAngsuran(String jumlahAngsuran) {
        this.jumlahAngsuran = jumlahAngsuran;
    }

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public String getPeriodeAngsuran() {
        return periodeAngsuran;
    }

    public void setPeriodeAngsuran(String periodeAngsuran) {
        this.periodeAngsuran = periodeAngsuran;
    }

    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

}