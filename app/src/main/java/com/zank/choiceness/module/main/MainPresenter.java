package com.zank.choiceness.module.main;

import com.zank.choiceness.base.IRxBusPresenter;
import com.zank.choiceness.entity.NewsTypeInfo;
import com.zank.choiceness.greendao.NewsTypeInfoDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zank on 2017/10/18.
 */

public class MainPresenter implements IRxBusPresenter{

    private final IMainView mView;
    private final NewsTypeInfoDao mDbDao;

    public MainPresenter(IMainView mView, NewsTypeInfoDao mDbDao) {
        this.mView = mView;
        this.mDbDao = mDbDao;
    }


    @Override
    public void getData(boolean isRefresh) {
        Observable.just(mDbDao.queryBuilder().list())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsTypeInfo>>() {
                    @Override
                    public void accept(List<NewsTypeInfo> newsTypeInfos) throws Exception {
                        mView.loadData(newsTypeInfos);
                    }
                });
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
