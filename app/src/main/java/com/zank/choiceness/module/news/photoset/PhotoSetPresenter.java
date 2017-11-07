package com.zank.choiceness.module.news.photoset;

import com.orhanobut.logger.Logger;
import com.zank.choiceness.api.RetrofitService;
import com.zank.choiceness.api.bean.PhotoSetInfo;
import com.zank.choiceness.base.IBasePresenter;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Zank on 2017/10/31.
 */

public class PhotoSetPresenter implements IBasePresenter {

    public final String mPhotoSetId;
    public final IPhotoSetView mView;

    public PhotoSetPresenter(String mPhotoSetId, IPhotoSetView mView) {
        this.mView = mView;
        this.mPhotoSetId = mPhotoSetId;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getPhotoSet(mPhotoSetId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .compose(mView.<PhotoSetInfo>bindToLife())
                .subscribe(new Observer<PhotoSetInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.e("fdsf", "dfs");
                    }

                    @Override
                    public void onNext(@NonNull PhotoSetInfo photoSetInfo) {
                        mView.loadData(photoSetInfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getMoreData() {

    }
}
