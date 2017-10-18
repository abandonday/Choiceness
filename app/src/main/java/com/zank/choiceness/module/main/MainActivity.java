package com.zank.choiceness.module.main;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zank.choiceness.R;
import com.zank.choiceness.base.BaseActivity;
import com.zank.choiceness.constant.FragmentTags;
import com.zank.choiceness.module.main.MainFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;

    @BindView(R.id.nv_side_view)
    NavigationView mNaView;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_bom_view)
    BottomNavigationView mBomNavView;

    private long mExitTime = 0;

    private int mItemId = -1;

    private SparseArray<String> mSparseTags = new SparseArray<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        _initDrawableLayout();
        _getPermission();
        mSparseTags.put(R.id.nav_home, FragmentTags.FRAGMENT_HOME);
        mSparseTags.put(R.id.nav_catrgory, FragmentTags.FRAGMENT_CATRGORY);
        mSparseTags.put(R.id.nav_hot, FragmentTags.FRAGMENT_HOT);
        mSparseTags.put(R.id.nav_movie, FragmentTags.FRAGMENT_MOVIE);

        mNaView.setNavigationItemSelectedListener(this);
        mBomNavView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mBomNavView.setSelectedItemId(R.id.nav_home);
        addFragment(R.id.fl_container, new MainFragment(), FragmentTags.FRAGMENT_HOME);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) {
            return false;
        }
        mItemId = item.getItemId();
        switch (mItemId) {
            /**
             * 底部nav
             */
            case R.id.nav_home:
                break;
            case R.id.nav_catrgory:
                break;
            case R.id.nav_hot:
                break;
            case R.id.nav_movie:
                break;

            /**
             * 侧边栏上部nav
             */
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;

            /**
             * 侧边栏下部nav
             */
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // 获取堆栈fragment数量
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (stackEntryCount == 1) {
            // 只剩下一个页面
            _exit();
        } else {
            String tagName = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount - 2).getName();
            int index = mSparseTags.indexOfValue(tagName);
            mBomNavView.setSelectedItemId(index);
            super.onBackPressed();
        }

    }

    private void _initDrawableLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }

    }

    private void _getPermission() {
    }

    private void _exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Snackbar snackbar = Snackbar.make(mDrawerLayout, "再按一次退出程序", Snackbar.LENGTH_SHORT);
            View view = snackbar.getView();
            if (view != null) {
                view.setBackgroundColor(getResources().getColor(R.color.snackbar_bg));
                ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.collapsed_title));
            }
            snackbar.show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
