package com.zank.choiceness.injector.components;

import android.app.Application;
import android.content.Context;

import com.zank.choiceness.injector.module.APPModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Zank on 2017/9/25.
 * 全局注射器
 * 提供对象以便于其他组件使用，比如context、rest、api
 */

@Singleton
@Component(modules = APPModule.class)
public interface APPComponent {

    /**
     * 对外公布对象
     */

    Application getApplication();



}
