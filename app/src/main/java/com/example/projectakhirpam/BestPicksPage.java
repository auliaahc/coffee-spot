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

        bestPicksCafe.add(new Cafe("Ada Apa Dengan Kopi (AADK)", "24 Jam", "Malang"));
        bestPicksCafe.add(new Cafe("Robucca", "24 Jam", "Malang"));
        bestPicksCafe.add(new Cafe("Pesen Kopi Plus", "24 Jam", "Malang"));
        bestPicksCafe.add(new Cafe("Handall Coffee", "24 Jam", "Malang"));

        BestPickAdapter = new BestPickAdapter(this, bestPicksCafe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BestPicksPage.this);
        recyclerCafe.setLayoutManager(layoutManager);
    }
}

