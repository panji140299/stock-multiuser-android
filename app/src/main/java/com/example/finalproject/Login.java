package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.finalproject.admin.AdminDashboard;
import com.example.finalproject.staff.StaffDashboard;
import com.example.finalproject.user.Dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText logUsername, logPassword;
    private Button login, register, back;
    private Switch active;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logUsername = findViewById(R.id.username);
        logPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        active = findViewById(R.id.active);
        back = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    public void Signin(View view) {
        login();
    }

    private void login() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String username = logUsername.getText().toString();
        String password = logPassword.getText().toString();
        if (username.isEmpty() || username.length() < 3) {
            logUsername.setError("Username is Required and must have atleast 3 character");
            return;
        } else logUsername.setError(null);
        if (password.isEmpty() || password.length() < 3) {
            logPassword.setError("Password is Required and must have atleast 6 character");
            return;
        } else logPassword.setError(null);

        Query query = FirebaseDatabase.getInstance().getReference("users")
                .orderByChild("username").equalTo(username);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot users : snapshot.getChildren()) {
                        String email = snapshot.child(users.getKey()).child("email").getValue().toString();
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String role = snapshot.child(users.getKey()).child("role").getValue().toString();
                                    if (role.equals("admin")) {
                                        Intent in = new Intent(getApplicationContext(), AdminDashboard.class);
                                        startActivity(in);
                                        finish();
                                    } else if (role.equals("staff")) {
                                        Intent in = new Intent(getApplicationContext(), StaffDashboard.class);
                                        startActivity(in);
                                        finish();
                                    } else {
                                        Intent in = new Intent(getApplicationContext(), Dashboard.class);
                                        startActivity(in);
                                        finish();
                                    }
                                } else{
                                    Toast.makeText(Login.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//            rootNode = FirebaseDatabase.getInstance();
//            reference = rootNode.getReference("users");
//
//            Query Check_user = reference.orderByChild("username").equalTo(dbphone);
//            Check_user.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        logUsername.setError(null);
//                        String Pass_Check = snapshot.child(dbphone).child("password").getValue(String.class);
//                        if (Pass_Check.equals(dbpass)) {
//                            logPassword.setError(null);
//
//                            String Role_check = snapshot.child(dbphone).child("role").getValue(String.class);
//                            if(Role_check.equals("admin")){
//                                Intent in = new Intent(getApplicationContext(), AdminDashboard.class);
//                                startActivity(in);
//                                finish();
//                            }
//                            else if(Role_check.equals("staff")){
//                                Intent in = new Intent(getApplicationContext(), StaffDashboard.class);
//                                startActivity(in);
//                                finish();
//                            }
//                            else{
//                                Intent in = new Intent(getApplicationContext(), Dashboard.class);
//                                startActivity(in);
//                                finish();
//                            }
//                        } else logPassword.setError("Password dosent match");
//                    } else logUsername.setError("User dosen't exist");
//                }
//
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }
//    }

        }
    }
