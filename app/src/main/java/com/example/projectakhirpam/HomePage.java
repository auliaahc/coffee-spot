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

    private ArrayList<Cafe> bestPicksCafe = new ArrayList<>();


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

        BestPicksCafe.add(new BestPicksCafe(R.drawable.aadk, R.drawable.aadk, "AADK", "07.00 - 00.00", "Jl. Bandung No.28, Penanggungan, Kec. Klojen, Malang","AADK Coffee & Eatery adalah salah satu brand coffee shop yang berdiri sejak tahun 2020. AADK tidak ingin menjadi sekadar tempat ngopi, namun selalu berkeinginan untuk turut menemani #KawanAADK menciptakan berbagai momen berarti. \n \nInstagram : @adaapadengankopi", "Best Picks"));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.robucca, R.drawable.robucca, "Robucca", "07.30 - 21.00", "Ijen Nirwana, Jl. Raya No.1A, Bareng, Kec. Klojen, Malang", "Coffee • Eatery • Co-working space. \n \nInstagram : @robucca.id", "Best Picks"));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.pesenkopipluss, R.drawable.pesenkopipluss, "Pesen Kopi Plus", "11.00 - 23.45", "Jl. Mayjend Panjaitan No.191, Penanggungan, Kec. Klojen, Malang", "Start your day with a scrumptious waffle adventure at #PesenkopiPlus!. \n \nInstagram : @pesenkopiplus", "Best Picks"));
        BestPicksCafe.add(new BestPicksCafe(R.drawable.handall, R.drawable.handall, "Handall Coffee", "06.00 - 22.00", "Jl. Semanggi Timur No.7, Jatimulyo, Kec. Lowokwaru, Malang", "The pursuit of brewing specialty coffee that you'll remember. #EveryoneCanBeHandall. \n \nInstagram : @handall.coffee", "Best Picks"));

        TopOffersCafe.add(new TopOffersCafe(R.drawable.sejenakcafe, R.drawable.sejenakcafe, "Sejenak Cafe", "07.00 - 22.00", " Jl. Bunga Mondokaki No.30, Jatimulyo, Kec. Lowokwaru, Malang", "A good place to hang out!. \n \nInstagram : @sejenak.coffeehouse", "Top Offers"));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.roketto, R.drawable.roketto, "Roketto", "24 Jam", "Jl. Kendalsari No.06, Jatimulyo, Kec. Lowokwaru, Malang", "A Cozy Place with Good Ambience. \n \nInstagram : @roketto.coffee", "Top Offers"));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.lumacafe, R.drawable.lumacafe, "Luma Cafe", "08.00 - 00.00", "Jl. Klampok Kasri, Gg. 2A Jl. Wilis No.1, Malang", "Yuk #pulangkeLUMA. \n \nInstagram : @luma.malang", "Top Offers"));
        TopOffersCafe.add(new TopOffersCafe(R.drawable.kedaipokamiami, R.drawable.kedaipokamiami, "Kedai Pok Ami-Ami", "11.00 - 21.00", "Jl. Jaksa Agung Suprapto No.1, Oro-oro Dowo, Kec. Klojen, Malang", "Homey Vibes with Good Food. \n \nInstagram : @kedai.pokamiami", "Top Offers"));

        BestHomePageAdapter = new BestHomePageAdapter(this, BestPicksCafe);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        //BestHomePageAdapter = new BestHomePageAdapter(getApplicationContext(), BestPicksCafe);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        //recyclerCafe.setLayoutManager(layoutManager);
        recyclerCafe.setAdapter(BestHomePageAdapter);
        recyclerCafe.setLayoutManager(layoutManager);

        TopHomePageAdapter = new TopHomePageAdapter(this, TopOffersCafe);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(HomePage.this, LinearLayoutManager.HORIZONTAL, false);
        //TopHomePageAdapter = new TopHomePageAdapter(getApplicationContext(), TopOffersCafe);
        //RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(HomePage.this);
        //recyclerCafe.setLayoutManager(layoutManager2);
        recyclerCafe2.setAdapter(TopHomePageAdapter);
        recyclerCafe2.setLayoutManager(layoutManager2);

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

