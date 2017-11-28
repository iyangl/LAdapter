package com.lewinlee.ladapter_library.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LBaseAdapter<K, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<K> mData;

    public LBaseAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected void setNewData(List<K> dataList) {
        mData.clear();
        mData = dataList;
    }

    protected void setData(K data) {
        mData.add(data);
    }

    protected void setDataList(List<K> dataList) {
        mData.addAll(dataList);
    }

    protected boolean isEmpty() {
        return mData != null && mData.size() == 0;
    }

    protected boolean isNotEmpty() {
        return !isEmpty();
    }

    protected int getDataSize() {
        return mData.size();
    }
}
