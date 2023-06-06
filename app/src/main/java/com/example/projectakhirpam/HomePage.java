package com.example.projectakhirpam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private RecyclerView recyclerCafe, recyclerCafe2;
    private ArrayList<HomeCafe> homePageCafe = new ArrayList<>();
    private ArrayList<HomeCafe> homePageCafe2 = new ArrayList<>();
    private HomePageAdapter HomePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerCafe = findViewById(R.id.recyclerViewHomePage);
        recyclerCafe.setHasFixedSize(true);
        recyclerCafe2 = findViewById(R.id.recyclerViewHomePage2);
        recyclerCafe2.setHasFixedSize(true);

        homePageCafe.add(new HomeCafe("https://drive.google.com/file/d/19p6wc6MYG6kNUJiGFE3z8xCAt1HkBkVr/view?usp=drive_link"));
        homePageCafe.add(new HomeCafe("https://drive.google.com/file/d/1zD9etzejTzVrPE4uHeTHcGU0Tj21mYGW/view?usp=drive_link"));
        homePageCafe.add(new HomeCafe("https://drive.google.com/file/d/1wnF7GdgfbUn4S4Ngf9-ZicdRcgV9_Skd/view?usp=drive_link"));
        homePageCafe.add(new HomeCafe("https://drive.google.com/file/d/1dirmelO_gyfOwkz5HzmEZeS9QFaVZE4O/view?usp=drive_link"));

        homePageCafe2.add(new HomeCafe("https://drive.google.com/file/d/1ZpiKIMvGVBeIrOMxHHQfEjvaWPMK2hdM/view?usp=drive_link"));
        homePageCafe2.add(new HomeCafe("https://drive.google.com/file/d/1d5H2wKQE4eBHUfZ34WfkhPD7-kUb9CSS/view?usp=drive_link"));
        homePageCafe2.add(new HomeCafe("https://drive.google.com/file/d/1f6FX9fUxCL-oPhJiOdAy25ZAzFo9TV90/view?usp=drive_link"));
        homePageCafe2.add(new HomeCafe("https://drive.google.com/file/d/1tEwS3NabV0Soa3mcecLj6cglcZJCgNae/view?usp=drive_link"));

        HomePageAdapter = new HomePageAdapter(this, homePageCafe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomePage.this);
        recyclerCafe.setLayoutManager(layoutManager);

        HomePageAdapter = new HomePageAdapter(this, homePageCafe2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(HomePage.this);
        recyclerCafe.setLayoutManager(layoutManager2);
    }
}

