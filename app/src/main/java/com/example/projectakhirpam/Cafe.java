package com.example.projectakhirpam;

public class Cafe {
    String namaCafe, jamOperasional, alamat;

    public Cafe(String namaCafe, String jamOperasional, String alamat) {
        this.namaCafe = namaCafe;
        this.jamOperasional = jamOperasional;
        this.alamat = alamat;
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
}
