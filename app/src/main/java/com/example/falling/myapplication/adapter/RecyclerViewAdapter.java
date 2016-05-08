package com.example.falling.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falling.myapplication.R;
import com.example.falling.myapplication.TestBean;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , Object data);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    List<TestBean> contents;

    static final int TYPE_HEADER = 0;

    public RecyclerViewAdapter(List<TestBean> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new ViewHolder(view);
            }
            default: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                //将创建的View注册点击事件
                view.setOnClickListener(this);
                return new ViewHolder(view);

            }
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        TestBean bean = contents.get(position);
        switch (position) {
            case TYPE_HEADER:
                viewHolder.name.setText(bean.getName());
                viewHolder.age.setText(bean.getAge() + "岁");
                viewHolder.sex.setText(bean.getSex());
                break;
            default:
                viewHolder.name.setText("positon:"+position + "||" + bean.getAge());
                viewHolder.itemView.setTag(bean);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        TextView name;
        TextView age;
        TextView sex;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            sex = (TextView) itemView.findViewById(R.id.sex);
        }
    }
}