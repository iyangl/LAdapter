package com.lewinlee.ladapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lewinlee.ladapter.adapter.TestAdapter;
import com.lewinlee.ladapter.api.ServiceGenerator;
import com.lewinlee.ladapter.api.TestService;
import com.lewinlee.ladapter.util.BaseSubscriber;
import com.lewinlee.ladapter.util.RxUtil;
import com.lewinlee.ladapter_library.LAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new TestAdapter();
        recycler.setAdapter(adapter);

        adapter.setNewData(Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"));

        adapter.bindToRecyclerView(recycler);
        adapter.setOnLoadMoreListener(new LAdapter.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addDataList(Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"));
                    }
                }, 2000);
            }
        });

        TestService service = ServiceGenerator.getService(TestService.class);
        service.live().compose(RxUtil.<List<String>>rxSchedulerHelper())
                .subscribe(new BaseSubscriber<List<String>>() {
                    @Override
                    public void onNext(List<String> strings) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                });
    }

}
