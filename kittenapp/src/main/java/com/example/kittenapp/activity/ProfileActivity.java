package com.example.kittenapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;
import com.example.kittenapp.model.Kitten;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

public class ProfileActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST_CALL_PHONE;
    private ImageView profileImage;
    private ImageButton phoneButtonImage;
    private TextView phoneText;
    private TextView numberText;
    private TextView nameText;

    private Kitten kittenUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profileImage = findViewById(R.id.profileImageView);
        phoneButtonImage = findViewById(R.id.phoneImageButton);
        phoneText = findViewById(R.id.profilePhoneText);
        numberText = findViewById(R.id.profileNumberTextView);
        nameText = findViewById(R.id.profileNameTextView);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            kittenUser = (Kitten) extras.getSerializable("kitten");

            String imageUrl = kittenUser.getImageUrl();
            Picasso.get().load(imageUrl).into(profileImage);
            phoneText.setText(kittenUser.getPhone());
            numberText.setText(kittenUser.getNumber());
            nameText.setText(kittenUser.getName());
        }

        phoneButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRequest();
            }
        });
    }

    private void callRequest() {
        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.d("MyLogs", "Try permission request");

            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                Log.d("MyLogs", "Permission window request");
                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);

            } else {
                Log.d("MyLogs", "Permission requested");
                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }

        } else {
            Log.d("MyLogs", "Permission granted");
            callPhone();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    if(!shouldShowRequestPermissionRationale(permissions[0])){
                        Log.d("MyLogs", "User checked Never show again");
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                }
            }
        }
    }

    private void callPhone() {
        Uri uri = Uri.parse("tel:" + kittenUser.getPhone());
        Log.d("MyLogs", "Calling");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL, uri);
        startActivity(phoneIntent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
