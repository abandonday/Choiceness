package com.zank.choiceness.base;

/**
 * Created by Zank on 2017/9/25.
 */

public interface IBasePresenter {

    /**
     * 获取网络数据，更新界面
     * @param isRefresh
     */
    void getData(boolean isRefresh);

    /**
     * 加载更多
     */
    void getMoreData();
}
