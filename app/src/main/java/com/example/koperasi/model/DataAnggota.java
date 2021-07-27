package com.example.koperasi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAnggota {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_anggota")
    @Expose
    private String namaAnggota;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("nomor_anggota")
    @Expose
    private String nomorAnggota;
    @SerializedName("alamat_anggota")
    @Expose
    private String alamatAnggota;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("foto_ktp")
    @Expose
    private String fotoKtp;
    @SerializedName("foto_selfie")
    @Expose
    private String fotoSelfie;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNomorAnggota() {
        return nomorAnggota;
    }

    public void setNomorAnggota(String nomorAnggota) {
        this.nomorAnggota = nomorAnggota;
    }

    public String getAlamatAnggota() {
        return alamatAnggota;
    }

    public void setAlamatAnggota(String alamatAnggota) {
        this.alamatAnggota = alamatAnggota;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(String fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

    public String getFotoSelfie() {
        return fotoSelfie;
    }

    public void setFotoSelfie(String fotoSelfie) {
        this.fotoSelfie = fotoSelfie;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
