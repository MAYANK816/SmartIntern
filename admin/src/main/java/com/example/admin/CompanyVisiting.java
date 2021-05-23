package com.example.admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class CompanyVisiting extends AppCompatActivity {
    ImageView imageView;
    Button button;
    Uri selectedImage,downlink;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText company_Name,cgpa_Required;
    StorageReference mStorageRef,Ref;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_visiting);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("internshipCompany");
        mStorageRef = FirebaseStorage.getInstance().getReference("images");
        imageView=findViewById(R.id.imageView);
        company_Name=findViewById(R.id.companyName);
        cgpa_Required=findViewById(R.id.cgpaeditext);
        button=findViewById(R.id.galleryButton);
        progressBar1=findViewById(R.id.progressBar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryOpens();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                galleryOpens();
            }
        }

    }
    public void galleryOpens() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
            try {
                selectedImage=data.getData();
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this,"Please Select Image",Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(View view) {
        Ref=mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(selectedImage));
        Ref.putFile(selectedImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        linkUp();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        progressBar1.setVisibility(View.VISIBLE);
                    }
                });

    }

    private void linkUp() {
        imageView.setVisibility(View.INVISIBLE);
        progressBar1.setVisibility(View.INVISIBLE);
        ComanyVisitingModel helper=new ComanyVisitingModel();
        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri downloadUrl = uri;
                String Image_Url=downloadUrl.toString();
                String Company_Title=company_Name.getText().toString();
                String Require_Cgpa=cgpa_Required.getText().toString();
                if(Image_Url.isEmpty()&& Company_Title.isEmpty()&& Require_Cgpa.isEmpty() ){
                Toast.makeText(getApplicationContext(),"Please Fill The Require Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    ComanyVisitingModel helper=new ComanyVisitingModel(Image_Url,Company_Title,Require_Cgpa);
                    String mdid=myRef.push().getKey();
                    myRef.child(mdid).setValue(helper);
                    Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CompanyVisiting.this,SuccefullyRegistered.class));
                    finish();
                }

            }
        });
    }

    private String getFileExtension(Uri selectedImage) {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(selectedImage));
    }

}