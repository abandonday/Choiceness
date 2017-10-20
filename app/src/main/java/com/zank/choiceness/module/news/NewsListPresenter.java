package com.zank.choiceness.module.news;

import com.zank.choiceness.base.IBasePresenter;

/**
 * Created by Zank on 2017/10/20.
 */

public class NewsListPresenter implements IBasePresenter {

    private INewsListView mView;

    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(INewsListView mView, String mNewsId) {
        this.mView = mView;
        this.mNewsId = mNewsId;
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}
