package com.example.projectakhirpam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference;
    TextView username, fullName, phoneNumber, emailLogin;
    Button buttonSaveprofile;
    ImageButton backButton;
    FirebaseStorage storage;
    StorageReference storageRef;
    ImageView pictureprofile;
    private static final int PICK_IMAGE_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        username = findViewById(R.id.username);
        fullName = findViewById(R.id.fullName);
        emailLogin = findViewById(R.id.emailLogin);
        buttonSaveprofile = findViewById(R.id.buttonSaveprofile);
        pictureprofile = findViewById(R.id.pictureprofile);
        backButton = findViewById(R.id.backButton);
        phoneNumber = findViewById(R.id.phoneNumber);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String userId = auth.getCurrentUser().getUid();
        DatabaseReference userRef = databaseReference.child("Users").child(userId);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        pictureprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openGallery();
            }
        });

        loadProfileImage();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String usernamed = dataSnapshot.child("email").getValue(String.class);

                    Object phone = dataSnapshot.child("phoneNumber").getValue(Object.class);
                    String phoned = phone != null ?phone.toString() : "";
                    phoneNumber.setText(phoned);

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
                String phone = phoneNumber.getText().toString();

                userRef.child("name").setValue(name);
                userRef.child("email").setValue(email);
                userRef.child("email").setValue(usernamed);
                userRef.child("phoneNumber").setValue(phone);

                Toast.makeText(EditProfileActivity.this, "Update Profile Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            pictureprofile.setImageURI(selectedImageUri);
            uploadImage(selectedImageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        StorageReference imagesRef = storageRef.child("profiles").child(fileName);
        UploadTask uploadTask = imagesRef.putFile(imageUri);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            imagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                saveImageUrlToDatabase(downloadUrl);
            });
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveImageUrlToDatabase(String imageUrl) {
        FirebaseUser currentUser = auth.getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference userRef = databaseReference.child("Users").child(userId);

        userRef.child("profile").setValue(imageUrl)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to save image URL to database", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(EditProfileActivity.this, "Failed to load profile image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
