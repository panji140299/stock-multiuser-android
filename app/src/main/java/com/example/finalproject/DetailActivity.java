package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.user.Dashboard;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView namaBarang = findViewById(R.id.nama);
        TextView stockBarang = findViewById(R.id.stock);
        TextView deskBarang = findViewById(R.id.deskripsi);
        ImageView gambarBarang = findViewById(R.id.sportsImageDetail);

        namaBarang.setText(getIntent().getStringExtra("nama"));
        stockBarang.setText(getIntent().getStringExtra("stock"));
        deskBarang.setText(getIntent().getStringExtra("deskripsi"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(gambarBarang);
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
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}