package com.zank.choiceness.module.news.photoset;

import com.zank.choiceness.api.bean.PhotoSetInfo;
import com.zank.choiceness.base.IBaseView;

/**
 * Created by Zank on 2017/10/31.
 */

public interface IPhotoSetView extends IBaseView{
    /**
     * 显示数据
     */
    void loadData(PhotoSetInfo photoSetInfo);
}
