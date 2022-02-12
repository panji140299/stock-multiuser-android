package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.model.BarangHelperClass;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private  ArrayList<BarangHelperClass> list;

    public ListAdapter(Context context, ArrayList<BarangHelperClass> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        BarangHelperClass barang = list.get(position);
        holder.nama.setText(barang.getNama());
        holder.stock.setText(barang.getStock());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView nama, stock;
        Button deskripsi;

        public ListViewHolder(@NonNull View itemView){
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            stock = itemView.findViewById(R.id.stock);
            deskripsi = itemView.findViewById(R.id.detail);
            deskripsi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BarangHelperClass barang = list.get(getAdapterPosition());

                    Intent detailIntent = new Intent(context, DetailActivity.class);
                    detailIntent.putExtra("nama", barang.getNama());
                    detailIntent.putExtra("stock", barang.getStock());
                    context.startActivity(detailIntent);
                }
            });

        }
    }


}
