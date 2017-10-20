package com.zank.choiceness.injector.components;

import android.content.Context;

import com.zank.choiceness.greendao.DaoSession;
import com.zank.choiceness.injector.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Zank on 2017/9/25.
 * 全局注射器
 * 提供对象以便于其他组件使用，比如context、rest、api
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 对外公布对象
     */

    Context getContext();

    DaoSession getDaoSession();



}
