package com.duoduo.main.classify.subclassify;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.commonbusiness.fragment.BaseLoadingDialogFragment;
import com.duoduo.main.R;
import com.duoduo.main.base.data.TopicTwoProductListEntity;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.home.view.ClassifySubHomeAdapter;
import com.duoduo.main.classify.subclassify.presenter.ClassifySubTabPresenter;
import com.duoduo.main.classify.subclassify.presenter.IClassifySubTabPresenter;
import com.duoduo.main.classify.subclassify.view.IClassifySubTabView;

import java.util.List;

/**
 * 子分类Fragment
 */
public class ClassifySubTabFragment
        extends BaseLoadingDialogFragment<ClassifySubTabEntity.CategoryNewListEntity>
        implements IClassifySubTabView {

    private IClassifySubTabPresenter classifySubTabPresenter;

    private ViewGroup mainView;

    private RecyclerView recyclerView;

    private ClassifySubHomeAdapter recyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classifySubTabPresenter = new ClassifySubTabPresenter(getActivity().getApplicationContext(), this, data);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_sub_tab_fragment, container, false);
        initView();
        return mainView;
    }

    private void initView() {
        final Context context = getContext().getApplicationContext();

        recyclerAdapter = new ClassifySubHomeAdapter(context);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void updateListByProductData(List<TopicTwoProductListEntity> entities) {
        if (isDestroy) {
            return;
        }
        recyclerAdapter.setData(entities);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected() {
        super.onSelected();
        //如果被选中，而且当前没有数据，也没有正在进行请求，就请求数据
        if (classifySubTabPresenter != null) {
            classifySubTabPresenter.handleSelected();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (classifySubTabPresenter != null) {
            classifySubTabPresenter.destroy();
            classifySubTabPresenter = null;
        }

        mainView = null;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }
        recyclerAdapter = null;
    }

}
