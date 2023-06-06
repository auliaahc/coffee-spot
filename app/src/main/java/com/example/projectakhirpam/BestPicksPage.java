package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class BestPicksPage extends AppCompatActivity {
    private RecyclerView recyclerCafe;
    private ArrayList<Cafe> bestPicksCafe = new ArrayList<>();
    private BestPickAdapter BestPickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestpicks_page);

        recyclerCafe = findViewById(R.id.recyclerViewBestPicks);
        recyclerCafe.setHasFixedSize(true);

        ArrayList<a> aa = new ArrayList<>(R.drawable.aadk)

        bestPicksCafe.add(new Cafe("Ada Apa Dengan Kopi (AADK)", "24 Jam", "Malang", "https://drive.google.com/file/d/19p6wc6MYG6kNUJiGFE3z8xCAt1HkBkVr/view?usp=drive_link"));
        bestPicksCafe.add(new Cafe("Robucca", "24 Jam", "Malang", "https://drive.google.com/file/d/1zD9etzejTzVrPE4uHeTHcGU0Tj21mYGW/view?usp=drive_link"));
        bestPicksCafe.add(new Cafe("Pesen Kopi Plus", "24 Jam", "Malang", "https://drive.google.com/file/d/1wnF7GdgfbUn4S4Ngf9-ZicdRcgV9_Skd/view?usp=drive_link"));
        bestPicksCafe.add(new Cafe("Handall Coffee", "24 Jam", "Malang", "https://drive.google.com/file/d/1dirmelO_gyfOwkz5HzmEZeS9QFaVZE4O/view?usp=drive_link"));

        BestPickAdapter = new BestPickAdapter(this, bestPicksCafe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BestPicksPage.this);
        recyclerCafe.setLayoutManager(layoutManager);
    }
}

