package com.zank.choiceness.injector.components;

import com.zank.choiceness.injector.FragmentScope;
import com.zank.choiceness.injector.modules.MainModule;
import com.zank.choiceness.module.main.MainFragment;

import dagger.Component;

/**
 * Created by Zank on 2017/10/18.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainFragment mainFragment);
}
