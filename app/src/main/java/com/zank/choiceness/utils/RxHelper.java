package com.zank.choiceness.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zank on 2017/10/12.
 * RxJava工具类
 */

public class RxHelper {
    private RxHelper() {
        throw new AssertionError();
    }

    /**
     * 倒计时
     *
     * @return 被观察者，由观察者处理
     */
    public static Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .map(new Function<Long, Integer>() { // map 做数据简单处理，这里反转时间
                    @Override
                    public Integer apply(@NonNull Long aLong) throws Exception {
                        return countTime-aLong.intValue();
                    }
                })
                .take(countTime+1) // 限制最多输出多少
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
