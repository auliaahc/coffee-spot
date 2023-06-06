package com.example.projectakhirpam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.projectakhirpam.Cafe;
import java.util.ArrayList;

public class BestPickAdapter extends RecyclerView.Adapter<BestPickAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cafe> bestPicksCafe;

    public BestPickAdapter(Context context, ArrayList<Cafe> bestPicksCafe) {
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
        holder.tvNama.setText(contact.getNamaCafe());
        holder.tvAlamat.setText(contact.getAlamat());
        holder.tvJamOperasional.setText(contact.getJamOperasional());
        Glide.with(context).load(bestPicksCafe.get(position).gambar).placeholder(R.mipmap.ic_launcher).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return bestPicksCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
    }
}