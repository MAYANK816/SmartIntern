package com.example.smartintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {
    Button forgotPass;
    private FirebaseAuth mAuth;
    EditText forgotEmail1;
    final static String KEY_NAME = "mypref";
    final static String KEY_CHECKBOX = "rememberme";
    final static String KEY_UNAME = "Armando";
    final static String KEY_UMail = "android@gmail.com";
    String User_Name = "", User_Mail = "";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        forgotEmail1=findViewById(R.id.userRegisterEmail);
        sharedPreferences = getSharedPreferences(KEY_NAME, MODE_PRIVATE);
        User_Name = sharedPreferences.getString(KEY_UNAME, "");
        User_Mail = sharedPreferences.getString(KEY_UMail, "");
        forgotEmail1.setText(User_Mail);
        forgotPass=findViewById(R.id.button2);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= forgotEmail1.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(forgotPassword.this, "Fill The Details.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email);
                    Toast.makeText(forgotPassword.this, "Please Check Your Email",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}