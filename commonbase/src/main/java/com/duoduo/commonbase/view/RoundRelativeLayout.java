package com.duoduo.commonbase.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.duoduo.commonbase.R;

/**
 * 圆角RelativeLayout
 */
public class RoundRelativeLayout extends RelativeLayout {

    private Path roundPath = new Path();
    private RectF rectF = new RectF();
    private float[] radiusArray = {25f, 25f, 25f, 25f, 25f, 25f, 25f, 25f};

    public RoundRelativeLayout(Context context) {
        super(context);
        init(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundRelativeLayoutStyleable);
        float radius = a.getDimensionPixelSize(R.styleable.RoundRelativeLayoutStyleable_layoutRadius, 25);
        radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        a.recycle();
    }

    public void setRadius(float radius) {
        radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        roundPath.reset();
        rectF.set(0, 0, getWidth(), getHeight());
        roundPath.addRoundRect(rectF, radiusArray, Path.Direction.CW);
        canvas.clipPath(roundPath);
        super.draw(canvas);
        canvas.restore();
    }
}
