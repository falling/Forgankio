package com.example.falling.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.falling.myapplication.R;
import com.example.falling.myapplication.adapter.AdapterFactory;
import com.example.falling.myapplication.adapter.RecyclerViewAdapter;
import com.example.falling.myapplication.TestBean;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private List<TestBean> mContentItems = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initSwipeRefreshLayout(view);


        mAdapter = AdapterFactory.getInstance(mContentItems);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        {
            //读取缓存的操作。。
            for (int i = 0; i < 10; ++i) {
                mContentItems.add(new TestBean(i+"岁"));
            }
            mAdapter.notifyDataSetChanged();
        }

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    private void initSwipeRefreshLayout(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    public void setContentItems(List<TestBean> contentItems) {
        mContentItems.clear();
        mContentItems = contentItems;
        mAdapter.notifyDataSetChanged();
    }

    public void addObejct(Object object) {
        mContentItems.add(new TestBean());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i = 23;i<30;i++) {
                    TestBean bean = new TestBean(i+"岁");
                    mContentItems.add(i-23,bean);
                }
                mAdapter.notifyItemInserted(1);//最前面开始更新
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onItemClick(View view, Object data) {
        Toast.makeText(view.getContext(), ((TestBean)data).getAge(), Toast.LENGTH_SHORT).show();
    }
}
