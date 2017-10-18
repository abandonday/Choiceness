package com.zank.choiceness.injector.components;

import android.app.Activity;

import com.zank.choiceness.injector.ActivtityScope;
import com.zank.choiceness.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Zank on 2017/9/26.
 * 全局注射器
 * 提供Activty使用
 */
@ActivtityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivtyComponent {

    Activity getActivity();
}
