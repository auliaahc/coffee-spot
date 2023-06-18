package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailCafePage extends AppCompatActivity {

    TextView tvDetailNama, tvDetailJam, tvDetailAlamat, tvDetailDeskripsi, tvDetailKategori;
    ImageButton backButton;
    ImageView detailGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cafe);

        backButton = findViewById(R.id.ib_back);
        tvDetailNama = findViewById(R.id.nama_detail);
        tvDetailJam = findViewById(R.id.jamOperasional_detail);
        tvDetailAlamat = findViewById(R.id.alamat_detail);
        tvDetailDeskripsi = findViewById(R.id.deskripsi_detail);
        tvDetailKategori = findViewById(R.id.category_detail);
        detailGambar = findViewById(R.id.imageView);

//        get intent extra
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String getCDetailNama = bundle.getString("cDetailNama");
            String getCDetailJam = bundle.getString("cDetailJam");
            String getCDetailAlamat = bundle.getString("cDetailAlamat");
            String getCDetailDeskripsi = bundle.getString("cDetailDeskripsi");
            String getCDetailKategori = bundle.getString("cDetailKategori");
            int getCDetailGambar = bundle.getInt("cDetailGambar");

            tvDetailNama.setText(getCDetailNama);
            tvDetailJam.setText(getCDetailJam);
            tvDetailAlamat.setText(getCDetailAlamat);
            tvDetailDeskripsi.setText(getCDetailDeskripsi);
            tvDetailKategori.setText(getCDetailKategori);
            detailGambar.setImageResource(getCDetailGambar);

            backButton.setOnClickListener(v -> {
                setResult(RESULT_OK, null);
                finish();
            });
        }
    }
}