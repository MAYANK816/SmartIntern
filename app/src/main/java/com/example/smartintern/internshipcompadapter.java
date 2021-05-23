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

public class internshipcompadapter extends RecyclerView.Adapter<internshipcompadapter.Viewholder>{
    @NonNull

    Context context;
    List<internshipCompany> helperList;

    public internshipcompadapter(@NonNull Context context, List<internshipCompany> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.internshipcompany, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        internshipCompany helper = helperList.get(position);
        holder.title.setText(helper.getTitle());
        holder.CGPA.setText(helper.getCgpa());
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
        private TextView title;
        private TextView CGPA;
        LinearLayout linearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.protitle);
            CGPA = itemView.findViewById(R.id.cgpa);
            linearLayout = itemView.findViewById(R.id.linear);

        }

    }
}
