package com.zank.choiceness.injector.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zank.choiceness.AppApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/9/26.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(AppApplication application) {
        context = application;
    }

    @Singleton
    @Provides
    public Context providerApplication() {
        return context;
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
