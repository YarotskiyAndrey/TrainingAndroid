package Interpolators;

import android.view.animation.Interpolator;

public class CustomAccelerateDecelerateInterpolator implements Interpolator {
    public CustomAccelerateDecelerateInterpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        float x = input * 2.0f;
        float fiveX = x * x * x;
        if (input < 0.5f) return 0.5f * fiveX;
        x = (input - 0.5f) * 2 - 1;
        fiveX = x * x * x;
        return 0.5f * fiveX + 1;
    }
}
