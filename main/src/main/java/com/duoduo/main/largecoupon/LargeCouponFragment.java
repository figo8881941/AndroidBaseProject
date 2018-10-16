package com.duoduo.main.largecoupon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;

/**
 * 大额券Fragment
 */
public class LargeCouponFragment extends BaseFragment {

    private ViewGroup mainView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_large_coupon_fragment, container, false);
        return mainView;
    }
}
