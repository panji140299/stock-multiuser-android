package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.BarangHelperClass;
import com.example.finalproject.user.Dashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ListBarang extends AppCompatActivity {
    RecyclerView recyclerView;
    Query database;
    ListAdapter listAdapter;
    ArrayList<BarangHelperClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Dashboard.EXTRA_MESSAGE);

        recyclerView = findViewById(R.id.listbarang);
        database = FirebaseDatabase.getInstance().getReference("barang").orderByChild("kategori").equalTo(message);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        listAdapter = new ListAdapter(this, list);
        recyclerView.setAdapter(listAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        BarangHelperClass barang = dataSnapshot.getValue(BarangHelperClass.class);
                        list.add(barang);
                    }
                    listAdapter.notifyDataSetChanged();
                } catch (Exception e){
                    Log.d("Database", String.valueOf(e));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Database", String.valueOf(error));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Anda Telah "+item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(ListBarang.this, MainActivity.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}