package com.lewinlee.ladapter_library;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewinlee.ladapter_library.base.LBaseAdapter;
import com.lewinlee.ladapter_library.base.LViewHolder;

/**
 * @author: ly
 * @date : 2017/11/28
 * @desc :
 */
public class LAdapter<K> extends LBaseAdapter<K, LViewHolder<K>> {

    /**
     * whether enable empty view.
     */
    private boolean isEnableEmptyView = true;
    /**
     * whether enable load more view.
     */
    private boolean isEnableLoadMoreView = true;
    /**
     * whether enable full screen loading view.
     */
    private boolean isEnableLoadingView = true;
    /**
     * whether enable end view.
     */
    private boolean isEnableEndView = true;
    /**
     * whether to reach the end.
     */
    private boolean isEnding;
    /**
     * viewType of empty view.
     */
    private static final int EMPTY_VIEW = 0x111;
    /**
     * instance of empty view.
     */
    private View mEmptyView;
    /**
     * viewType of load more view.
     */
    private static final int LOAD_MORE_VIEW = 0x222;
    /**
     * instance of load more view.
     */
    private View mLoadMoreView;
    /**
     * viewType of full screen loading view.
     */
    private static final int LOADING_VIEW = 0x333;
    /**
     * instance of loading view.
     */
    private View mLoadingView;
    /**
     * viewType of end view.
     */
    private static final int END_VIEW = 0x444;
    /**
     * instance of end view.
     */
    private View mEndView;

    @Override
    public LViewHolder<K> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW:
                return new LViewHolder<>(getItemView(parent, R.layout.loading_view));
            case LOAD_MORE_VIEW:

                break;
            case LOADING_VIEW:

                break;
            case END_VIEW:

                break;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(LViewHolder<K> holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        // has no data and enable one of two views
        if (isEmpty() && (isEnableEmptyView || isEnableLoadingView)) {
            return 1;
        }
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        // no data and position is 1
        if (position == 1 && isEmpty()) {
            // enable empty view and reach the end
            if (isEnableEmptyView && isEnding) {
                return EMPTY_VIEW;
            }
            // has not reach the end and enable loading view
            if (isEnableLoadingView) {
                return LOADING_VIEW;
            }
        }
        if (position == getDataSize()) {
            // reach the end show end view
            if (isEnding && isEnableEndView) {
                return END_VIEW;
            }
            // else show load more view
            if (!isEnding && isEnableLoadMoreView) {
                return LOAD_MORE_VIEW;
            }
        }
        return super.getItemViewType(position);
    }

    protected View getItemView(ViewGroup parent, @LayoutRes int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

}
