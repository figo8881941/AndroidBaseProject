package com.duoduo.main.classify.subclassify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifySubTabDataBean;

/**
 * 子分类Fragment
 */
public class ClassifySubTabFragment extends BaseFragment<ClassifySubTabDataBean.CategoryNewListEntity> {

    private ViewGroup mainView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_tab_fragment, container, false);
        initView();
        return mainView;
    }

    private void initView() {
        TextView name = (TextView) mainView.findViewById(R.id.name);
        if (data != null) {
            name.setText(data.getCategoryName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
