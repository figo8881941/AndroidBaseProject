package com.duoduo.commonbase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 * 实现了双击的View
 *
 */
public class DoubleClickView extends RelativeLayout {

    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration
            .getDoubleTapTimeout();

    private MotionEvent currentDownEvent;
    private MotionEvent previousUpEvent;
    private int doubleTapSlopSquare;
    private int doubleTapTouchSlopSquare;
    private boolean alwaysInBiggerTapRegion = true;
    private float downFocusX;
    private float downFocusY;

    private DoubleClickLisnter mDoubleClickLisnter;

    public DoubleClickView(Context context) {
        super(context);
    }

    public DoubleClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleClickView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        int doubleTapSlop = configuration.getScaledDoubleTapSlop();
        int doubleTapTouchSlop = configuration.getScaledTouchSlop();
        doubleTapSlopSquare = doubleTapSlop * doubleTapSlop;
        doubleTapTouchSlopSquare = doubleTapTouchSlop * doubleTapTouchSlop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final boolean pointerUp = (action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP;
        final int skipIndex = pointerUp ? event.getActionIndex() : -1;
        float sumX = 0, sumY = 0;
        final int count = event.getPointerCount();
        for (int i = 0; i < count; i++) {
            if (skipIndex == i)
                continue;
            sumX += event.getX(i);
            sumY += event.getY(i);
        }
        final int div = pointerUp ? count - 1 : count;
        final float focusX = sumX / div;
        final float focusY = sumY / div;
        switch (event.getAction()) {

            case MotionEvent.ACTION_POINTER_DOWN:
                downFocusX = focusX;
                downFocusY = focusY;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                downFocusX = focusX;
                downFocusY = focusY;
                break;

            case MotionEvent.ACTION_DOWN: {
                downFocusX = focusX;
                downFocusY = focusY;
                if (previousUpEvent != null
                        && currentDownEvent != null
                        && isConsideredDoubleTap(currentDownEvent,
                        previousUpEvent, event)) {
                    if (mDoubleClickLisnter != null) {
                        mDoubleClickLisnter.onDoubleClick();
                    }
                }
                currentDownEvent = MotionEvent.obtain(event);
                alwaysInBiggerTapRegion = true;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final int deltaX = (int) (focusX - downFocusX);
                final int deltaY = (int) (focusY - downFocusY);
                int distance = (deltaX * deltaX) + (deltaY * deltaY);
                if (distance > doubleTapTouchSlopSquare) {
                    alwaysInBiggerTapRegion = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                previousUpEvent = MotionEvent.obtain(event);
                performClick();
                break;
            }

            default:
                break;
        }

        return true;
    }

    public void setDoubleClickListner(DoubleClickLisnter doubleClickLisnter) {
        mDoubleClickLisnter = doubleClickLisnter;
    }

    private boolean isConsideredDoubleTap(MotionEvent firstDown,
                                          MotionEvent firstUp, MotionEvent secondDown) {
        if (!alwaysInBiggerTapRegion) {
            return false;
        }
        if (secondDown.getEventTime() - firstUp.getEventTime() > DOUBLE_TAP_TIMEOUT) {
            return false;
        }
        int deltaX = (int) firstDown.getX() - (int) secondDown.getX();
        int deltaY = (int) firstDown.getY() - (int) secondDown.getY();
        return (deltaX * deltaX + deltaY * deltaY < doubleTapSlopSquare);
    }

    public interface DoubleClickLisnter {
        public void onDoubleClick();
    }
}

