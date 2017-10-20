package com.zank.choiceness.greendao;

import android.content.Context;

import com.zank.choiceness.entity.NewsTypeInfo;
import com.zank.choiceness.utils.AssetsHelper;
import com.zank.choiceness.utils.GsonHelper;

import java.util.List;

/**
 * Created by Zank on 2017/10/19.
 *
 * 新闻分类数据访问
 */

public class NewsTypeDao {

    // 所有栏目
    private static List<NewsTypeInfo> sAllChannels;

    public static void updateLocalData(Context context, DaoSession daoSession){
        sAllChannels = GsonHelper.convertEntities(AssetsHelper.readData(context, "NewsChannel"), NewsTypeInfo.class);
        NewsTypeInfoDao newsTypeInfoDao = daoSession.getNewsTypeInfoDao();
        if(newsTypeInfoDao.count() == 0){
            newsTypeInfoDao.insertInTx(sAllChannels.subList(0,5));
        }
    }

    /**
     * 获取所有栏目
     * @return
     */
    public static List<NewsTypeInfo> getAllChannels() {
        return sAllChannels;
    }
}
