package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class TopOffersPage extends AppCompatActivity {
    private RecyclerView recyclerCafe;
    private ArrayList<Cafe> topOffersCafe = new ArrayList<>();
    private TopOffersAdapter TopOffersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topoffers_page);

        recyclerCafe = findViewById(R.id.recyclerViewTopOffers);
        recyclerCafe.setHasFixedSize(true);

        topOffersCafe.add(new Cafe("Sejenak Cafe", "24 Jam", "Malang"));
        topOffersCafe.add(new Cafe("Roketto", "24 Jam", "Malang"));
        topOffersCafe.add(new Cafe("Luma Cafe", "24 Jam", "Malang"));
        topOffersCafe.add(new Cafe("Kedai Pok Ami-Ami", "24 Jam", "Malang"));

        TopOffersAdapter = new TopOffersAdapter(this, topOffersCafe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TopOffersPage.this);
        recyclerCafe.setLayoutManager(layoutManager);
    }
}