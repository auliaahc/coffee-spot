package com.example.projectakhirpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TopOffersPage extends AppCompatActivity {
    private RecyclerView recyclerCafe;
    private ArrayList<Cafe> topOffersCafe = new ArrayList<>();
    private TopOffersAdapter TopOffersAdapter;
    private ImageButton toBack;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topoffers_page);

        toBack = findViewById(R.id.to_back);

        recyclerCafe = findViewById(R.id.recyclerViewTopOffers);
        recyclerCafe.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        toBack.setOnClickListener(v -> {
            setResult(RESULT_OK, null);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!topOffersCafe.isEmpty()) {
                    topOffersCafe.clear();
                }

                for (DataSnapshot taskSnapshot : snapshot.child("Cafes2").getChildren()) {
//                    taskSnapshot.getValue(Cafe.class).gambar = Integer.parseInt(taskSnapshot.getValue(Cafe.class).gambar);
                    Cafe cafe = taskSnapshot.getValue(Cafe.class);
                    topOffersCafe.add(cafe);
                }

                TopOffersAdapter = new TopOffersAdapter(getApplicationContext(), topOffersCafe);
                recyclerCafe.setAdapter(TopOffersAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TopOffersPage.this);
                recyclerCafe.setLayoutManager(layoutManager);

                TopOffersAdapter.setOnItemClickListener((position, v) ->
                {
                    topOffersCafe.remove(position);
                    TopOffersAdapter = new TopOffersAdapter(getApplicationContext(), topOffersCafe);
                    recyclerCafe.setAdapter(TopOffersAdapter);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_topoffers_page);
//
//        toBack = findViewById(R.id.to_back);
//
//        recyclerCafe = findViewById(R.id.recyclerViewTopOffers);
//        recyclerCafe.setHasFixedSize(true);
//        recyclerCafe.setVisibility(View.VISIBLE);
//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference();
//
////        topOffersCafe.add(new Cafe("Sejenak Cafe", "07.00 - 22.00", " Jl. Bunga Mondokaki No.30, Jatimulyo, Kec. Lowokwaru, Malang", R.drawable.sejenakcafe, "A good place to hang out!. \n \nInstagram : @sejenak.coffeehouse", "Top Offers"));
////        topOffersCafe.add(new Cafe("Roketto", "24 Jam", "Jl. Kendalsari No.06, Jatimulyo, Kec. Lowokwaru, Malang", R.drawable.roketto, "A Cozy Place with Good Ambience. \n \nInstagram : @roketto.coffee", "Top Offers"));
////        topOffersCafe.add(new Cafe("Luma Cafe", "08.00 - 00.00", "Jl. Klampok Kasri, Gg. 2A Jl. Wilis No.1, Malang", R.drawable.lumacafe, "Yuk #pulangkeLUMA. \n \nInstagram : @luma.malang", "Top Offers"));
////        topOffersCafe.add(new Cafe("Kedai Pok Ami-Ami", "11.00 - 21.00", "Jl. Jaksa Agung Suprapto No.1, Oro-oro Dowo, Kec. Klojen, Malang", R.drawable.kedaipokamiami, "Homey Vibes with Good Food. \n \nInstagram : @kedai.pokamiami", "Top Offers"));
////        topOffersCafe.add(new Cafe("Amstirdam Coffee and Roastery", "09.00 - 22.00", "Ruko Soekarno Hatta Indah D18, Mojolangu, Kec. Lowokwaru, Malang", R.drawable.amstirdam, "Amstirdam Coffee, terinspirasi dari 4 daerah penghasil kopi di Malang, menjadi destinasi wajib untuk anda! \n \nInstagram : @amstirdamcoffee", "Top Offers"));
////        topOffersCafe.add(new Cafe("Djawara Coffee", "08.30 - 23.30", "Jl. Rajekwesi, Sumberjo, Kec. Klojen, Malang", R.drawable.djawara, "\"Pull up a chair. Take a taste. Come join us. Life is so endlessly delicious!\" \n \nInstagram : @djawara____", "Top Offers"));
////        topOffersCafe.add(new Cafe("Kopi Tuju", "09.00 - 22.00", "Jl. Sutan Syahrir No.7, Sukoharjo, Kec. Klojen, Malang", R.drawable.kopituju, "#temankopituju \n \nInstagram : @kopi.tuju", "Top Offers"));
//
//        @Override
//        protected void onStart() {
//            super.onStart();
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (!topOffersCafe.isEmpty()) {
//                        topOffersCafe.clear();
//
//                    }
//
//                    for (DataSnapshot taskSnapshot : snapshot.child("Cafes").getChildren()) {
////                    taskSnapshot.getValue(Cafe.class).gambar = Integer.parseInt(taskSnapshot.getValue(Cafe.class).gambar);
//                        Cafe cafe = taskSnapshot.getValue(Cafe.class);
//                        topOffersCafe.add(cafe);
//
//                    }
//
//                    TopOffersAdapter = new TopOffersAdapter(this, topOffersCafe);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TopOffersPage.this);
//                    recyclerCafe.setLayoutManager(layoutManager);
//                    recyclerCafe.setAdapter(TopOffersAdapter);
//
//                    TopOffersAdapter.setOnItemClickListener((position, v) ->
//                    {
//                        topOffersCafe.remove(position);
//                        TopOffersAdapter = new TopOffersAdapter(this, topOffersCafe);
//                        recyclerCafe.setAdapter(TopOffersAdapter);
//                    });
//                }
//
//
//
//        toBack.setOnClickListener(v -> {
//            setResult(RESULT_OK, null);
//            finish();
//        });
//    }