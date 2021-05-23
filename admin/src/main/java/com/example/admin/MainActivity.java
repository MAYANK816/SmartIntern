package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CompanyVisiting(View view) {
        startActivity(new Intent(MainActivity.this, CompanyVisiting.class));
    }

    public void DevelopersRequired(View view) {
        startActivity(new Intent(MainActivity.this, StartupFilling.class));

    }

    public void Events(View view) {
        startActivity(new Intent(MainActivity.this, Events.class));
    }

    public void BooksandNotes(View view) {
        startActivity(new Intent(MainActivity.this, Books.class));
    }
}