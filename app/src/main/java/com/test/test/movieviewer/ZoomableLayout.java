package com.test.test.movieviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Extended HorizontalScrollView to easily handle the rendering of layout exceeding the initial bounds
 */
public class ZoomableLayout extends HorizontalScrollView implements ScaleGestureDetector.OnScaleGestureListener {

    private ScaleGestureDetector scaleDetector;
    private int MOVED = 0;
    private static final float MIN_ZOOM = 0.6f;
    private static final float MAX_ZOOM = 4.0f;

    private int mode = 0;   //mode 0 = default, 1 = drag, 2 = zoom
    private float prevScaleFactor = 0f;
    private float scale = 1.0f;

    private float startX = 0f;
    private float startY = 0f;

    private float dx = 0f;
    private float dy = 0f;
    private float prevDx = 0f;
    private float prevDy = 0f;


    //Contstructors
    public ZoomableLayout(Context context) {
        super(context);
        init(context);
    }

    public ZoomableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZoomableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        scaleDetector = new ScaleGestureDetector(context, this);

    }

    //To disable horizontal scroll
    @Override
    public boolean onTouchEvent(MotionEvent ev){
        return false;
    }

    //Translate and scale layout accordingly
    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                MOVED = 0;
                if (scale >= MIN_ZOOM) {
                    mode = 1;
                    startX = motionEvent.getX() - prevDx;
                    startY = motionEvent.getY() - prevDy;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                MOVED++;
                if (mode == 1) {
                    dx = motionEvent.getX() - startX;
                    dy = motionEvent.getY() - startY;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = 2;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = 1;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                prevDx = dx;
                prevDy = dy;
                if (MOVED > 5) {
                    return true;
                } else {
                    return false;
                }
        }
        scaleDetector.onTouchEvent(motionEvent);

        if ((mode == 1 && scale >= MIN_ZOOM) || mode == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
            applyScaleAndTranslation();
        }

        return false;

    }

    // ScaleGestureDetector
    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleDetector) {
        return true;
    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleDetector) {
        float scaleFactor = scaleDetector.getScaleFactor();
        if (prevScaleFactor == 0 || (Math.signum(scaleFactor) == Math.signum(prevScaleFactor))) {
            scale *= scaleFactor;
            scale = Math.max(MIN_ZOOM, Math.min(scale, MAX_ZOOM));
            prevScaleFactor = scaleFactor;
        } else {
            prevScaleFactor = 0;
        }
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleDetector) {

    }

    private void applyScaleAndTranslation() {
        child().setScaleX(scale);
        child().setScaleY(scale);
        child().setTranslationX(dx);
        child().setTranslationY(dy);
    }

    private View child() {
        return getChildAt(0);
    }
}