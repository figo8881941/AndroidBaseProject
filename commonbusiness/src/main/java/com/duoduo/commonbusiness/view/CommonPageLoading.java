package com.duoduo.commonbusiness.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.duoduo.commonbusiness.R;


public class CommonPageLoading extends LinearLayout {

    private View animView;

    public CommonPageLoading(Context context) {
        super(context);
    }

    public CommonPageLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonPageLoading(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        animView = findViewById(R.id.gifView);
    }

    @Override
    public void setVisibility(int visibility) {
        if (animView != null) {
            if (visibility == GONE || visibility == INVISIBLE) {
                animView.setVisibility(GONE);
            } else {
                animView.setVisibility(VISIBLE);
            }
        }
        super.setVisibility(visibility);
    }
}
