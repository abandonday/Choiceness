package com.zank.choiceness.injector.modules;

import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.injector.ActivtityScope;
import com.zank.choiceness.module.news.article.NewsArticleActivity;
import com.zank.choiceness.module.news.article.NewsArticlePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/10/30.
 */
@ActivtityScope
@Module
public class NewsArticleModule {
    private final String newsId;
    private final NewsArticleActivity mView;


    public NewsArticleModule(String newsId, NewsArticleActivity mView) {
        this.newsId = newsId;
        this.mView = mView;
    }

    @ActivtityScope
    @Provides
    public IBasePresenter providePresenter(){
        return new NewsArticlePresenter(newsId, mView);
    }
}
