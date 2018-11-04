package ru.startandroid.training;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView dayTimeImage;
    private Button changeButton;
    private TextView dayTimeText;

    private ImageView lightsourceImage;
    private Animation lightsourceAnimation;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_DAYTIME = "Daytime";

    SharedPreferences mSettings;

    public static final long LIGHTSOURCE_ANIMATION = 10;// period of animation
    private long mStartTime;


    private static final String TAG = "myLogs";
    private static final String TAG_ONSAVE = "onSaveLogs";
    private static final String TAG_FILE = "FileLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG_ONSAVE, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayTimeImage = (ImageView) findViewById(R.id.dayTimeImage);
        changeButton = (Button) findViewById(R.id.changeButton);
        dayTimeText = (TextView) findViewById(R.id.dayTimeText);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_DAYTIME)) {
            if (mSettings.getString(APP_PREFERENCES_DAYTIME, "").equals(getString(R.string.night))){//Setting last daytime
                    setDayTime(R.string.night);
            }
        }

        View.OnClickListener onClickButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDayTime();
            }
        };
        changeButton.setOnClickListener(onClickButtonListener);

        mStartTime = getTime();

        lightsourceImage = findViewById(R.id.imageView);

        lightsourceAnimation = AnimationUtils.loadAnimation(this, R.anim.lightsourceanimation);
        lightsourceAnimation.setDuration(1000);

        lightsourceAnimation.setAnimationListener(animationListener);
        lightsourceImage.startAnimation(lightsourceAnimation);

    }

    private long getTime() { //returns current time in milliseconds
        return System.nanoTime() / 1_000_000;
    }

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            lightsourceImage.startAnimation(lightsourceAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private void changeDayTime() {
        String morningText = getString(R.string.morning);
        String currentDayTimeText = dayTimeText.getText().toString();
        if (currentDayTimeText.equals(morningText)) {
            Log.d(TAG, "Changing morning to night");
            setDayTime(R.string.night);
        } else {
            Log.d(TAG, "Changing night to morning");
            setDayTime(R.string.morning);
        }
    }

    private void setDayTime(int dayTimeTextInt) {
        String morningText = getString(R.string.morning);
        String newDayTimeText = getString(dayTimeTextInt);

        int dayTimeImageInt = newDayTimeText.equals(morningText) ? R.drawable.morning : R.drawable.night;

        dayTimeImage.setImageResource(dayTimeImageInt);
        dayTimeText.setText(dayTimeTextInt);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d(TAG_ONSAVE, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_ONSAVE, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_ONSAVE, "onStop");

        String dayTime = dayTimeText.getText().toString();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_DAYTIME, dayTime);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_ONSAVE, "onDestroy");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG_ONSAVE, "onRestart");
        super.onRestart();
    }


}