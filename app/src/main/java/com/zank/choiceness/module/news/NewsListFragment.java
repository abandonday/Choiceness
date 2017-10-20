package com.zank.choiceness.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zank.choiceness.R;
import com.zank.choiceness.base.BaseFragment;
import com.zank.choiceness.base.IBasePresenter;

import butterknife.BindView;


public class NewsListFragment extends BaseFragment<IBasePresenter> implements INewsListView{

    private static final String NEWS_TYPE_KEY = "NewsTypeKey";

    private String mNewsId;

    @BindView(R.id.rv_news_list)
    RecyclerView mRvNewsList;

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
    protected int attachLayoutRes() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        Toast.makeText(mContext, mNewsId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void loadData(Object data) {

    }

    @Override
    public void loadMoreData(Object data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void loadAdData() {

    }
}
