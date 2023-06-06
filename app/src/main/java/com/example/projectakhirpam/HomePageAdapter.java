package com.example.projectakhirpam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeCafe> homePageCafe;

    public HomePageAdapter(Context context, ArrayList<HomeCafe> homePageCafe) {
        this.context = context;
        this.homePageCafe = homePageCafe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homecafe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeCafe contact = homePageCafe.get(position);
        Glide.with(context).load(homePageCafe.get(position).homeGambar).placeholder(R.mipmap.ic_launcher).into(holder.ivHomeImage);
    }

    @Override
    public int getItemCount() {

        return homePageCafe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView ivHomeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHomeImage = itemView.findViewById(R.id.iv_homeImage);
        }
    }
}