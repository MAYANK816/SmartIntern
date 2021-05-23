package com.example.smartintern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    @NonNull

    Context context;
    List<slideImage> helperList;

    public Adapter(@NonNull Context context, List<slideImage> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlayout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        slideImage helper = helperList.get(position);
        Glide.with(context)
                .load(helperList.get(position).getImage())
                .into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return helperList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageview;

        LinearLayout linearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.image);
            linearLayout = itemView.findViewById(R.id.linear);

        }

    }
}
