package com.example.projectakhirpam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class BestPicksAdapter extends RecyclerView.Adapter<BestPicksAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cafe> bestPicksCafe;
    private static ClickListener clickListener;

    public BestPicksAdapter(Context context, ArrayList<Cafe> bestPicksCafe) {
        this.context = context;
        this.bestPicksCafe = bestPicksCafe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cafe contact = bestPicksCafe.get(position);
        int gambar = bestPicksCafe.get(position).getGambar();

        holder.tvNama.setText(contact.getNamaCafe());
        holder.tvAlamat.setText(contact.getAlamat());
        holder.tvJamOperasional.setText(contact.getJamOperasional());
        Glide.with(context).load(gambar).placeholder(R.mipmap.ic_launcher).into(holder.ivImage);

        holder.linearLayout.setOnClickListener(v -> {
            String detailNama = holder.tvNama.getText().toString();
            String detailJam = holder.tvJamOperasional.getText().toString();
            String detailAlamat = holder.tvAlamat.getText().toString();
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

        return bestPicksCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements com.example.projectakhirpam.ViewHolder {
        LinearLayout linearLayout;
        TextView tvNama, tvAlamat, tvJamOperasional;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJamOperasional = itemView.findViewById(R.id.tv_jamOperasional);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), itemView);
        }
    }

    public void setOnItemClickListener(BestPicksAdapter.ClickListener clickListener) {
        BestPicksAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
