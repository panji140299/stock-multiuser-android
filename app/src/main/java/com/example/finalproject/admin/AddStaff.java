package com.example.finalproject.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Login;
import com.example.finalproject.R;
import com.example.finalproject.model.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaff extends AppCompatActivity {
    Button register, back;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    EditText regUsername, regPassword, regNama, regEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regUsername = findViewById(R.id.username);
        regPassword = findViewById(R.id.password);
        regNama = findViewById(R.id.nama);
        regEmail = findViewById(R.id.email);

        register = findViewById(R.id.register);
        back = findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStaff.this, Login.class));
            }
        });
    }

    public void Register(View view) {
        register();
    }

    private void register() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        String username = regUsername.getText().toString();
        String password = regPassword.getText().toString();
        String nama = regNama.getText().toString();
        String email = regEmail.getText().toString();

        if (email.isEmpty() || email.length() < 3) {
            regEmail.setError("Email is Required and must have atleast 3 character");
            return;
        } else regEmail.setError(null);


        if (username.isEmpty() || username.length() < 3) {
            regUsername.setError("Username is Required and must have atleast 3 character");
            return;
        } else regUsername.setError(null);
        if (password.isEmpty() || password.length() < 6) {
            regPassword.setError("Password is Required and must have atleast 6 character");
            return;
        } else regPassword.setError(null);
        if (nama.isEmpty() || nama.length() < 3) {
            regNama.setError("Nama is Required and must have atleast 3 character");
            return;
        } else regNama.setError(null);

        if (username.isEmpty() || username.length() < 3) {
            regUsername.setError("Username is Required and must have atleast 3 character");
            return;
        } else regUsername.setError(null);
        if (password.isEmpty() || password.length() < 6) {
            regPassword.setError("Password is Required and must have atleast 6 character");
            return;
        } else regPassword.setError(null);
        if (nama.isEmpty() || nama.length() < 3) {
            regNama.setError("Nama is Required and must have atleast 3 character");
            return;
        } else regNama.setError(null);

        if (!password.isEmpty()) {

            final String dbpass = regPassword.getText().toString();
            final String dbphone = regUsername.getText().toString();
            final String dbnama = regNama.getText().toString();
            final String dbemail = regEmail.getText().toString();
            final String dbrole = "staff";
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");
            mAuth.createUserWithEmailAndPassword(dbemail, dbpass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                UserHelperClass user = new UserHelperClass(dbphone, dbpass, dbnama, dbrole, dbemail);
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(AddStaff.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), AdminDashboard.class));
                                            finish();
                                        } else {
                                            Toast.makeText(AddStaff.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(AddStaff.this, "Register Unsuccessfull!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}