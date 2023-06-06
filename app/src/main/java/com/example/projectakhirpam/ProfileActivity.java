package com.example.projectakhirpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    TextView username, fullName, phoneNumber, emailLogin;
    Button buttonSignout;
    ImageButton editProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        username = findViewById(R.id.username);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailLogin = findViewById(R.id.emailLogin);
        buttonSignout = findViewById(R.id.buttonSignout);
        editProfile = findViewById(R.id.editProfile);
        showAllUserData();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        buttonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ProfileActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passUserData();
//            }
//        });
    }

    public void showAllUserData(){
        Intent intent = getIntent();
        String dataUsername = intent.getStringExtra("email");
        String dataFullname = intent.getStringExtra("name");
        String dataPhoneNumber = intent.getStringExtra("phoneNumber");
        String dataEmail = intent.getStringExtra("email");

        username.setText(dataUsername);
        fullName.setText(dataFullname);
        phoneNumber.setText(dataPhoneNumber);
        emailLogin.setText(dataEmail);

    }
//    public void passUserData(){
//        String userUsername = profileUsername.getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
//                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
//                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("username", usernameFromDB);
//                    intent.putExtra("password", passwordFromDB);
//                    startActivity(intent);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }
}