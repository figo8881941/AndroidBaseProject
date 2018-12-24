package com.duoduo.main.main;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.duoduo.commonbase.permission.DefaultCheckRequestListener;
import com.duoduo.commonbase.permission.PermissionUtils;
import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbase.view.NoScrollViewPager;
import com.duoduo.commonbusiness.activity.BaseActivity;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.R;
import com.duoduo.main.main.controller.MainController;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.event.MainTabRequestEvent;
import com.duoduo.main.main.view.MainFragmentHelper;
import com.duoduo.main.main.view.MainFragmentPagerAdapter;
import com.duoduo.main.main.view.MainTabHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 主activity
 */
public class MainActivity extends BaseActivity {

    private MainController mainController;

    //Viewpager、Fragment
    private NoScrollViewPager mainViewPager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    private ArrayList<BaseFragment> mainFragmentList;
    //当前Fragment
    private BaseFragment curFragment;

    //Tab
    private TabLayout mainTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_main_main_activity);

        //白色风格透明状态栏
        StatusBarUtils.changeStatusBarTran(this, false);

        EventBus.getDefault().register(this);

        //初始化界面
        initView();

        mainController = new MainController(getApplicationContext());

        // 检查必须的权限
        checkShouldGetPermission();

    }

    /**
     * 初始化界面
     */
    private void initView() {

        //ViewPager、Fragment
        mainViewPager = (NoScrollViewPager) findViewById(R.id.main_viewpager);
        mainViewPager.canScrollHorizontally(0);
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainFragmentPagerAdapter);
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isDestroy) {
                    return;
                }
                if (curFragment != null) {
                    curFragment.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (isDestroy) {
                    return;
                }
                //Fragment切换
                BaseFragment fragment = getFragmentByPosition(position);
                if (fragment != null) {
                    if (fragment != curFragment) {
                        fragment.onSelected();
                        if (curFragment != null) {
                            curFragment.onUnSelected();
                        }
                        curFragment = fragment;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (isDestroy) {
                    return;
                }
                if (curFragment != null) {
                    curFragment.onPageScrollStateChanged(state);
                }
            }
        });

        //Tab
        mainTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mainTabLayout.setSelectedTabIndicatorHeight(0);
        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (isDestroy) {
                    return;
                }
                //切换Tab样式
                MainTabHelper.changeTabToSelectedStyle(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (isDestroy) {
                    return;
                }
                //切换Tab样式
                MainTabHelper.changeTabToUnSelectedStyle(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (isDestroy) {
                    return;
                }

            }
        });
        mainTabLayout.setupWithViewPager(mainViewPager);
    }

    /**
     * 检查必须权限的方法
     */
    private void checkShouldGetPermission() {
        PermissionUtils.checkAndRequestPermission(getApplicationContext(), true, new DefaultCheckRequestListener() {
            @Override
            public void onGrantedPermission(String... permissions) {
                //授权成功，请求Tab数据
                if (mainController != null) {
                    mainController.requestTabData();
                }
            }

            @Override
            public void onDeniedPermission(String... permissions) {
                //授权失败，提示
                Toast.makeText(getApplicationContext(), R.string.main_main_no_permission_tips, Toast.LENGTH_LONG).show();
            }
        }, Manifest.permission.READ_PHONE_STATE);
    }

    /**
     * 处理请求tab数据返回
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMainTabRequest(MainTabRequestEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case MainTabRequestEvent.EVENT_NAME_REQUEST_START: {

            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_SUCCESS: {
                //tab数据返回，更新界面
                MainTabEntity mainTabEntity = event.getArg3();
                updateViewByMainTabData(mainTabEntity);
            }
            break;
            case MainTabRequestEvent.EVENT_NAME_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getApplicationContext(), exception);
            }
            break;
        }
    }

    /**
     * 根据tab数据更新界面的方法
     *
     * @param mainTabEntity
     */
    private void updateViewByMainTabData(MainTabEntity mainTabEntity) {
        if (mainTabEntity == null) {
            return;
        }


        //初始化Fragment
        mainFragmentList = MainFragmentHelper.createMainFragmentList(mainTabEntity);
        mainFragmentPagerAdapter.setFragments(mainFragmentList);
        mainFragmentPagerAdapter.notifyDataSetChanged();
        //默认选中第一个Framgnet
        curFragment = getCurFragment();
        if (curFragment != null) {
            curFragment.onSelected();
        }

        //初始化Tab
        MainTabHelper.createTabByData(mainTabLayout, mainTabEntity);
    }

    /**
     * 获取当前Fragment
     *
     * @return
     */
    private BaseFragment getCurFragment() {
        if (mainViewPager == null) {
            return null;
        }
        int curPosition = mainViewPager.getCurrentItem();
        return getFragmentByPosition(curPosition);
    }

    /**
     * 通过position获取Fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragmentByPosition(int position) {
        if (mainFragmentList == null) {
            return null;
        }
        return mainFragmentList.get(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);

        mainController = null;

        if (mainViewPager != null) {
            mainViewPager.setAdapter(null);
            mainViewPager = null;
        }

        if (mainFragmentPagerAdapter != null) {
            mainFragmentPagerAdapter.destory();
            mainFragmentPagerAdapter = null;
        }

        curFragment = null;

        if (mainFragmentList != null) {
            for (BaseFragment fragment : mainFragmentList) {
                fragment.onDestroy();
            }
            mainFragmentList.clear();
            mainFragmentList = null;
        }

        if (mainTabLayout != null) {
            mainTabLayout.setupWithViewPager(null);
            mainTabLayout.removeAllTabs();
            mainTabLayout = null;

        }
    }
}
