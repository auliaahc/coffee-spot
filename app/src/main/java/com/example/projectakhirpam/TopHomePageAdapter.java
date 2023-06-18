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

public class TopHomePageAdapter extends RecyclerView.Adapter<TopHomePageAdapter.ViewHolder> {

    private Context context;

    private ArrayList<TopOffersCafe> TopOffersCafe;


    public TopHomePageAdapter(Context context, ArrayList<TopOffersCafe> TopOffersCafe) {
        this.context = context;
        this.TopOffersCafe = TopOffersCafe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecafe2_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TopOffersCafe contact = TopOffersCafe.get(position);
        Glide.with(context).load(TopOffersCafe.get(position).to_HomeGambar).placeholder(R.mipmap.ic_launcher).into(holder.ivHomeImage2);
        holder.ivHomeImage2.setOnClickListener(v -> {

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

        return TopOffersCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView ivHomeImage2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHomeImage2 = itemView.findViewById(R.id.iv_homeImage2);
        }
    }
}
