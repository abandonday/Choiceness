package com.zank.choiceness.injector.modules;

import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.injector.ActivtityScope;
import com.zank.choiceness.module.news.photoset.PhotoSetActivity;
import com.zank.choiceness.module.news.photoset.PhotoSetPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/10/31.
 */
@ActivtityScope
@Module
public class PhotoSetModule {
    private final String newId;
    private final PhotoSetActivity mView;


    public PhotoSetModule(String newId, PhotoSetActivity mView) {
        this.newId = newId;
        this.mView = mView;
    }

    @ActivtityScope
    @Provides
    public IBasePresenter providePhotoSetPresenter(){
        return new PhotoSetPresenter(newId, mView);
    }
}
