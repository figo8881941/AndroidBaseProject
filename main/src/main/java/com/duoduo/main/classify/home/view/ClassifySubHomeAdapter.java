package com.duoduo.main.classify.home.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.main.R;

import java.util.List;

/**
 * 分类首页adapter
 */
public class ClassifySubHomeAdapter extends RecyclerView.Adapter {

    private enum ITEM_TYPE {
        HEADER_ITEM_TYPE,
        FOOTER_ITEM_TYPE,
        LIST_ITEM_TYPE
    }

    private Context context;

    private View headerView;
    private RecyclerView.ViewHolder headerViewHolder;

    private View footerView;
    private RecyclerView.ViewHolder footerViewHolder;

    private LayoutInflater layoutInflater;

    private List<Object> data;

    public ClassifySubHomeAdapter(Context context) {
        super();
        this.context = context.getApplicationContext();
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER_ITEM_TYPE.ordinal();
        } else if (data != null && !data.isEmpty() && position <= data.size()) {
            return ITEM_TYPE.LIST_ITEM_TYPE.ordinal();
        } else {
            return ITEM_TYPE.FOOTER_ITEM_TYPE.ordinal();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER_ITEM_TYPE.ordinal()) {
            return headerViewHolder;
        } else if (viewType == ITEM_TYPE.FOOTER_ITEM_TYPE.ordinal()) {
            return footerViewHolder;
        } else {
            View itemView = layoutInflater.inflate(R.layout.main_common_two_list_item_layout, parent, false);
            return new ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE.HEADER_ITEM_TYPE.ordinal()) {
            return;
        } else if (viewType == ITEM_TYPE.FOOTER_ITEM_TYPE.ordinal()) {
            return;
        } else {

        }
    }

    @Override
    public int getItemCount() {
        int count = 2;
        if (data != null) {
            count += data.size();
        }
        return count;
    }

    public View getHeaderView() {
        return headerView;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        this.headerViewHolder = new RecyclerView.ViewHolder(headerView) {
        };
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
        this.footerViewHolder = new RecyclerView.ViewHolder(footerView) {
        };
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
