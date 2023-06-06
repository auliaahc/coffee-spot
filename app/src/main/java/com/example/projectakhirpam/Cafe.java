package com.example.projectakhirpam;

public class Cafe {
    String namaCafe, jamOperasional, alamat, gambar;

    public Cafe(String namaCafe, String jamOperasional, String alamat, String gambar) {
        this.namaCafe = namaCafe;
        this.jamOperasional = jamOperasional;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public Cafe() {}
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
    public String getGambar() {

        return gambar;
    }
    public void setGambar(String gambar) {

        this.gambar = gambar;
    }
}
