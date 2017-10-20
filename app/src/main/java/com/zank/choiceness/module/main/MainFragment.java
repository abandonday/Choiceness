package com.zank.choiceness.module.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.zank.choiceness.R;
import com.zank.choiceness.base.BaseFragment;
import com.zank.choiceness.base.IRxBusPresenter;
import com.zank.choiceness.entity.NewsTypeInfo;
import com.zank.choiceness.injector.components.DaggerMainComponent;
import com.zank.choiceness.injector.modules.MainModule;
import com.zank.choiceness.module.BlankFragment;
import com.zank.choiceness.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 主页
 */
public class MainFragment extends BaseFragment<IRxBusPresenter> implements IMainView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Inject
    ViewPagerAdapter mPagerAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initInjector() {
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, "首页");
        setHasOptionsMenu(true); // 初始化onCreateOptionsMenu需要调用
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<NewsTypeInfo> newsTypeInfos) {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for(NewsTypeInfo typeInfo: newsTypeInfos){
            titles.add(typeInfo.getName());
            fragments.add(new BlankFragment());
        }
        mPagerAdapter.setItems(fragments, titles);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_channel, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
