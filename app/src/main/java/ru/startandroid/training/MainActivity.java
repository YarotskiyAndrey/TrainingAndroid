package ru.startandroid.training;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Interpolators.CustomAccelerateDecelerateInterpolator;
import Interpolators.UpDownInterpolator;

public class MainActivity extends AppCompatActivity {
    private ImageView dayTimeImage;
    private Button changeButton;
    private TextView dayTimeText;

    private ImageView lightsourceImage;
    private AnimatorSet lightsourceAnimation;

    private static final float LIGHTSOURCE_X_START = 0f;
    private static final float LIGHTSOURCE_X_END = 2250f;
    private static final float LIGHTSOURCE_Y_START = 0f;
    private static final float LIGHTSOURCE_Y_END = -900f;
    public static final long LIGHTSOURCE_ANIMATION_DURATION = 2000;
    private static boolean isSun = true;

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_DAYTIME = "Daytime";
    SharedPreferences mSettings;


    private static final String TAG = "myLogs";
    private static final String TAG_ONSAVE = "onSaveLogs";
    private static final String TAG_FILE = "FileLog";
    private static final String TAG_ANIM = "AnimLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG_ONSAVE, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayTimeImage = (ImageView) findViewById(R.id.dayTimeImage);
        changeButton = (Button) findViewById(R.id.changeButton);
        dayTimeText = (TextView) findViewById(R.id.dayTimeText);
        lightsourceImage = (ImageView) findViewById(R.id.lightsourceImage);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mSettings.contains(APP_PREFERENCES_DAYTIME)) {
            if (mSettings.getString(APP_PREFERENCES_DAYTIME, "").equals(getString(R.string.night))) {//Setting last daytime
                setDayTime(R.string.night);
            }
        }

        View.OnClickListener onClickButtonListener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                changeDayTime();
                changeLightSource();
            }
        };
        changeButton.setOnClickListener(onClickButtonListener);


        ValueAnimator animationX = ValueAnimator.ofFloat(LIGHTSOURCE_X_START, LIGHTSOURCE_X_END);
        animationX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                float animatedValue = (float) updatedAnimation.getAnimatedValue();
                lightsourceImage.setTranslationX(animatedValue);
            }
        });
        animationX.addListener(new ValueAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isSun = true;
                lightsourceImage.setImageResource(R.drawable.sun);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                changeLightSource();
                Log.d(TAG_ANIM, "animationSet_end");
            }


        });
        animationX.setInterpolator(new CustomAccelerateDecelerateInterpolator());
        //animationX.setInterpolator(new AccelerateDecelerateInterpolator());
        animationX.setRepeatCount(ValueAnimator.INFINITE);
        animationX.setRepeatMode(ValueAnimator.RESTART);

        ValueAnimator animationY = ValueAnimator.ofFloat(LIGHTSOURCE_Y_START, LIGHTSOURCE_Y_END);
        animationY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator updatedAnimation) {
                float animatedValue = (float) updatedAnimation.getAnimatedValue();
                lightsourceImage.setTranslationY(animatedValue);
            }
        });
        animationY.setInterpolator(new UpDownInterpolator());
        animationY.setRepeatCount(ValueAnimator.INFINITE);
        animationY.setRepeatMode(ValueAnimator.RESTART);

        lightsourceAnimation = new AnimatorSet();

        lightsourceAnimation.playTogether(animationX, animationY);
        lightsourceAnimation.setDuration(LIGHTSOURCE_ANIMATION_DURATION);

        lightsourceAnimation.start();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void changeLightSource() {
        Log.d(TAG_ANIM, "Change light");
        if (isSun) {
            lightsourceImage.setImageResource(R.drawable.moon);
            lightsourceAnimation.reverse();
            setDayTime(R.string.night);
            isSun = false;
            Log.d(TAG_ANIM, "sun to moon");
        } else {
            lightsourceImage.setImageResource(R.drawable.sun);
            lightsourceAnimation.reverse();
            setDayTime(R.string.morning);
            isSun = true;
            Log.d(TAG_ANIM, "moon to sun");
        }
    }

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