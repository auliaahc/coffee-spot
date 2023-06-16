package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerCafe, recyclerCafe2;
    private ArrayList<BestPicksCafe> BestPicksCafe = new ArrayList<>();
    private ArrayList<TopOffersCafe> TopOffersCafe = new ArrayList<>();
    private BestHomePageAdapter BestHomePageAdapter;
    private TopHomePageAdapter TopHomePageAdapter;
    private View IntentProfile;

    private TextView bpSee, toSee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        IntentProfile = findViewById(R.id.nav_profile);
        bpSee = findViewById(R.id.bp_see);
        toSee = findViewById(R.id.to_see);

        recyclerCafe = findViewById(R.id.rvHomePage);
        recyclerCafe.setHasFixedSize(true);

        recyclerCafe2 = findViewById(R.id.rvHomePage2);
        recyclerCafe2.setHasFixedSize(true);

        BestPicksCafe.add(new BestPicksCafe(R.drawable.aadk));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.robucca));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.pesenkopipluss));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.handall));

        TopOffersCafe.add(new TopOffersCafe(R.drawable.sejenakcafe));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.roketto));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.lumacafe));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.kedaipokamiami));

        BestHomePageAdapter = new BestHomePageAdapter(this, BestPicksCafe);
        BestHomePageAdapter = new BestHomePageAdapter(getApplicationContext(), BestPicksCafe);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        //recyclerCafe.setLayoutManager(layoutManager);
        recyclerCafe.setAdapter(BestHomePageAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCafe.setLayoutManager(layoutManager);

        TopHomePageAdapter = new TopHomePageAdapter(this, TopOffersCafe);
        TopHomePageAdapter = new TopHomePageAdapter(getApplicationContext(), TopOffersCafe);
        //RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(HomePage.this);
        //recyclerCafe.setLayoutManager(layoutManager2);
        recyclerCafe.setAdapter(TopHomePageAdapter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerCafe.setLayoutManager(layoutManager2);

        IntentProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, ProfileActivity.class);
            startActivity(intent);
        });

       bpSee.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, BestPicksPage.class);
            startActivity(intent);
        });

        toSee.setOnClickListener(v -> {
            Intent intent = new Intent(HomePage.this, TopOffersPage.class);
            startActivity(intent);
        });
}}

