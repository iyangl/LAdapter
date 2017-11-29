package com.lewinlee.ladapter;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lewinlee.ladapter.adapter.TestAdapter;
import com.lewinlee.ladapter_library.LAdapter;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    private RecyclerView recycler;
    private TestAdapter adapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            addData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new TestAdapter();
        recycler.setAdapter(adapter);

        adapter.bindToRecyclerView(recycler);
        adapter.setOnLoadMoreListener(new LAdapter.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mHandler.sendEmptyMessageDelayed(1, 3000);
                if (count == 5) {
                    adapter.setEnding(true);
                } else {
                    count++;
                }
            }
        });
    }

    private void mockData() {
        new Thread() {
            public void run() {
                SystemClock.sleep(2000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(2000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(2000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(2000);
                mHandler.sendEmptyMessage(1);
                adapter.setEnding(true);
            }
        }.start();
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            adapter.addData(String.valueOf(i));
        }
        adapter.notifyDataSetChanged();
    }
}
