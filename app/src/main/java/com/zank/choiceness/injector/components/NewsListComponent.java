package com.zank.choiceness.injector.components;

import com.zank.choiceness.injector.FragmentScope;
import com.zank.choiceness.injector.modules.NewsListModule;
import com.zank.choiceness.module.news.NewsListFragment;

import dagger.Component;

/**
 * Created by Zank on 2017/10/23.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListFragment newsListFragment);
}
