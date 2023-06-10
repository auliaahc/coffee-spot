package com.example.projectakhirpam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    TextView username, fullName, phoneNumber, emailLogin;
    Button buttonSaveprofile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        username = findViewById(R.id.username);
        fullName = findViewById(R.id.fullName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailLogin = findViewById(R.id.emailLogin);
        buttonSaveprofile = findViewById(R.id.buttonSaveprofile);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference userRef = databaseReference.child("Users").child(userId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String usernamed = dataSnapshot.child("email").getValue(String.class);

                    fullName.setText(name);
                    emailLogin.setText(email);
                    username.setText(usernamed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        buttonSaveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String email = emailLogin.getText().toString();
                String usernamed = username.getText().toString();

                userRef.child("name").setValue(name);
                userRef.child("email").setValue(email);
                userRef.child("email").setValue(usernamed);

                Toast.makeText(EditProfileActivity.this, "Update Profile Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
