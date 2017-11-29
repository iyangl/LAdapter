package com.lewinlee.ladapter_library;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewinlee.ladapter_library.base.LBaseAdapter;
import com.lewinlee.ladapter_library.base.LViewHolder;

import java.util.List;

/**
 * @author: ly
 * @date : 2017/11/28
 * @desc :
 */
public abstract class LAdapter<K> extends LBaseAdapter<K, LViewHolder<K>>
        implements View.OnClickListener {

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
     * custom layout res of empty view.
     */
    @LayoutRes
    private int mEmptyLayoutRes;
    /**
     * viewType of load more view.
     */
    private static final int LOAD_MORE_VIEW = 0x222;
    /**
     * instance of load more view.
     */
    private View mLoadMoreView;
    /**
     * custom layout res of load more view.
     */
    @LayoutRes
    private int mLoadMoreLayoutRes;
    /**
     * viewType of full screen loading view.
     */
    private static final int LOADING_VIEW = 0x333;
    /**
     * instance of loading view.
     */
    private View mLoadingView;
    /**
     * custom layout res of full screen loading view.
     */
    @LayoutRes
    private int mLoadingLayoutRes;
    /**
     * viewType of end view.
     */
    private static final int END_VIEW = 0x444;
    /**
     * instance of end view.
     */
    private View mEndView;
    /**
     * custom layout res of end view.
     */
    @LayoutRes
    private int mEndLayoutRes;
    /**
     * a state means whether is load data
     */
    private boolean isLoadingData;

    @Override
    public LViewHolder<K> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW:
                return new LViewHolder<>(createEmptyView(parent, viewType));
            case LOAD_MORE_VIEW:
                return new LViewHolder<>(createLoadMoreView(parent, viewType));
            case LOADING_VIEW:
                return new LViewHolder<>(createLoadingView(parent, viewType));
            case END_VIEW:
                return new LViewHolder<>(createEndView(parent, viewType));
        }
        return onHolderCreate(parent, viewType);
    }

    protected abstract LViewHolder<K> onHolderCreate(ViewGroup parent, int viewType);

    protected abstract void onHolderBind(LViewHolder<K> holder, int position);

    @Override
    public void onBindViewHolder(LViewHolder<K> holder, int position) {
        holder.itemView.setTag(position);
        onHolderBind(holder, position);
    }

    @Override
    public int getItemCount() {
        // has no data and enable one of two views
        if (isEmpty() && (isEnableEmptyView || isEnableLoadingView)) {
            return 1;
        }
        // enable load more view or end view, add bottom to RecyclerView
        if (isEnableLoadMoreView || isEnableEndView) {
            return getDataSize() + 1;
        }
        return getDataSize();
    }

    @Override
    public int getItemViewType(int position) {
        // no data and position is 0
        if (position == 0 && isEmpty()) {
            // now is loading and enable loading view
            if (isEnableLoadingView && isLoadingData) {
                return LOADING_VIEW;
            }
            // enable empty view and reach the end or is not load data
            if (isEnableEmptyView && isEnding || !isLoadingData) {
                return EMPTY_VIEW;
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

    /**
     * @return custom empty view if existed, or default empty view
     */
    private View createEmptyView(ViewGroup parent, int viewType) {
        return mEmptyView = mEmptyView == null ? getDefaultItemView(parent, viewType) : mEmptyView;
    }

    /**
     * @return custom loading view if existed, or default loading view
     */
    private View createLoadingView(ViewGroup parent, int viewType) {
        return mLoadingView = mLoadingView == null ? getDefaultItemView(parent, viewType) : mLoadingView;
    }

    /**
     * @return custom load more view if existed, or default load more view
     */
    private View createLoadMoreView(ViewGroup parent, int viewType) {
        return mLoadMoreView = mLoadMoreView == null ? getDefaultItemView(parent, viewType) : mLoadMoreView;
    }

    /**
     * @return custom end view if existed, or default end view
     */
    private View createEndView(ViewGroup parent, int viewType) {
        return mEndView = mEndView == null ? getDefaultItemView(parent, viewType) : mEndView;
    }

    @LayoutRes
    public int getEmptyLayoutRes() {
        return mEmptyLayoutRes == 0 ? R.layout.empty_view : mEmptyLayoutRes;
    }

    @LayoutRes
    public int getLoadingLayoutRes() {
        return mLoadingLayoutRes == 0 ? R.layout.loading_view : mLoadingLayoutRes;
    }

    @LayoutRes
    public int getLoadMoreLayoutRes() {
        return mLoadMoreLayoutRes == 0 ? R.layout.load_more_view : mLoadMoreLayoutRes;
    }

    @LayoutRes
    public int getEndLayoutRes() {
        return mEndLayoutRes == 0 ? R.layout.end_view : mEndLayoutRes;
    }

    public void setEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
    }

    public void setEmptyLayoutRes(int mEmptyLayoutRes) {
        this.mEmptyLayoutRes = mEmptyLayoutRes;
    }

    public void setLoadMoreView(View mLoadMoreView) {
        this.mLoadMoreView = mLoadMoreView;
    }

    public void setLoadMoreLayoutRes(int mLoadMoreLayoutRes) {
        this.mLoadMoreLayoutRes = mLoadMoreLayoutRes;
    }

    public void setmEndView(View mEndView) {
        this.mEndView = mEndView;
    }

    public void setLoadingLayoutRes(int mLoadingLayoutRes) {
        this.mLoadingLayoutRes = mLoadingLayoutRes;
    }

    public void setLoadingView(View mLoadingView) {
        this.mLoadingView = mLoadingView;
    }

    public void setEndLayoutRes(int mEndLayoutRes) {
        this.mEndLayoutRes = mEndLayoutRes;
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public View getLoadMoreView() {
        return mLoadMoreView;
    }

    public View getLoadingView() {
        return mLoadingView;
    }

    public View getEndView() {
        return mEndView;
    }

    public boolean isEnding() {
        return isEnding;
    }

    public void setEnding(boolean ending) {
        isEnding = ending;
    }

    protected View getItemView(ViewGroup parent, @LayoutRes int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

    private View getDefaultItemView(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_VIEW:
                return getItemView(parent, getEmptyLayoutRes());
            case LOADING_VIEW:
                return getItemView(parent, getLoadingLayoutRes());
            case LOAD_MORE_VIEW:
                return getItemView(parent, getLoadMoreLayoutRes());
            case END_VIEW:
                return getItemView(parent, getEndLayoutRes());
            default:
                return null;
        }
    }


    public void setNewData(List<K> dataList) {
        super.setNewData(dataList);
        isEnding = false;
    }

    public void addData(K data) {
        super.addData(data);
    }

    public void addDataList(List<K> dataList) {
        super.addDataList(dataList);
        notifyDataSetChanged();
    }

    public K getData(int position) {
        return super.getData(position);
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView == null) {
            throw new RuntimeException("can not bind a RecyclerView's value is null ");
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int currentState = 10010;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                currentState = newState;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (currentState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager != null &&
                            layoutManager instanceof LinearLayoutManager) {
                        int itemPosition = ((LinearLayoutManager) layoutManager)
                                .findLastCompletelyVisibleItemPosition();
                        if (itemPosition == getItemCount() - 1) {
                            // start pull up load more
                            if (onLoadMoreListener != null) {
                                onLoadMoreListener.onLoadMore();
                            }
                        }
                    }
                }
            }
        });
    }

    public static interface onLoadMoreListener {
        void onLoadMore();
    }

    private onLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(LAdapter.onLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public static interface onItemClickListener {
        void onClick(int position);
    }

    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(LAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onClick((int) view.getTag());
        }
    }
}
