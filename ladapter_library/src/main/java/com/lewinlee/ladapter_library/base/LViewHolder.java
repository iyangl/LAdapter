package com.lewinlee.ladapter_library.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lewinlee.ladapter_library.LAdapter;

/**
 * @author: ly
 * @date : 2017/11/28
 * @desc :
 */
public class LViewHolder<K> extends RecyclerView.ViewHolder {
    private LAdapter adapter;

    public LViewHolder(View itemView) {
        super(itemView);
    }


    /**
     * add {@link com.lewinlee.ladapter_library.LAdapter.onItemClickListener} to view
     */
    public LViewHolder(View itemView, LAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        itemView.setOnClickListener(adapter);
    }

    public void bind(K data) {

    }
}
