package com.lewinlee.ladapter;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lewinlee.ladapter.adapter.TestAdapter;

public class MainActivity extends AppCompatActivity {

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

        mockData();
    }

    private void mockData() {
        new Thread() {
            public void run() {
                SystemClock.sleep(10000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(10000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(10000);
                mHandler.sendEmptyMessage(1);
                SystemClock.sleep(10000);
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
