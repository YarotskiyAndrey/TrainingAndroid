package Interpolators;

import android.view.animation.Interpolator;

public class UpDownInterpolator implements Interpolator {
    public UpDownInterpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        return (float) Math.sin(input * (float) Math.PI);
    }
}
