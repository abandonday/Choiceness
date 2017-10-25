package com.zank.choiceness.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.zank.choiceness.R;
import com.zank.choiceness.adapter.item.NewsMultiItem;
import com.zank.choiceness.api.bean.NewsInfo;
import com.zank.choiceness.base.BaseFragment;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.injector.components.DaggerNewsListComponent;
import com.zank.choiceness.injector.modules.NewsListModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView{

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";

    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;

    @Inject
    BaseQuickAdapter mAdapter;

    private String mNewsId;



    public static NewsListFragment newInstance(String newsId){
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TYPE_KEY, newsId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mNewsId = getArguments().getString(NEWS_TYPE_KEY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initInjector() {
        DaggerNewsListComponent.builder()
                .appComponent(getAppComponent())
                .newsListModule(new NewsListModule(this, mNewsId))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        mRvNewsList.setAdapter(mAdapter);
        mRvNewsList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getMoreData();
            }
        }, mRvNewsList);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }


    @Override
    public void loadData(List<NewsMultiItem> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreData(List<NewsMultiItem> data) {
        mAdapter.loadMoreComplete();
        mAdapter.addData(data);
    }

    @Override
    public void loadNoData() {
    }

    @Override
    public void loadAdData(NewsInfo newsInfo) {

    }
}
