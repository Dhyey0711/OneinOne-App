package com.example.oneinone_alltoolsapp;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.view.View;

public class GradientBackgroundAnimation {

    private View view;
    private int[] gradientDrawables;
    private int currentIndex = 0;
    private Handler handler = new Handler();
    private int transitionDuration = 2000; // 2 seconds transition duration
    private int delayDuration = 2000; // 2 seconds delay duration

    public GradientBackgroundAnimation(View view, int[] gradientDrawables) {
        this.view = view;
        this.gradientDrawables = gradientDrawables;
    }

    public void startAnimation() {
        handler.post(runnable);
    }

    public void stopAnimation() {
        handler.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            changeBackground();
            handler.postDelayed(this, transitionDuration + delayDuration); // schedule next change
        }
    };

    private void changeBackground() {
        Context context = view.getContext();
        int nextIndex = (currentIndex + 1) % gradientDrawables.length;
        Drawable[] drawables = new Drawable[]{
                context.getResources().getDrawable(gradientDrawables[currentIndex]),
                context.getResources().getDrawable(gradientDrawables[nextIndex])
        };
        TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);
        view.setBackground(transitionDrawable);
        transitionDrawable.startTransition(transitionDuration); // smooth transition

        currentIndex = nextIndex;
    }
}
