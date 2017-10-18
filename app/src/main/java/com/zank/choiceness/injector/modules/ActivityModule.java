package com.zank.choiceness.injector.modules;

import android.app.Activity;

import com.zank.choiceness.injector.ActivtityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/9/25.
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @ActivtityScope
    @Provides
    Activity providerActivity(){
        return mActivity;
    }
}
