package com.zank.choiceness.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.labelview.LabelView;
import com.zank.choiceness.R;
import com.zank.choiceness.adapter.item.NewsMultiItem;
import com.zank.choiceness.api.bean.NewsInfo;
import com.zank.choiceness.utils.DefIconFactory;
import com.zank.choiceness.utils.ImageLoader;
import com.zank.choiceness.utils.ListUtils;
import com.zank.choiceness.utils.NewsUtils;
import com.zank.choiceness.utils.StringUtils;
import com.zank.choiceness.widget.RippleView;

import java.util.List;

/**
 * Created by Zank on 2017/10/23.
 */

public class NewsMultiListAdapter extends BaseMultiItemQuickAdapter<NewsMultiItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    public NewsMultiListAdapter(List<NewsMultiItem> data) {
        super(data);
    }

    public NewsMultiListAdapter(Context context, List<NewsMultiItem> data) {
        super(data);
        addItemType(NewsMultiItem.ITEM_TYPE_NORMAL, R.layout.adapter_news_list);
        addItemType(NewsMultiItem.ITEM_TYPE_PHOTO_SET, R.layout.adapter_news_photo_set);
    }

    /**
     * 转换器
     *
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, NewsMultiItem item) {
        switch (item.getItemType()) {
            case NewsMultiItem.ITEM_TYPE_NORMAL: //  正常新闻
                _handleNewsNormal(helper, item.getNewsInfo());
                break;
            case NewsMultiItem.ITEM_TYPE_PHOTO_SET: // 新闻图集
                _handleNewsPhotoSet(helper, item.getNewsInfo());
                break;
        }
    }

    /**
     * 处理正常新闻
     *
     * @param viewHolder
     * @param item
     */
    private void _handleNewsNormal(final BaseViewHolder viewHolder, final NewsInfo item) {
        ImageView newsIcon = viewHolder.getView(R.id.iv_icon);
        ImageLoader.loadCenterCrop(mContext, item.getImgsrc(), newsIcon, DefIconFactory.provideIcon());
        viewHolder.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_source, StringUtils.clipNewsSource(item.getSource()))
                .setText(R.id.tv_time, item.getPtime());
        // 设置专题
        if (NewsUtils.isNewsSpecial(item.getSkipType())) {
            LabelView labelView = viewHolder.getView(R.id.label_view);
            labelView.setVisibility(View.VISIBLE);
            labelView.setText("专题");
        } else {
            viewHolder.setVisible(R.id.label_view, false);
        }

        // 波纹效果
        RippleView rippleView = viewHolder.getView(R.id.item_ripple);
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {

            }
        });
    }

    /**
     * 处理图集
     *
     * @param viewHolder
     * @param item
     */
    private void _handleNewsPhotoSet(final BaseViewHolder viewHolder, final NewsInfo item) {
        ImageView[] newsPhoto = new ImageView[3];
        newsPhoto[0] = viewHolder.getView(R.id.iv_icon_1);
        newsPhoto[1] = viewHolder.getView(R.id.iv_icon_2);
        newsPhoto[2] = viewHolder.getView(R.id.iv_icon_3);
        viewHolder.setVisible(R.id.iv_icon_2, false).setVisible(R.id.iv_icon_3, false);
        ImageLoader.loadCenterCrop(mContext, item.getImgsrc(), newsPhoto[0], DefIconFactory.provideIcon());
        if (!ListUtils.isEmpty(item.getImgextra())) {
            for (int i = 0; i < Math.min(2, item.getImgextra().size()); i++) {
                newsPhoto[i + 1].setVisibility(View.VISIBLE);
                ImageLoader.loadCenterCrop(mContext, item.getImgextra().get(i).getImgsrc(),
                        newsPhoto[i + 1], DefIconFactory.provideIcon());
            }
        }
        viewHolder.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_source, StringUtils.clipNewsSource(item.getSource()))
                .setText(R.id.tv_time, item.getPtime());

        // 波纹效果
        RippleView rippleView = viewHolder.getView(R.id.item_ripple);
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {

            }
        });
    }
}
