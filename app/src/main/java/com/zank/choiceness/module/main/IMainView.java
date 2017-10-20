package com.zank.choiceness.module.main;

import com.zank.choiceness.entity.NewsTypeInfo;

import java.util.List;

/**
 * Created by Zank on 2017/10/16.
 */

public interface IMainView {
    /**
     * 显示数据
     */
    void loadData(List<NewsTypeInfo> newsTypeInfos);
}
