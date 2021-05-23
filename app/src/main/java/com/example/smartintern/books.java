package com.example.smartintern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class books extends AppCompatActivity {
ListView listView;
DatabaseReference reference;
List<BooksModel> booksModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
            listView=findViewById(R.id.listview);
            booksModels=new ArrayList<>();
            viewAllfiles();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BooksModel booksModel=booksModels.get(position) ;
                    Intent i=new Intent();
                    i.setData(Uri.parse(booksModel.getUrl()));
                    startActivity(i);
                }
            });
    }

    private void viewAllfiles() {
        reference= FirebaseDatabase.getInstance().getReference("uploads");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    BooksModel booksModel= dataSnapshot.getValue(BooksModel.class);
                    booksModels.add(booksModel);

                }
                String[] uploads=new String[booksModels.size()];
                for(int i=0;i<uploads.length;i++){
                    uploads[i]=booksModels.get(i).getName();

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploads){
                    @Override
                    public View getView(int position,View convertView, ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        TextView myText=view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);
                        return view;
                    }
                };
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}