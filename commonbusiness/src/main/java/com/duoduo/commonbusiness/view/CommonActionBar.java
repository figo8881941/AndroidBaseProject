package com.duoduo.commonbusiness.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duoduo.commonbase.view.DoubleClickView;
import com.duoduo.commonbusiness.R;

/**
 * 自定义actionbar组件
 *
 * @author wangzhuobin
 */
public class CommonActionBar extends DoubleClickView {

    private ImageView backButton;
    private TextView title;
    private TextView menuText;
    private LinearLayout mMenuContainer;

    public CommonActionBar(Context context) {
        super(context);
    }

    public CommonActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonActionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        backButton = (ImageView) findViewById(R.id.back_button);
        title = (TextView) findViewById(R.id.title);
        menuText = (TextView) findViewById(R.id.menu_text);
        mMenuContainer = (LinearLayout) findViewById(R.id.action_bar_menu_container);
    }

    public void setBackButtonOnClickListener(View.OnClickListener onClickListener) {
        if (backButton != null) {
            backButton.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(String title) {
        if (this.title != null) {
            this.title.setText(title);
        }
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public void setBackButton(ImageView backButton) {
        this.backButton = backButton;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getMenu() {
        return menuText;
    }

    public LinearLayout getMenuContainer() {
        return mMenuContainer;
    }

    public void destroy() {
        if (backButton != null) {
            backButton.setOnClickListener(null);
            backButton = null;
        }
        title = null;
    }
}

