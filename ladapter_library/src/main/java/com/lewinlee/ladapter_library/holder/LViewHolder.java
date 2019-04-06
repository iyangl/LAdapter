package com.lewinlee.ladapter_library.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author: ly
 * @date : 2017/11/28
 * @desc :
 */
public class LViewHolder<K> extends RecyclerView.ViewHolder {

    public LViewHolder(View itemView) {
        super(itemView);
    }


    /**
     * add {@link com.lewinlee.ladapter_library.LAdapter.onItemClickListener} to view
     */
    public LViewHolder(View itemView, View.OnClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(listener);
    }

    public void bind(K data) {

    }
}
