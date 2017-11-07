package com.zank.choiceness.injector.components;

import com.zank.choiceness.injector.ActivtityScope;
import com.zank.choiceness.injector.modules.NewsArticleModule;
import com.zank.choiceness.module.news.article.NewsArticleActivity;

import dagger.Component;

/**
 * Created by Zank on 2017/10/30.
 */
@ActivtityScope
@Component(modules = NewsArticleModule.class)
public interface NewsArticleComponent {

    void inject(NewsArticleActivity activity);
}
