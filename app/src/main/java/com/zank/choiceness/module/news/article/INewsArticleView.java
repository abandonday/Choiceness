package com.zank.choiceness.module.news.article;

import com.zank.choiceness.api.bean.NewsDetailInfo;
import com.zank.choiceness.base.IBaseView;

/**
 * Created by Zank on 2017/10/30.
 * 新闻文章详情接口
 */

public interface INewsArticleView extends IBaseView{

    /**
     * 加载文章新闻
     */
    void loadData(NewsDetailInfo detailInfo);
}
