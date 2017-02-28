package com.gui.garethpower.rugbyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button msubmitButton;
    private   Uri mImageUri = null;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

        mSelectImage = (ImageButton) findViewById(R.id.uploadImage);
        mPostTitle = (EditText) findViewById(R.id.titleField);
        mPostDesc = (EditText) findViewById(R.id.descField);
        msubmitButton = (Button) findViewById(R.id.subBtn);
        mProgress = new ProgressDialog(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("Image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        msubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {
        mProgress.setMessage("Posting to Database...");

        final String title_value = mPostTitle.getText().toString().trim();
        final String desc_value = mPostDesc.getText().toString().trim();

        if (!TextUtils.isEmpty(title_value) && !TextUtils.isEmpty(desc_value) && mImageUri != null){
            mProgress.show();
            StorageReference filepath = mStorage.child("News_Feed_Images").child(mImageUri.getLastPathSegment());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("title").setValue(title_value);
                    newPost.child("desc").setValue(desc_value);
                    newPost.child("image").setValue(downloadUrl.toString());
                    mProgress.dismiss();
                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
           mImageUri  = data.getData();
            mSelectImage.setImageURI(mImageUri);
        }
    }
}
