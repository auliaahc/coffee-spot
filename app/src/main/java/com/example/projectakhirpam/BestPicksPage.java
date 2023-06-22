package com.example.projectakhirpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BestPicksPage extends AppCompatActivity {
    private RecyclerView recyclerCafe;
    private ArrayList<Cafe> bestPicksCafe = new ArrayList<>();
    private BestPicksAdapter BestPicksAdapter;
    private ImageButton bpBack;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestpicks_page);

        bpBack = findViewById(R.id.bp_back);

        recyclerCafe = findViewById(R.id.recyclerViewBestPicks);
        recyclerCafe.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

//        bestPicksCafe.add(new Cafe("AADK", "07.00 - 00.00", "Jl. Bandung No.28, Penanggungan, Kec. Klojen, Malang", R.drawable.aadk, "AADK Coffee & Eatery adalah salah satu brand coffee shop yang berdiri sejak tahun 2020. AADK tidak ingin menjadi sekadar tempat ngopi, namun selalu berkeinginan untuk turut menemani #KawanAADK menciptakan berbagai momen berarti. \n \nInstagram : @adaapadengankopi", "Best Picks"));
//        bestPicksCafe.add(new Cafe("Robucca", "07.30 - 21.00", "Ijen Nirwana, Jl. Raya No.1A, Bareng, Kec. Klojen, Malang", R.drawable.robucca, "Coffee • Eatery • Co-working space. \n \nInstagram : @robucca.id", "Best Picks"));
//        bestPicksCafe.add(new Cafe("Pesen Kopi Plus", "11.00 - 23.45", "Jl. Mayjend Panjaitan No.191, Penanggungan, Kec. Klojen, Malang", R.drawable.pesenkopipluss, "Start your day with a scrumptious waffle adventure at #PesenkopiPlus!. \n \nInstagram : @pesenkopiplus", "Best Picks"));
//        bestPicksCafe.add(new Cafe("Handall Coffee", "06.00 - 22.00", "Jl. Semanggi Timur No.7, Jatimulyo, Kec. Lowokwaru, Malang", R.drawable.handall, "The pursuit of brewing specialty coffee that you'll remember. #EveryoneCanBeHandall. \n \nInstagram : @handall.coffee", "Best Picks"));
//        bestPicksCafe.add(new Cafe("Kogu Cafe", "08.00 - 23.00", "Jl. Kepundung No.45, Bareng, Kec. Klojen, Kota Malang,", R.drawable.kogu, "Most affordable coffee shop in malang. ✨ ☕ INTRODUCE YOU OUR NEW KOGU COFFEE BLEND!!! ☕ ✨. \n \nInstagram : @kogu.id", "Best Picks"));
//        bestPicksCafe.add(new Cafe("OR Traffic", "24 Jam", "Jl. Raya Sengkaling, Mulyoagung, Kecamatan Dau, Malang", R.drawable.ortraffic, "#fastbarorpassion. Everyday 24 Hours 1st Floor | 2nd Floor | Rooftop \n \nInstagram : @traffic.or", "Best Picks"));
//        bestPicksCafe.add(new Cafe("Hindia Koffie", "08.00 - 22.00", "Jl. Simpang Ijen No.7, Oro-oro Dowo, Kec. Klojen, Malang", R.drawable.hindia, "Nikmati hari liburmu dengan segelas cappuccino hangat dari Hindia #hindiakoffie · Minuman segar berbahan dasar kopi untuk sore di Malang yang panas  \n \nInstagram : @hindiakoffie", "Best Picks"));

//        BestPicksAdapter = new BestPicksAdapter(this, bestPicksCafe);

        bpBack.setOnClickListener(v -> {
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
                if (!bestPicksCafe.isEmpty()) {
                    bestPicksCafe.clear();

                }

                for (DataSnapshot taskSnapshot : snapshot.child("Cafes").getChildren()) {
//                    taskSnapshot.getValue(Cafe.class).gambar = Integer.parseInt(taskSnapshot.getValue(Cafe.class).gambar);
                    Cafe cafe = taskSnapshot.getValue(Cafe.class);
                    bestPicksCafe.add(cafe);

                }

                BestPicksAdapter = new BestPicksAdapter(getApplicationContext(), bestPicksCafe);
                recyclerCafe.setAdapter(BestPicksAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BestPicksPage.this);
                recyclerCafe.setLayoutManager(layoutManager);

                BestPicksAdapter.setOnItemClickListener((position, v) ->
                {
                    bestPicksCafe.remove(position);
                    BestPicksAdapter = new BestPicksAdapter(getApplicationContext(), bestPicksCafe);
                    recyclerCafe.setAdapter(BestPicksAdapter);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

