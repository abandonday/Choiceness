package com.zank.choiceness.module.news;

import com.orhanobut.logger.Logger;
import com.zank.choiceness.api.RetrofitService;
import com.zank.choiceness.api.bean.NewsInfo;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.utils.NewsUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by Zank on 2017/10/20.
 */

public class NewsListPresenter implements IBasePresenter {

    private INewsListView mView;

    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(INewsListView mView, String mNewsId) {
        this.mView = mView;
        this.mNewsId = mNewsId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.getNewsList(mNewsId, mPage)
                .doOnSubscribe(new Consumer<Disposable>() { // 观察开始是执行
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (!isRefresh){
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Predicate<NewsInfo>() {
                    @Override
                    public boolean test(@NonNull NewsInfo newsInfo) throws Exception {
                        if (NewsUtils.isAbNews(newsInfo)){
                            mView.loadAdData(newsInfo);
                        }
                        return !NewsUtils.isAbNews(newsInfo);
                    }
                })
                .subscribe(new Observer<NewsInfo>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsInfo newsInfo) {
                        mView.loadData(newsInfo);
                        mPage++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.toString() + " " + isRefresh);
                        if(isRefresh){
                            mView.finishRefresh();
                        } else{
                            mView.showNetError();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.w("onCompleted " + isRefresh);
                        if (isRefresh){
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}
