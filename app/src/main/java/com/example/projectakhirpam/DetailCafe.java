package com.example.projectakhirpam;

public class DetailCafe {
    String detailNamaCafe, detailJamOperasional, detailAlamat, detailKategori, detailDeskripsi;

    public DetailCafe(String detailNamaCafe, String detailJamOperasional, String detailAlamat, String detailKategori, String detailDeskripsi) {
        this.detailNamaCafe = detailNamaCafe;
        this.detailJamOperasional = detailJamOperasional;
        this.detailAlamat = detailAlamat;
        this.detailDeskripsi = detailDeskripsi ;
        this.detailKategori = detailKategori;
    }

    public DetailCafe() {}
    public String getdetailNamaCafe() {

        return detailNamaCafe;
    }
    public void setdetailNamaCafe(String detailNamaCafe) {

        this.detailNamaCafe = detailNamaCafe;
    }
    public String getdetailJamOperasional() {

        return detailJamOperasional;
    }
    public void setdetailJamOperasional(String detailJamOperasional) {

        this.detailJamOperasional = detailJamOperasional;
    }
    public String getDetailAlamat() {

        return detailAlamat;
    }
    public void setDetailAlamat(String detailAlamat) {

        this.detailAlamat = detailAlamat;
    }
    public String getdetailKategori() {

        return detailKategori;
    }
    public void setDetailKategori(String detailKategori) {

        this.detailKategori = detailKategori;
    }
    public String getDetailDeskripsi() {

        return detailDeskripsi;
    }
    public void setDetailDeskripsi(String detailDeskripsi) {

        this.detailDeskripsi = detailDeskripsi;
    }
}
