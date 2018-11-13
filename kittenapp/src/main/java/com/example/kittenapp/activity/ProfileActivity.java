package com.example.kittenapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;
import com.example.kittenapp.model.Kitten;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

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

            String imageUrl = extras.getString("imageUrl");
            Picasso.get().load(imageUrl).into(profileImage);
            phoneText.setText(kittenUser.getPhone());
            numberText.setText(kittenUser.getNumber());
            nameText.setText(extras.getString("name"));
        }

        phoneButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhone();
            }
        });
    }

    private void dialPhone(){
//        Uri uri = Uri.parse(kittenUser.getPhone());
//        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, uri);
//        startActivity(phoneIntent);
        Uri uri = Uri.parse("tel:"+kittenUser.getPhone());
        Log.d("MyLogs", uri.toString());
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(phoneIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
