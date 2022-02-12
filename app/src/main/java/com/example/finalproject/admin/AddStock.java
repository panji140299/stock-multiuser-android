package com.example.finalproject.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddStock extends AppCompatActivity {
    EditText id, qty;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        id = findViewById(R.id.idstock);
        qty = findViewById(R.id.stock);



    }

    public void add(View view) {
        String qtystock = qty.getText().toString();
        String idbarang = id.getText().toString();

        if (qtystock.isEmpty()) {
            qty.setError("Qty is Required");
            return;
        } else qty.setError(null);


        if (idbarang.isEmpty()) {
            id.setError("Username is Required and must have atleast 3 character");
            return;
        } else id.setError(null);

        if(!idbarang.isEmpty()){
            final String dbid = id.getText().toString();
            final String dbqty = qty.getText().toString();
            final String dbnama = "Kaos";
            final String dbkategori = "clothmen";
            final String dbdesk = "kaso pria paling nyaman";
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("barang");
            Query Check_user = reference.child("barang"+dbid);
            Check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                            reference.child("barang"+dbid).child("stock").setValue(dbqty);

                            Context context = getApplicationContext();
                            CharSequence text = "Add Successfull ";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            startActivity(new Intent(AddStock.this, AdminDashboard.class));


                    }
                    else {
                        id.setError("Id Tidak Ada");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}