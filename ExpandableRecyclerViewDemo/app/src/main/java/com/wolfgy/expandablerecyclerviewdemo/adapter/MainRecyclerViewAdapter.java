package com.wolfgy.expandablerecyclerviewdemo.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wolfgy.expandablerecyclerviewdemo.R;


/**
 * 使用LinearLayout嵌套RecyclerView实现点击展开二层布局效果
 * Created by wolgy on 2017/10/8.
 * 参考:http://blog.csdn.net/zz110731/article/details/53307758
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder> {


    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        MainRecyclerViewHolder viewHolder = new MainRecyclerViewHolder(item);
        viewHolder.setIsRecyclable(false);//取消viewHolder的重用机制。没有这句话子布局subView会被重复添加。

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    protected class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textTime, textPrice;

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);
            textTime = itemView.findViewById(R.id.text_first_time);
            textPrice = itemView.findViewById(R.id.text_first_price);
            //item点击事件监听
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int flag = 1;//用于判断当前是展开还是收缩状态
                    //获取外层linearlayout布局
                    LinearLayout linearLayout = view.findViewById(R.id.main_tree_root_layout);
                    //new一个RecyclerView来当展开的子布局。
                    RecyclerView subView = new RecyclerView(view.getContext());
                    SubViewAdapter adapter = new SubViewAdapter();
                    subView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    subView.setAdapter(adapter);
                    //当flag不为空的时候,获取flag的值。
                    if (linearLayout.getTag() != null) {
                        flag = (int) linearLayout.getTag();
                    }
                    //当flag为1时，添加子布局。否则，移除子布局。
                    if (flag == 1) {
                        linearLayout.addView(subView);
                        subView.setTag(101);
                        linearLayout.setTag(2);
                    } else {
                        linearLayout.removeView(view.findViewWithTag(101));
                        linearLayout.setTag(1);
                    }
                }
            });
        }
    }

    private class SubViewAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SubViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_detail,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        private class SubViewHolder extends RecyclerView.ViewHolder{
            private SubViewHolder(View itemView){
                super(itemView);
            }
        }
    }
}
