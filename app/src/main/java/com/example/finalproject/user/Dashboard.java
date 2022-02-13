package com.example.finalproject.user;

import static com.example.finalproject.R.layout.dashboard_fashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.ListBarang;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_dashboard);
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
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, MainActivity.class));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void cloth(View view) {
        setContentView(dashboard_fashion);
    }
    public void fashionmen(View view) {
        setContentView(R.layout.menfashion);
    }

    public void fashionwoman(View view) {
        setContentView(R.layout.womanfashion);
    }

    public void electronic(View view) {
        setContentView(R.layout.dashboard_electronic);
    }
    public void books(View view) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "book");
        startActivity(intent);
    }

    public void other(View view) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "other");
        startActivity(intent);
    }


    public void tshirts(View view) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "clothmen");
        startActivity(intent);
    }

    public void formal(View view2) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "formalmen");
        startActivity(intent);
    }

    public void bottom(View view3) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "bottommen");
        startActivity(intent);
    }

    public void shoes(View view4) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "shoesmen");
        startActivity(intent);
    }

    public void twoman(View view5) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "clothwoman");
        startActivity(intent);
    }

    public void fwoman(View view6) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "formalwoman");
        startActivity(intent);
    }

    public void bwoman(View view7) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "bottomwoman");
        startActivity(intent);
    }

    public void swoman(View view8) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "shoeswoman");
        startActivity(intent);
    }

    public void computer(View view) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "computer");
        startActivity(intent);
    }

    public void smartphone(View view) {
        Intent intent = new Intent(this, ListBarang.class);
        intent.putExtra(EXTRA_MESSAGE, "smartphone");
        startActivity(intent);
    }


}