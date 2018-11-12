package com.example.kittenapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kittenapp.R;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private ImageButton phoneButtonImage;
    private TextView phoneText;
    private TextView numberText;
    private TextView nameText;

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
            String imageUrl = extras.getString("imageUrl");
            Picasso.get().load(imageUrl).into(profileImage);
            phoneText.setText(extras.getString("phone"));
            numberText.setText(extras.getString("number"));
            nameText.setText(extras.getString("name"));
        }

        phoneButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                Log.d("MyLogs","Start call");
                phoneIntent.setData(Uri.parse("tel:0970460922"));
                startActivity(phoneIntent);
                Log.d("MyLogs","End call");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
