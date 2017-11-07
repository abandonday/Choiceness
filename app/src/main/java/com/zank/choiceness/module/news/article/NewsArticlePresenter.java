package com.zank.choiceness.module.news.article;

import com.zank.choiceness.api.RetrofitService;
import com.zank.choiceness.api.bean.NewsDetailInfo;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.utils.ListUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Zank on 2017/10/30.
 */

public class NewsArticlePresenter implements IBasePresenter{

    private static final String HTML_IMG_TEMPLATE = "<img src=\"http\" />";

    private final String newsId;
    private final INewsArticleView mView;

    public NewsArticlePresenter(String newsId, INewsArticleView mView) {
        this.newsId = newsId;
        this.mView = mView;
    }


    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.getNewsDetail(newsId)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                    }
                })
                .doOnNext(new Consumer<NewsDetailInfo>() {
                    @Override
                    public void accept(NewsDetailInfo detailInfo) throws Exception {
                        _handleRichTextWithImg(detailInfo);
                    }
                })
                .compose(mView.<NewsDetailInfo>bindToLife())
                .subscribe(new Observer<NewsDetailInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsDetailInfo detailInfo) {
                        mView.loadData(detailInfo);
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

    private void _handleRichTextWithImg(NewsDetailInfo detailInfo){
        if(!ListUtils.isEmpty(detailInfo.getImg())){
            String body = detailInfo.getBody();
            for(NewsDetailInfo.ImgEntity imgEntity: detailInfo.getImg()){
                String ref = imgEntity.getRef();
                String src = imgEntity.getSrc();
                String img = HTML_IMG_TEMPLATE.replace("http", src);
                body = body.replaceAll(ref, img);
            }
            detailInfo.setBody(body);
        }
    }
}
