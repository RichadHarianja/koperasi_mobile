package com.example.koperasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSimpanan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_anggota")
    @Expose
    private Integer idAnggota;
    @SerializedName("tanggal_simpan")
    @Expose
    private String tanggalSimpan;
    @SerializedName("jumlah_saldo")
    @Expose
    private String jumlahSaldo;

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

    public String getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(String tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }

    public String getJumlahSaldo() {
        return jumlahSaldo;
    }

    public void setJumlahSaldo(String jumlahSaldo) {
        this.jumlahSaldo = jumlahSaldo;
    }
}
