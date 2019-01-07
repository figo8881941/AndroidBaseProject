package com.duoduo.commonbase.glide;

import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

/**
 * RecyclerView滚动停止加载图片，停止滚动加载图片的Glide监听器
 */
public class GlideRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private RequestManager manager;

    public GlideRecyclerViewScrollListener(RequestManager manager) {
        this.manager = manager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (manager == null) {
            return;
        }
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE : {
                manager.resumeRequests();
            }
            break;
            case RecyclerView.SCROLL_STATE_DRAGGING : {
                manager.pauseAllRequests();
            }
            break;
            case RecyclerView.SCROLL_STATE_SETTLING : {
                manager.pauseAllRequests();
            }
            break;
        }
    }

}
