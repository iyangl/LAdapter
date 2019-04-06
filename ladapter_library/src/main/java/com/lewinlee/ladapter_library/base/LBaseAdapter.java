package com.lewinlee.ladapter_library.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LBaseAdapter<K, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<K> mData;

    protected LBaseAdapter() {
        this(null);
    }

    protected LBaseAdapter(List<K> list) {
        mData = list == null ? new ArrayList<K>() : list;
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
        return mData.size();
    }

}
