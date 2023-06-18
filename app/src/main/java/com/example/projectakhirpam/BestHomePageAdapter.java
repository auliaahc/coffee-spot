package com.example.projectakhirpam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BestHomePageAdapter extends RecyclerView.Adapter<BestHomePageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BestPicksCafe> BestPicksCafe;


    public BestHomePageAdapter(Context context, ArrayList<BestPicksCafe> BestPicksCafe) {
        this.context = context;
        this.BestPicksCafe = BestPicksCafe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecafe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BestPicksCafe contact = BestPicksCafe.get(position);
        Glide.with(context).load(BestPicksCafe.get(position).bp_HomeGambar).placeholder(R.mipmap.ic_launcher).into(holder.ivHomeImage);

        holder.ivHomeImage.setOnClickListener(v -> {

            String detailNama = contact.getNamaCafe();
            String detailJam = contact.getJamOperasional();
            String detailAlamat = contact.getAlamat();
            String detailDeskripsi = contact.getDeskripsi();
            String detailKategori = contact.getKategori();
            int detailGambar= contact.getGambar();

            Intent intent = new Intent(context, DetailCafePage.class);
            Bundle bundle = new Bundle();
            bundle.putString("cDetailNama", detailNama);
            bundle.putString("cDetailJam", detailJam);
            bundle.putString("cDetailAlamat", detailAlamat);
            bundle.putString("cDetailDeskripsi", detailDeskripsi);
            bundle.putString("cDetailKategori", detailKategori);
            bundle.putInt("cDetailGambar", detailGambar);

            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return BestPicksCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements com.example.projectakhirpam.ViewHolder {
        LinearLayout linearLayout;
        ImageView ivHomeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHomeImage = itemView.findViewById(R.id.iv_homeImage);
        }

        @Override
        public void onClick(View v) {

        }
    }
}