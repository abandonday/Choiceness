package com.zank.choiceness.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Zank on 2017/9/25.
 */

public interface IBaseView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     */
    void showNetError();

    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();

    /**
     * 绑定生命周期
     */
    <T>LifecycleTransformer<T> bindToLife();

}
