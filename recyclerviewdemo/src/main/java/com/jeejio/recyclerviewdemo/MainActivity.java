package com.jeejio.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.awesome.sdk.util.NetUtil;
import com.jeejio.recyclerviewdemo.java.APIService;
import com.jeejio.recyclerviewdemo.java.Course;
import com.jeejio.recyclerviewdemo.java.Teacher;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Course> courses = new ArrayList<>();
    private SmartRefreshLayout mSmartRefreshLayout;
        private MultiCourseAdapter mAdapter;
//    private CourseAdapter mAdapter;
    private int mPage = 2;
    private final String BASE_URL = "http://www.imooc.com/";
    private final int PAGE_SIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycleView);
        mSmartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        initListener();
        initData();
    }

    private void initListener() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 2;
                courses.clear();
                getRequest();
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage = mPage + 2;
                getRequest();
            }
        });
    }

    private void initData() {
        // 线性管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 网格管理器
//        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        // 瀑布流管理器
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        // 自定义ItemDecoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration.Builder().setPaintColor(Color.RED)
                .setDividerMarginLeft(40).setViewMarginTop(100).setViewMarginBottom(100).build());
        // 单布局
//        mAdapter = new CourseAdapter(courses);
//        mRecyclerView.setAdapter(mAdapter);

        // 多布局
        mAdapter = new MultiCourseAdapter(new ArrayList<>(),10);
//        mAdapter = new MultiCourseAdapter(courses);
        mRecyclerView.setAdapter(mAdapter);
        getRequest();
    }

    private void getRequest() {
        NetUtil.SINGLETON
                .getService(APIService.class, BASE_URL)
                .getInfoRx(mPage, PAGE_SIZE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Teacher>() {
                    @Override
                    public void onCompleted() {
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.finishLoadMore();
                        Log.i("Retrofit", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.finishLoadMore();
                        Log.i("Retrofit", "onError");
                    }

                    @Override
                    public void onNext(Teacher teacher) {
                        courses.addAll(teacher.getData());
                        mAdapter.notifyDataSetChanged();
                        for (int i = 0; i < teacher.getData().size(); i++) {
                            Log.i("Retrofit", teacher.getData().get(i).toString());
                        }
                    }
                });
    }
}