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

    private View footerView;

    private LayoutInflater layoutInflater;

    private List<Object> data;

    public ClassifySubHomeAdapter(Context context) {
        super();
        this.context = context.getApplicationContext();
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            return ITEM_TYPE.HEADER_ITEM_TYPE.ordinal();
        } else if (footerView != null && position == getItemCount() - 1) {
            return ITEM_TYPE.FOOTER_ITEM_TYPE.ordinal();
        }
        return ITEM_TYPE.LIST_ITEM_TYPE.ordinal();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER_ITEM_TYPE.ordinal()) {
            return new RecyclerView.ViewHolder(headerView) {
            };
        } else if (viewType == ITEM_TYPE.FOOTER_ITEM_TYPE.ordinal()) {
            return new RecyclerView.ViewHolder(footerView) {
            };
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
        int count = 0;
        if (headerView != null) {
            count++;
        }
        if (footerView != null) {
            count++;
        }
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
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
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
