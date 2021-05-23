package com.example.smartintern;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class developersAdapter extends RecyclerView.Adapter<developersAdapter.Viewholder>{
    @NonNull
    DatabaseReference myRef;
    FirebaseDatabase database;
    Context context;
    List<developers> helperList;

    public developersAdapter(@NonNull Context context, List<developers> helperList) {
        this.context = context;
        this.helperList = helperList;
    }

    public developersAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developerlayout, parent, false);
        return new developersAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull developersAdapter.Viewholder holder, int position) {
        developers helper = helperList.get(position);

        holder.androiddeveloper.setText(helper.getAndroiddeveloper());
        holder.startupName.setText(helper.getStartupname());
        holder.webdeveloper.setText(helper.getWebdeveloper());
        holder.uiux.setText(helper.getUiux());
        holder.fullstack.setText(helper.getFullstack());
        holder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("developers");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:+91-"+helper.getNumber()));
                            v.getContext().startActivity(intent);
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return helperList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView androiddeveloper;
        private TextView startupName;
        private TextView webdeveloper;
        private TextView uiux;
        private TextView fullstack;
        private ImageButton callButton;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            startupName=itemView.findViewById(R.id.startupname);
            androiddeveloper = itemView.findViewById(R.id.AndroidDev);
            webdeveloper = itemView.findViewById(R.id.webdev);
            uiux = itemView.findViewById(R.id.uiux);
            fullstack = itemView.findViewById(R.id.fullstack);
            callButton=itemView.findViewById(R.id.btncall);
        }

    }
}
