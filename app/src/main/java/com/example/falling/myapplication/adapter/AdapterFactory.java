package com.example.falling.myapplication.adapter;

import com.example.falling.myapplication.TestBean;

import java.util.List;

/**
 * Created by falling on 16/5/8.
 */
public class AdapterFactory {
    public static RecyclerViewAdapter getInstance(List<TestBean> content){
        return new RecyclerViewAdapter(content);
    }
}
