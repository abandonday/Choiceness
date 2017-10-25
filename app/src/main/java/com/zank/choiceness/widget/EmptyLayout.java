package com.zank.choiceness.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.zank.choiceness.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zank on 2017/9/25.
 * 空布局（主要实现公共提示）
 */

public class EmptyLayout extends FrameLayout{

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;
    private Context mContext;
    private OnRetryListener mOnRetryListener;
    private int mEmptyStatus = STATUS_LOADING;
    private int mBgColor;

    @BindView(R.id.tv_net_error)
    TextView mTvEmptyMessage;
    @BindView(R.id.rl_empty_container)
    View mRlEmptyContainer;
    @BindView(R.id.empty_loading)
    SpinKitView mEmptyLoading;
    @BindView(R.id.empty_layout)
    FrameLayout mEmptyLayout;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        } finally {
            a.recycle();
        }
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        mEmptyLayout.setBackgroundColor(mBgColor);
        _switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        _switchEmptyView();
    }

    /**
     * 获取状态
     * @return  状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mTvEmptyMessage.setText(msg);
    }

    public void hideErrorIcon() {
        mTvEmptyMessage.setCompoundDrawables(null, null, null, null);
    }

//    /**
//     * 设置图标
//     * @param resId 资源ID
//     */
//    public void setEmptyIcon(int resId) {
//        mIvEmptyIcon.setImageResource(resId);
//    }
//
//    /**
//     * 设置图标
//     * @param drawable drawable
//     */
//    public void setEmptyIcon(Drawable drawable) {
//        mIvEmptyIcon.setImageDrawable(drawable);
//    }

    public void setLoadingIcon(Sprite d) {
        mEmptyLoading.setIndeterminateDrawable(d);
    }

    /**
     * 切换视图
     */
    private void _switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(GONE);
                mEmptyLoading.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mEmptyLoading.setVisibility(GONE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
        }
    }

    /**
     * 设置重试监听器
     *
     * @param retryListener 监听器
     */
    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    @OnClick(R.id.tv_net_error)
    public void onClick() {
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
        }
    }

    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }

    /**
     * Retention(保留)注解说明,这种类型的注解会被保留到那个阶段. 有三个值:
     1.RetentionPolicy.SOURCE —— 这种类型的Annotations只在源代码级别保留,编译时就会被忽略
     2.RetentionPolicy.CLASS —— 这种类型的Annotations编译时被保留,在class文件中存在,但JVM将会忽略
     3.RetentionPolicy.RUNTIME —— 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
     * IntDef 枚举注解
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA, STATUS_HIDE})
    public @interface EmptyStatus{}
}

