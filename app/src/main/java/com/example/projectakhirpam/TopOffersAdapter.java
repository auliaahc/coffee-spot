package com.example.projectakhirpam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TopOffersAdapter extends RecyclerView.Adapter<TopOffersAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cafe> topOffersCafe;

    public TopOffersAdapter (Context context, ArrayList<Cafe> topOffersCafe) {
        this.context = context;
        this.topOffersCafe = topOffersCafe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cafe contact = topOffersCafe.get(position);
        holder.tvNama.setText(contact.getNamaCafe());
        holder.tvAlamat.setText(contact.getAlamat());
        holder.tvJamOperasional.setText(contact.getJamOperasional());
    }

    @Override
    public int getItemCount() {
        return topOffersCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvNama, tvAlamat, tvJamOperasional;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvJamOperasional = itemView.findViewById(R.id.tv_jamOperasional);
        }
    }
}