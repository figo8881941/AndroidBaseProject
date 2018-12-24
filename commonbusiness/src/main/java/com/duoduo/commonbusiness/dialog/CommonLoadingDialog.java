package com.duoduo.commonbusiness.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.duoduo.commonbusiness.R;

public class CommonLoadingDialog extends Dialog {

    private ImageView loadingImg;
    private Animation animation;

    public CommonLoadingDialog(@NonNull Context context) {
        //设置对话框主题
        super(context, R.style.common_business_dialog_common_loading);
        init();
    }

    private void init() {
        setContentView(R.layout.commonbusiness_dialog_common_loading_layout);
        setCancelable(false);
        loadingImg = (ImageView) findViewById(R.id.loading_img);
        initAnimation();
    }

    private void initAnimation() {
        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin); //设置插值器
        animation.setDuration(500);//设置动画持续周期
        animation.setRepeatCount(-1);//设置重复次数
    }

    @Override
    public void show() {
        super.show();
        if (loadingImg != null) {
            loadingImg.startAnimation(animation);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (loadingImg != null) {
            loadingImg.clearAnimation();
        }
    }
}
