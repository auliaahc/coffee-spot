package com.example.projectakhirpam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    TextView username, fullName, emailLogin, phoneNumber;
    Button buttonSignout;
    ImageButton editProfile, backButton;
    ImageView pictureprofile;
    Bitmap bitmap;
    BitmapDrawable bitmapDrawable;
    ImageView imageView;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        username = findViewById(R.id.username);
        fullName = findViewById(R.id.fullName);
        emailLogin = findViewById(R.id.emailLogin);
        buttonSignout = findViewById(R.id.buttonSignout);
        editProfile = findViewById(R.id.editProfile);
        pictureprofile = findViewById(R.id.pictureprofile);
        backButton = findViewById(R.id.backButton);
        phoneNumber = findViewById(R.id.phoneNumber);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference userRef = databaseReference.child("Users").child(userId);

        loadProfileImage();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String usernamed = dataSnapshot.child("email").getValue(String.class);
                    String phone = dataSnapshot.child("phoneNumber").getValue(String.class);

                    phoneNumber.setText(phone);
                    fullName.setText(name);
                    emailLogin.setText(email);
                    username.setText(usernamed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

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

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pictureprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapDrawable = (BitmapDrawable) pictureprofile.getDrawable();
                bitmap = bitmapDrawable.getBitmap();

                FileOutputStream fileOutputStream = null;

                File sdCard = Environment.getExternalStorageDirectory();
                File Directory = new File(sdCard.getAbsolutePath()+ "/Download");
                Directory.mkdir();

                String filename = String.format("%d.jpg",System.currentTimeMillis());
                File outfile = new File(Directory,filename);

                Toast.makeText(ProfileActivity.this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();

                try {
                    fileOutputStream=new FileOutputStream(outfile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outfile));
                    sendBroadcast(intent);

                } catch ( FileNotFoundException e ){
                    e.printStackTrace();
                } catch ( IOException e ){
                    e.printStackTrace();
                }
            }
        });

    }

    private void loadProfileImage() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = databaseReference.child("Users").child(userId);

            userRef.child("profile").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        if (dataSnapshot.exists()) {
                            String imageUrl = dataSnapshot.getValue(String.class);
                            Picasso.get().load(imageUrl).into(pictureprofile);
                        }
                    } else {
                        Toast.makeText(ProfileActivity.this, "Failed to load profile image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

