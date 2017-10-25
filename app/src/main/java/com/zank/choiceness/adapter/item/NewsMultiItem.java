package com.zank.choiceness.adapter.item;

import android.support.annotation.IntDef;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zank.choiceness.api.bean.NewsInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Zank on 2017/10/23.
 */

public class NewsMultiItem implements MultiItemEntity {

    public static final int ITEM_TYPE_NORMAL = 1; // 正常

    public static final int ITEM_TYPE_PHOTO_SET = 2; // 图集

    private NewsInfo mNewsInfo;

    private int itemType;

    public NewsMultiItem(@NewsItemType int itemType, NewsInfo mNewsInfo) {
        this.mNewsInfo = mNewsInfo;
        this.itemType = itemType;
    }

    public NewsInfo getNewsInfo() {
        return mNewsInfo;
    }

    public void setNewsInfo(NewsInfo mNewsInfo) {
        mNewsInfo = mNewsInfo;
    }

    public void setItemType(@NewsItemType int itemType) {
        this.itemType = itemType;
}

    @Override
    public int getItemType() {
        return itemType;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ITEM_TYPE_NORMAL, ITEM_TYPE_PHOTO_SET})
    public @interface NewsItemType {}
}
