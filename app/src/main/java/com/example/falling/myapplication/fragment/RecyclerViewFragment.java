package com.example.falling.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.falling.myapplication.R;
import com.example.falling.myapplication.RecyclerViewAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<Object> mContentItems = new ArrayList<>();

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

        mAdapter = new RecyclerViewMaterialAdapter(new RecyclerViewAdapter(mContentItems));
        mRecyclerView.setAdapter(mAdapter);

        {
            //读取缓存的操作。。
            for (int i = 0; i < 10; ++i)
                mContentItems.add(new Object());
            mAdapter.notifyDataSetChanged();
        }

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }


    public void setContentItems(List<Object> contentItems) {
        mContentItems = contentItems;
        mAdapter.notifyDataSetChanged();
    }

    public void addObejct(Object object){
        mContentItems.add(new Object());
        mAdapter.notifyDataSetChanged();
    }
}
