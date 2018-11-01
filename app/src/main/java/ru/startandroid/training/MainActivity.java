package ru.startandroid.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView dayTimeImage;
    Button changeButton;
    TextView dayTimeText;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayTimeImage = (ImageView) findViewById(R.id.dayTimeImage);
        changeButton = (Button) findViewById(R.id.changeButton);
        dayTimeText = (TextView) findViewById(R.id.dayTimeText);

        final Boolean isMorning = true;

        View.OnClickListener onClickButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String morningText = getString(R.string.morning);
                String currentDayTimeText = dayTimeText.getText().toString();
                if (currentDayTimeText == morningText) {
                    Log.d(TAG, "Changing morning to night");

                    dayTimeImage.setImageResource(R.drawable.night);
                    dayTimeText.setText(R.string.night);
                } else {
                    Log.d(TAG, "Changing night to morning");

                    dayTimeImage.setImageResource(R.drawable.morning);
                    dayTimeText.setText(R.string.morning);
                }
            }
        };

        changeButton.setOnClickListener(onClickButtonListener);

    }
}
