package com.wolfgy.expandablerecyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wolfgy.expandablerecyclerviewdemo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用一层recyclerView实现点击展开二层布局效果
 * Created by wolfgy on 2017/10/16.
 */

public class TwoRecyclerViewAdapter  extends RecyclerView.Adapter {

    private List<String> list = new ArrayList<>();//存储数据
    private List<Integer>  secondViews = new ArrayList<>();//存储第二层布局的position。用于设置ViewType
    private List<Integer> hasExpand = new ArrayList<>();//存储被点击展开过的item的position
    private static final int TYPE_FIRST = 0;//第一层布局
    private static final int TYPE_SECOND = 1;//第二层布局

    public TwoRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据ViewType实例化布局
        switch (viewType){
            case TYPE_FIRST:
                return new FirstViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false));
            case TYPE_SECOND:
                return new SecondViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_detail,parent,false));
        }
        return new FirstViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        //通过secondViews中的内容设置ViewType
        for (int i : secondViews) {
            if (i == position){
                return TYPE_SECOND;
            }
        }
        return TYPE_FIRST;
    }

    private class FirstViewHolder extends RecyclerView.ViewHolder{

        public FirstViewHolder(final View itemView) {
            super(itemView);
            //item点击事件监听
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int flag = 1;//flag用于判断当前点击是展开子布局还是折叠子布局
                    int subViewPosition = getLayoutPosition()+1;//子布局添加后对应的position
                    //判断当前被点击的item是否已经展开过
                    for (int i :hasExpand) {
                        if (i == getLayoutPosition()){
                            flag = 2;
                            break;
                        }
                    }
                    //flag为1时展开子布局，否则折叠子布局
                    if (flag == 1){
                        //往list中存储数据
                        list.add(subViewPosition,"新增布局");
                        //将当前点击item的position放入已展开集合
                        hasExpand.add(getLayoutPosition());
                        //根据当前点击item的position修改hasExpand中存储的相应数据(因为往中间横插了item,所以原来集合中保存的position已经过时了，要做相应的更新)
                        for (int i = 0 ; i < hasExpand.size() ; i++){
                            int num = hasExpand.get(i);
                            if ( num > getLayoutPosition() ){
                                hasExpand.set(i,num+=1);
                            }
                        }
                        //根据当前展开的子布局position修改secondViews中存储的相应数据(因为往中间横插了item,所以原来集合中保存的position已经过时了，要做相应的更新)
                        for (int i = 0 ; i < secondViews.size() ; i++){
                            int num = secondViews.get(i);
                            if ( num > subViewPosition ){
                                secondViews.set(i,num+=1);
                            }
                        }
                        //将当前展开子布局position存入secondViews
                        secondViews.add(subViewPosition);
                        notifyItemInserted(subViewPosition);
                    }else{
                        //往list中删除对应数据数据
                        list.remove(subViewPosition);
                        //将当前点击item的position从已展开集合中移除
                        hasExpand.remove((Integer)getLayoutPosition());
                        //根据当前点击item的position修改hasExpand中存储的相应数据(因为从中间删除了item,所以原来集合中保存的position已经过时了，要做相应的更新)
                        for (int i = 0 ; i < hasExpand.size() ; i++){
                            int num = hasExpand.get(i);
                            if ( num > getLayoutPosition() ){
                                hasExpand.set(i,num-=1);
                            }
                        }
                        //根据当前折叠的子布局position修改secondViews中存储的相应数据(因为从中间删除了item,所以原来集合中保存的position已经过时了，要做相应的更新)
                        for (int i = 0 ; i < secondViews.size() ; i++){
                            int num = secondViews.get(i);
                            if ( num > subViewPosition ){
                                secondViews.set(i,num-=1);
                            }
                        }
                        //将当前折叠的子布局position从secondViews中删除
                        secondViews.remove((Integer) subViewPosition);
                        notifyItemRemoved(subViewPosition);
                    }
                }
            });
        }
    }

    private class SecondViewHolder extends RecyclerView.ViewHolder{

        public SecondViewHolder(View itemView) {
            super(itemView);
        }
    }

}
