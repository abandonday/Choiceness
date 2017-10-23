package com.zank.choiceness.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zank.choiceness.adapter.item.NewsMultiItem;

import java.util.List;

/**
 * Created by Zank on 2017/10/23.
 */

public class NewsMultiListAdapter extends BaseMultiItemQuickAdapter{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewsMultiListAdapter(List data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
