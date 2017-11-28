package com.lewinlee.ladapter_library.base;

import android.view.ViewGroup;

/**
 * @author: ly
 * @date : 2017/11/28
 * @desc :
 */
public class LAdapter<K> extends LBaseAdapter<K, LViewHolder<K>> {

    /**
     * whether enable empty view.
     */
    private boolean isEnableEmptyView;
    /**
     * whether enable load more view.
     */
    private boolean isEnableLoadMore;
    /**
     * whether enable full screen loading view.
     */
    private boolean isEnableLoadingView;
    /**
     * viewType of empty view.
     */
    private static final int EMPTY_VIEW = 0x111;
    /**
     * viewType of load more view.
     */
    private static final int LOAD_MORE_VIEW = 0x222;
    /**
     * viewType of full screen loading view.
     */
    private static final int LOADING_VIEW = 0x333;

    @Override
    public LViewHolder<K> onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(LViewHolder<K> holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }


}
