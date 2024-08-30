package com.ua.project.android_homework_swipefragments.listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ua.project.android_homework_swipefragments.listeners.interfaces.SwipeCallbacks;

public class OnSwipeListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;
    private final SwipeCallbacks swipeCallbacks;

    public OnSwipeListener(Context context, SwipeCallbacks swipeCallbacks) {
        this.gestureDetector = new GestureDetector(context, new GestureListener());
        this.swipeCallbacks = swipeCallbacks;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();

                if(Math.abs(diffX) > Math.abs(diffY)) {
                    if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if(diffX > 0) swipeCallbacks.onSwipeRight();
                        else swipeCallbacks.onSwipeLeft();

                        return true;
                    }
                }
                else {
                    if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if(diffY > 0) onSwipeDown();
                        else onSwipeUp();

                        return true;
                    }
                }
            }
            catch (Exception e) {
                Log.e("TAG", "onFling: ", e);
            }

            return false;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeUp() {

    }

    public void onSwipeDown() {

    }
}
