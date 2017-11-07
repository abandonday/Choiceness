package com.zank.choiceness.injector.components;

import com.zank.choiceness.injector.ActivtityScope;
import com.zank.choiceness.injector.modules.PhotoSetModule;
import com.zank.choiceness.module.news.photoset.PhotoSetActivity;

import dagger.Component;

/**
 * Created by Zank on 2017/10/31.
 */
@ActivtityScope
@Component(modules = PhotoSetModule.class)
public interface PhotoSetComponent {
    void inject(PhotoSetActivity activity);
}
