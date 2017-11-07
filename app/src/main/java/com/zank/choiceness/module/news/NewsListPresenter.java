package com.zank.choiceness.module.news;

import com.orhanobut.logger.Logger;
import com.zank.choiceness.adapter.item.NewsMultiItem;
import com.zank.choiceness.api.RetrofitService;
import com.zank.choiceness.api.bean.NewsInfo;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.utils.NewsUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                        if (!isRefresh) {
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Predicate<NewsInfo>() {
                    @Override
                    public boolean test(@NonNull NewsInfo newsInfo) throws Exception {
                        if (NewsUtils.isAbNews(newsInfo)) {
                            mView.loadAdData(newsInfo);
                        }
                        return !NewsUtils.isAbNews(newsInfo);
                    }
                })
                .compose(transformer)
                .subscribe(new Observer<List<NewsMultiItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<NewsMultiItem> newsMultiItems) {
                        mView.loadData(newsMultiItems);
                        mPage++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.toString() + " " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                            // 可以提示对应的信息，但不更新界面
                        } else {
                            mView.showNetError();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Logger.w("onCompleted " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(mNewsId, mPage)
                .compose(transformer)
                .subscribe(new Observer<List<NewsMultiItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<NewsMultiItem> newsMultiItems) {
                        mView.loadMoreData(newsMultiItems);
                        mPage++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 统一转换数据
     */
    ObservableTransformer<NewsInfo,List<NewsMultiItem>> transformer = new ObservableTransformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public ObservableSource<List<NewsMultiItem>> apply(@NonNull Observable<NewsInfo> upstream) {
            return upstream
                    .map(new Function<NewsInfo, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem apply(@NonNull NewsInfo newsInfo) throws Exception {
                            if (NewsUtils.isNewsPhotoSet(newsInfo.getSkipType())) {
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsInfo);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsInfo);
                        }
                    })
                    .toList()
                    .compose(mView.<List<NewsMultiItem>>bindToLife())
                    .toObservable();
        }
    };




}
