package com.example.smartintern;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivateKey;

public class Login extends AppCompatActivity {
    EditText userEmail1,userPassword;
    private FirebaseAuth mAuth;
    TextView forgotPassword,newAccount;
    String loggedin="";
    boolean Login_success=false;
    boolean done_login=false;
    Double log=0.0;
    String password="";
    ProgressBar progressBar;
    Double lat=0.0;
    boolean isEmailVerified=false;
    private FusedLocationProviderClient fusedLocationProviderClient;
    String email="";
    CheckBox remember;

    final static String KEY_NAME="mypref";
    final static String KEY_CHECKBOX="rememberme";
    final static String KEY_UNAME="Armando";
    final static String KEY_UMail="android@gmail.com";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

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

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        userEmail1=findViewById(R.id.userRegisterEmail);
        newAccount=findViewById(R.id.newAccount);
        userPassword=findViewById(R.id.userRegisterPass);
        remember=findViewById(R.id.checkBox);
        sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
        loggedin=sharedPreferences.getString(KEY_CHECKBOX,"");
        done_login=sharedPreferences.getBoolean("Login_Successful",false);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);


    }

    @Override
    public void onStart() {
        super.onStart();
        if(loggedin.equals("true") && done_login){
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }
    }
    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            Login_success=true;
            progressBar.setVisibility(View.INVISIBLE);
            sharedPreferences=getSharedPreferences(KEY_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("Login_Successful",Login_success);
            editor.apply();
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void singIn(View view) {
        progressBar.setVisibility(View.VISIBLE);
        email=userEmail1.getText().toString();
        password=userPassword.getText().toString();
        if(email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(Login.this, "Fill The Details.",
                    Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendEmailVerificaation();
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(e instanceof FirebaseAuthInvalidCredentialsException)
                    {
                        userPassword.setError("Invalid Password");
                        userPassword.requestFocus();
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                    else  if(e instanceof FirebaseAuthInvalidUserException){

                        userEmail1.setError("Invalid Email");
                        userEmail1.requestFocus();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else {
                        Toast.makeText(Login.this, "Please Check Your Connection Or Change Your City to Sirsa.",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }
    private void sendEmailVerificaation() {

         isEmailVerified=mAuth.getCurrentUser().isEmailVerified();
        if(mAuth.getCurrentUser()!=null)
        {

            if(isEmailVerified )
            {
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            }
            else {
                Toast.makeText(Login.this, " Please Verify Your Mail or Change Your City To Sirsa ." ,
                        Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}