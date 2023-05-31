package com.example.projectakhirpam;

public class Cafes {
    String namaCafe, jamOperasional, alamat, kategori, deskripsi;

    public Cafes(String namaCafe, String jamOperasional, String alamat, String kategori, String deskripsi) {
        this.namaCafe = namaCafe;
        this.jamOperasional = jamOperasional;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
    }

    public Cafes() {}
    public String getNamaCafe() {
        return namaCafe;
    }
    public void setNamaCafe(String namaCafe) {
        this.namaCafe = namaCafe;
    }
    public String getJamOperasional() {
        return jamOperasional;
    }
    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getKategori() {
        return kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
