package com.duoduo.commonbase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * 快捷的RecyclerViewAdapter
 * 包含headerview和footerview
 */
public abstract class QuickHeaderFooterRecyclerViewAdapter<T> extends QuickRecyclerViewAdapter<T> {

    private enum ITEM_TYPE {
        ITEM_TPYE_HEADER,
        ITEM_TPYE_FOOTER,
        ITEM_TPYE_NORMAL
    }

    protected View headerView;

    protected View footerView;

    public QuickHeaderFooterRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            return ITEM_TYPE.ITEM_TPYE_HEADER.ordinal();
        } else if (footerView != null && position == getItemCount() - 1) {
            return ITEM_TYPE.ITEM_TPYE_FOOTER.ordinal();
        }
        return ITEM_TYPE.ITEM_TPYE_NORMAL.ordinal();
    }

    @NonNull
    @Override
    public QuickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TPYE_HEADER.ordinal()) {
            return new QuickerViewHolder(headerView) {
            };
        } else if (viewType == ITEM_TYPE.ITEM_TPYE_FOOTER.ordinal()) {
            return new QuickerViewHolder(footerView) {
            };
        } else if (viewType == ITEM_TYPE.ITEM_TPYE_NORMAL.ordinal()) {
            return super.onCreateViewHolder(parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuickerViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == ITEM_TYPE.ITEM_TPYE_HEADER.ordinal()) {
            return;
        } else if (viewType == ITEM_TYPE.ITEM_TPYE_FOOTER.ordinal()) {
            return;
        } else if (viewType == ITEM_TYPE.ITEM_TPYE_NORMAL.ordinal()) {
            int dataIndex = position;
            if (headerView != null) {
                dataIndex--;
            }
            bindViewHolder(holder, data.get(dataIndex), position);
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
        count += super.getItemCount();
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
}
