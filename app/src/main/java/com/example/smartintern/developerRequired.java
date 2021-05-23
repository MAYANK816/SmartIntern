package com.example.smartintern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class developerRequired extends AppCompatActivity {
    developersAdapter adapter;
    List<developers> developersList=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_required);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("developers");
        recyclerView=findViewById(R.id.devlopersrcv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter =new developersAdapter(getApplicationContext(),developersList);
        recyclerView.setAdapter(adapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    developers helper=new developers();
                    helper.setStartupname(dataSnapshot.child("title").getValue().toString());
                    helper.setAndroiddeveloper("ANDROID DEVELOPER : "+dataSnapshot.child("androiddev").getValue().toString());
                    helper.setWebdeveloper("WEBSITE DEVELOPER : "+dataSnapshot.child("webdev").getValue().toString());
                    helper.setUiux("UI/UX DESIGNER : "+dataSnapshot.child("uiux").getValue().toString());
                    helper.setFullstack("FULLSTACK DEVELOPER : "+dataSnapshot.child("fullstackdev").getValue().toString());
                    helper.setNumber(dataSnapshot.child("number").getValue().toString());
                    developersList.add(helper);
                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}