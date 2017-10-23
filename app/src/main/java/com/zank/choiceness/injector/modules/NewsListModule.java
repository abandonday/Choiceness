package com.zank.choiceness.injector.modules;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zank.choiceness.adapter.NewsMultiListAdapter;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.injector.FragmentScope;
import com.zank.choiceness.module.news.NewsListFragment;
import com.zank.choiceness.module.news.NewsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/10/23.
 */
@Module
public class NewsListModule {

    private final NewsListFragment fragment;

    private final String mNewsId;


    public NewsListModule(NewsListFragment fragment, String mNewsId) {
        this.fragment = fragment;
        this.mNewsId = mNewsId;
    }

    @FragmentScope
    @Provides
    public IBasePresenter providePresenter(){
        return new NewsListPresenter(fragment, mNewsId);
    }

    public BaseQuickAdapter provideAdapter(){
        return null;
    }

}
