package com.zank.choiceness.base;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Zank on 2017/10/16.
 */

public interface IRxBusPresenter extends IBasePresenter{

    /**
     * 注册
     * @param eventType
     * @param <T>
     */
    <T> void registerRxBus(Class<T> eventType, Consumer<T> consumer);

    /**
     * 注销
     */
    void unregisterRxBus();
}
