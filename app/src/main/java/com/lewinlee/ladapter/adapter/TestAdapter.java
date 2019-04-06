package com.lewinlee.ladapter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewinlee.ladapter.R;
import com.lewinlee.ladapter_library.LAdapter;
import com.lewinlee.ladapter_library.holder.LViewHolder;

/**
 * @author: ly
 * @date : 2017/11/29
 * @desc :
 */
public class TestAdapter extends LAdapter<String> {

    @Override
    protected LViewHolder<String> onHolderCreate(ViewGroup parent, int viewType) {
        return new TestViewHolder(getItemView(parent, R.layout.item_test));
    }

    @Override
    protected void onHolderBind(LViewHolder<String> holder, int position) {
        if (position < getDataSize()) {
            holder.bind(getData(position));
        }
    }

    static class TestViewHolder extends LViewHolder<String> {
        private TextView textView;

        public TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }

        @Override
        public void bind(String data) {
            textView.setText(data);
        }
    }
}
