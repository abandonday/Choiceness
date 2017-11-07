package com.zank.choiceness.module.news.article;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zank.choiceness.R;
import com.zank.choiceness.api.bean.NewsDetailInfo;
import com.zank.choiceness.base.BaseActivity;
import com.zank.choiceness.base.IBasePresenter;
import com.zank.choiceness.injector.components.DaggerNewsArticleComponent;
import com.zank.choiceness.injector.modules.NewsArticleModule;
import com.zank.choiceness.utils.ListUtils;
import com.zank.choiceness.utils.NewsUtils;
import com.zank.choiceness.widget.EmptyLayout;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnURLClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zank.choiceness.R.id.tv_title_2;

public class NewsArticleActivity extends BaseActivity<IBasePresenter> implements INewsArticleView {

    private static final String NEWS_ID_KEY = "NewsIdKey";

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_pre_toolbar)
    LinearLayout mLlPreToolbar;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    @BindView(R.id.iv_back_2)
    ImageView mIvBack2;
    @BindView(value = tv_title_2)
    TextView mTvTitle2;
    @BindView(R.id.ll_top_bar)
    LinearLayout mLlTopBar;

    private String mNewsId;

    public static void launch(Context context, String newId) {
        Intent intent = new Intent(context, NewsArticleActivity.class);
        intent.putExtra(NEWS_ID_KEY, newId);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_right_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_news_article;
    }

    @Override
    protected void initInjector() {
        mNewsId = getIntent().getStringExtra(NEWS_ID_KEY);
        DaggerNewsArticleComponent.builder()
                .newsArticleModule(new NewsArticleModule(mNewsId, this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(NewsDetailInfo detailInfo) {
        mTvTitle.setText(detailInfo.getTitle());
        mTvTitle2.setText(detailInfo.getTitle());
        mTvTime.setText(detailInfo.getPtime());
        RichText.from(detailInfo.getBody())
                .into(mTvContent);
        _handleSpInfo(detailInfo.getSpinfo());

    }

    private void _handleSpInfo(List<NewsDetailInfo.SpinfoEntity> spinfoEntities) {
        if (!ListUtils.isEmpty(spinfoEntities)) {
            ViewStub stub = (ViewStub) findViewById(R.id.vs_related_content);
            assert stub != null;
            stub.inflate();
            TextView tvType = (TextView) findViewById(R.id.tv_type);
            TextView tvRelatedContent = (TextView) findViewById(R.id.tv_related_content);
            tvType.setText(spinfoEntities.get(0).getSptype());
            RichText.from(spinfoEntities.get(0).getSpcontent())
                    .urlClick(new OnURLClickListener() {
                        @Override
                        public boolean urlClicked(String url) {
                            String newId = NewsUtils.clipNewsIdFromUrl(url);
                            if (newId != null) {
                                launch(NewsArticleActivity.this, newId);
                            }
                            return true;
                        }
                    })
                    .into(tvRelatedContent);
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_back_2, tv_title_2})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.iv_back_2:
                finish();
                break;
            case tv_title_2:
                mScrollView.smoothScrollTo(0, 0);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_right_exit);
    }
}
