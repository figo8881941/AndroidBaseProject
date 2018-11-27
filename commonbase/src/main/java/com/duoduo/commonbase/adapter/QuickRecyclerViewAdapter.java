package com.duoduo.commonbase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

/**
 * 快捷的RecyclerViewAdapter
 *
 * @param <T>
 */
public abstract class QuickRecyclerViewAdapter<T> extends RecyclerView.Adapter<QuickRecyclerViewAdapter.QuickerViewHolder> {

    protected List<T> data;

    protected Context context;

    protected LayoutInflater layoutInflater;

    public QuickRecyclerViewAdapter(Context context) {
        this.context = context.getApplicationContext();
        layoutInflater = LayoutInflater.from(this.context);
    }

    /**
     * 获取ViewLayoutId
     *
     * @param viewType
     * @return
     */
    public abstract int getViewLayoutId(int viewType);

    @NonNull
    @Override
    public QuickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = getViewLayoutId(viewType);
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new QuickerViewHolder(itemView);
    }

    /**
     * 绑定数据的方法
     *
     * @param holder
     * @param itemData
     * @param position
     */
    public abstract void bindViewHolder(QuickerViewHolder holder, T itemData, int position);

    @Override
    public void onBindViewHolder(@NonNull QuickerViewHolder holder, int position) {
        bindViewHolder(holder, data.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static class QuickerViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views = new SparseArray<View>();

        private HashMap<View, SparseArray<View>> viewViews = new HashMap<View, SparseArray<View>>();

        public QuickerViewHolder(View itemView) {
            super(itemView);
        }

        public View getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = ((ViewGroup) itemView).findViewById(id);
                if (view != null) {
                    views.append(id, view);
                }
            }
            return view;
        }

        public View getView(View view, int id) {
            SparseArray<View> views = viewViews.get(view);
            if (views == null) {
                views = new SparseArray<View>();
                viewViews.put(view, views);
            }
            View returnView = views.get(id);
            if (returnView == null) {
                returnView = ((ViewGroup) view).findViewById(id);
                if (returnView != null) {
                    views.append(id, returnView);
                }
            }
            return returnView;
        }
    }
}
