package com.zank.choiceness.injector.modules;

import com.zank.choiceness.adapter.ViewPagerAdapter;
import com.zank.choiceness.base.IRxBusPresenter;
import com.zank.choiceness.greendao.DaoSession;
import com.zank.choiceness.injector.FragmentScope;
import com.zank.choiceness.module.main.MainFragment;
import com.zank.choiceness.module.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zank on 2017/10/18.
 */
@Module
public class MainModule {
    private final MainFragment mView;

    public MainModule(MainFragment mView) {
        this.mView = mView;
    }

    @FragmentScope
    @Provides
    public IRxBusPresenter provideMainPresenter(DaoSession daoSession) {
        return new MainPresenter(mView, daoSession.getNewsTypeInfoDao());
    }

    @FragmentScope
    @Provides
    public ViewPagerAdapter provideViewPagerAdapter(){
        return new ViewPagerAdapter(mView.getChildFragmentManager());
    }


}
