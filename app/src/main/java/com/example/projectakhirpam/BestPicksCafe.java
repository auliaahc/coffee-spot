package com.example.projectakhirpam;

public class BestPicksCafe {
    int bp_HomeGambar, gambar;

    String namaCafe, jamOperasional, alamat, deskripsi, kategori;

    public BestPicksCafe(int bp_HomeGambar, int gambar,  String namaCafe, String jamOperasional, String alamat, String deskripsi, String kategori) {

        this.bp_HomeGambar = bp_HomeGambar;
        this.gambar = gambar;
        this.namaCafe = namaCafe;
        this.jamOperasional = jamOperasional;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.kategori = kategori;
    }

    public BestPicksCafe() {}

    public int getbp_HomeGambar() {

        return bp_HomeGambar;
    }

    public void setbp_HomeGambar(int bp_HomeGambar) {

        this.bp_HomeGambar = bp_HomeGambar;
    }

    public int getGambar() {

        return gambar;
    }
    public void setGambar(int gambar) {

        this.gambar = gambar;
    }

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
    public String getDeskripsi() {

        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {

        this.deskripsi = deskripsi;
    }

    public String getKategori() {

        return kategori;
    }
    public void setKategori(String kategori) {

        this.kategori = kategori;
    }
}