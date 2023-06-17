package com.example.projectakhirpam;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    Button google;
    FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final EditText email = findViewById(R.id.emailLogin);
        final EditText password = findViewById(R.id.passwordLogin);
        final Button loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailtxt = email.getText().toString();
                final String passwordtxt = password.getText().toString();

                if (emailtxt.isEmpty() || passwordtxt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill the form correctly", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(emailtxt, passwordtxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = auth.getCurrentUser();
                                        if (user != null) {
                                            // Email dan password cocok
                                            // Lakukan tindakan yang sesuai, seperti pindah ke halaman beranda
                                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                                            intent.putExtra("email", user.getEmail());
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // Terjadi kesalahan, user null
                                            Toast.makeText(LoginActivity.this, "Failed to get user information", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        // Start Intent Register
        TextView register = findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // End Intent Register

        google = findViewById(R.id.GoogleLogin);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We are creating your account");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    int RC_SIGN_IN = 40;

    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

//                            Untuk Update RDB Cafe
//                            DatabaseReference cafeRef = FirebaseDatabase.getInstance().getReference().child("Cafes");
//                            Cafes cafe = new Cafes("Handall Coffee", "06:00 - 22:00", "Jl. Semanggi Timur No.7, Jatimulyo, Kec. Lowokwaru, Kota Malang", "Top Offers",
//                                    "Cafe Handall Malang berlokasi di Jatimulyo, Lowokwaru. Tempat ini sangat cocok untuk Anda yang ingin mengerjakan tugas, mengadakan event, atau tempat nongkrong bersama teman. Di sini menyediakan " +
//                                            "berbagai menu spesial mulai dari western food hingga Indonesian food yang tentunya enak. Handall Coffee memiliki bangunan 2 tingkat dengan area smooking dan non smoking room yang luas. Di sini juga tersedia berbagai fasilitas lengkap seperti membership, " +
//                                            "wifi, parkir luas, dan sebagainya.");
//                            String key = cafeRef.push().getKey();
//                            cafeRef.child(key).setValue(cafe);

                            Users users = new Users();
                            users.setUserId(user.getUid());
                            users.setProfile(user.getPhotoUrl().toString());
                            users.setName(user.getDisplayName());
                            users.setPhoneNumber(users.getPhoneNumber());
                            users.setEmail(user.getEmail());

                            database.getReference().child("Users").child(user.getUid()).setValue(users);
                            Intent intent = new Intent(LoginActivity.this, HomePage.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}