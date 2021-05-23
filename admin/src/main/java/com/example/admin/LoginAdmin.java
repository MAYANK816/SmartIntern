package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {
    EditText userEmail1,userPassword;
    String loggedin="";
    boolean Login_success=false;
    boolean done_login=false;
    String EmailID="",Password="";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Admin");
    String password="";
    String email="";
    CheckBox remember;
    Button login;
    final static String KEY_NAME="mypref";
    final static String KEY_CHECKBOX="rememberme";
    final static String KEY_UNAME="Armando";
    final static String KEY_UMail="android@gmail.com";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        init();
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(KEY_CHECKBOX,"true");
                    editor.putString(KEY_UMail,userEmail1.getText().toString());
                    editor.apply();
                }
                else
                {
                    sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(KEY_CHECKBOX,"false");
                    editor.putString(KEY_UMail,userEmail1.getText().toString());
                    editor.apply();

                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singIn();
            }
        });
    }
    private void init() {
        userEmail1=findViewById(R.id.userRegisterEmail);
        userPassword=findViewById(R.id.userRegisterPass);
        remember=findViewById(R.id.checkBox);
        sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        loggedin=sharedPreferences.getString(KEY_CHECKBOX,"");
        done_login=sharedPreferences.getBoolean("Login_Successful",false);
        login =findViewById(R.id.button);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(loggedin.equals("true") && Login_success){
            Intent intent=new Intent(LoginAdmin.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void singIn() {
        email=userEmail1.getText().toString();
        password=userPassword.getText().toString();
        if(email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(LoginAdmin.this, "Fill The Details.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        EmailID = ds.child("email").getValue().toString();
                        Password = ds.child("pass").getValue(String.class);
                        if (email.equals(EmailID) && password.equals(Password) ){
                            Intent intent=new Intent(LoginAdmin.this,MainActivity.class);
                            Login_success=true;
                            sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("Login_Successful",Login_success);
                            editor.apply();
                            startActivity(intent);
                            finish();
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}