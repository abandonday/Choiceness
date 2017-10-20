package com.zank.choiceness.injector.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zank.choiceness.AppApplication;
import com.zank.choiceness.greendao.DaoSession;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/9/26.
 */

@Module
public class AppModule {

    private final Context context;

    private final DaoSession mDaoSession;

    public AppModule(AppApplication application, DaoSession mDaoSession) {
        this.context = application;
        this.mDaoSession = mDaoSession;
    }

    @Singleton
    @Provides
    public Context providerApplication() {
        return context;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return mDaoSession;
    }

    @Singleton
    @Provides
    @Named("default")
    public SharedPreferences providerDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named("encode")
    public SharedPreferences providerEncodeSharedPreferences() {
        return context.getSharedPreferences("encode", Context.MODE_PRIVATE);
    }
}
