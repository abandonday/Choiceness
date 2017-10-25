package com.zank.choiceness.module.news;

import com.zank.choiceness.adapter.item.NewsMultiItem;
import com.zank.choiceness.api.bean.NewsInfo;
import com.zank.choiceness.base.ILoadDataView;

import java.util.List;

/**
 * Created by Zank on 2017/10/20.
 */

public interface INewsListView extends ILoadDataView<List<NewsMultiItem>>{

    /**
     * 加载广告数据
     */
    void loadAdData(NewsInfo newsInfo);
}
