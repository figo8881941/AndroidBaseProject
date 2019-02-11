package com.duoduo.commonbusiness.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoduo.commonbusiness.R;


public class CommonErrorView extends FrameLayout {

	private TextView mBtnRefresh;
	private ImageView mLoadingView;

	public CommonErrorView(Context context) {
		super(context);
	}

	public CommonErrorView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CommonErrorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
        mBtnRefresh = (TextView) findViewById(R.id.btn_refresh);
        mLoadingView = (ImageView) findViewById(R.id.loading_view);
	}

	public void setRefrshBtClickListner(OnClickListener clickListener) {
        mBtnRefresh.setOnClickListener(clickListener);
	}

	public void startLoading() {
        mBtnRefresh.setVisibility(INVISIBLE);
        mLoadingView.setVisibility(VISIBLE);
        mLoadingView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.commonbusiness_common_error_view_loading_anim));
	}

    public void show() {
        setVisibility(VISIBLE);
        mBtnRefresh.setVisibility(VISIBLE);
        mLoadingView.setVisibility(INVISIBLE);
        mLoadingView.clearAnimation();
    }

    public void hide() {
        mLoadingView.clearAnimation();
        setVisibility(INVISIBLE);
    }

	public void clean() {
        mLoadingView.clearAnimation();
    }
	
}
