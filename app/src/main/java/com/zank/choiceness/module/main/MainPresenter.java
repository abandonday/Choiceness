package com.zank.choiceness.module.main;

import com.zank.choiceness.base.IRxBusPresenter;

import io.reactivex.functions.Consumer;

/**
 * Created by Zank on 2017/10/18.
 */

public class MainPresenter implements IRxBusPresenter{

    private final IMainView mView;

    public MainPresenter(IMainView mView) {
        this.mView = mView;
    }


    @Override
    public void getData(boolean isRefresh) {
        mView.loadData();
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public <T> void registerRxBus(Class<T> eventType, Consumer<T> consumer) {

    }

    @Override
    public void unregisterRxBus() {

    }
}
