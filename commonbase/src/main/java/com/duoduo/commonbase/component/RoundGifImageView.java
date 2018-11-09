package com.duoduo.commonbase.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.duoduo.commonbase.R;

import pl.droidsonroids.gif.GifImageView;

/**
 * 圆角GifImageView
 */
public class RoundGifImageView extends GifImageView {

    private Path roundPath = new Path();
    private RectF rectF = new RectF();
    private float[] radiusArray = {25f, 25f, 25f, 25f, 25f, 25f, 25f, 25f};

    public RoundGifImageView(Context context) {
        super(context);
        init(context, null);
    }

    public RoundGifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoundGifImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundGifImageView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundGifImageView);
        float radius = a.getDimensionPixelSize(R.styleable.RoundGifImageView_radius, 25);
        radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        a.recycle();
    }

    public void setRadius(float radius) {
        radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        roundPath.reset();
        rectF.set(0, 0, getWidth(), getHeight());
        roundPath.addRoundRect(rectF, radiusArray, Path.Direction.CW);
        canvas.clipPath(roundPath);
        super.onDraw(canvas);
        canvas.restore();
    }
}
