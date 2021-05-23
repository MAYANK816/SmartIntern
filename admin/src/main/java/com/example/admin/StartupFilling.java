package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class StartupFilling extends AppCompatActivity {
    Button next;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    EditText startupname, androiddev, Number, webdev, uiux,fullstack;
    String user_startupname = "";
    String user_androiddev = "";
    String user_number = "";
    String user_webdev = "";
    String user_uiux = "";
    String user_fullstack = "";
    boolean duplicate_exists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_required);
        init();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_startupname = startupname.getText().toString();
                user_androiddev = androiddev.getText().toString();
                user_number = Number.getText().toString();
                user_webdev = webdev.getText().toString();
                user_uiux = uiux.getText().toString();
                user_fullstack = fullstack.getText().toString();
                myRef = database.getReference("developers");
                if (user_startupname.isEmpty() && user_androiddev.isEmpty() && user_number.isEmpty() && user_webdev.isEmpty()&& user_uiux.isEmpty() && user_fullstack.isEmpty()) {

                    Toast.makeText(StartupFilling.this, "Please fill the details", Toast.LENGTH_SHORT).show();

                }
                else{

                    AdminDeveloperRequired helper = new AdminDeveloperRequired(user_androiddev,user_webdev,user_uiux,user_fullstack,user_startupname,user_number);
                    String mdid = myRef.push().getKey();
                    myRef.child(mdid).setValue(helper);
                    Toast.makeText(StartupFilling.this, "Details Filled.",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StartupFilling.this,SuccefullyRegistered.class));
                    finish();
                }

            }
        });
    }



    private void init() {
        next = findViewById(R.id.next_button);
        startupname = findViewById(R.id.userName_);
        androiddev = findViewById(R.id.User_android);
        Number = findViewById(R.id.User_number);
        webdev = findViewById(R.id.websitedev);
        uiux = findViewById(R.id.uiuxdesigner);
        fullstack = findViewById(R.id.fullstack);
        database = FirebaseDatabase.getInstance();
        duplicate_exists=false;
    }
}