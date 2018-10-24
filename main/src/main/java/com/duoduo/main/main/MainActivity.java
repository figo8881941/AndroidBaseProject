package com.duoduo.main.main;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.duoduo.commonbase.component.NoScrollViewPager;
import com.duoduo.commonbase.utils.ActivityUtils;
import com.duoduo.commonbusiness.activity.BaseActivity;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.commonbusiness.permission.DefaultCheckRequestListener;
import com.duoduo.commonbusiness.permission.PermissionUtils;
import com.duoduo.main.R;
import com.duoduo.main.main.controller.MainController;
import com.duoduo.main.main.data.MainTabDataBean;
import com.duoduo.main.main.event.MainTabRequestEvent;
import com.duoduo.main.main.view.MainFragmentFactory;
import com.duoduo.main.main.view.MainFragmentPagerAdapter;
import com.duoduo.main.main.view.MainTabFactory;

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
        ActivityUtils.changeStatusBarTran(this, true);

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
                if (curFragment != null) {
                    curFragment.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
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
                //切换Tab样式
                MainTabFactory.changeTabToSelectedStyle(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //切换Tab样式
                MainTabFactory.changeTabToUnSelectedStyle(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
            case MainTabRequestEvent.EVENT_NAME_REQUEST_FINISH: {
                //tab数据返回，更新界面
                MainTabDataBean mainTabDataBean = event.getArg3();
                updateViewByMainTabData(mainTabDataBean);
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
     * @param mainTabDataBean
     */
    private void updateViewByMainTabData(MainTabDataBean mainTabDataBean) {
        if (mainTabDataBean == null) {
            return;
        }


        //初始化Fragment
        mainFragmentList = MainFragmentFactory.createMainFragmentList(mainTabDataBean);
        mainFragmentPagerAdapter.setFragments(mainFragmentList);
        mainFragmentPagerAdapter.notifyDataSetChanged();
        //默认选中第一个Framgnet
        curFragment = getCurFragment();
        if (curFragment != null) {
            curFragment.onSelected();
        }

        //初始化Tab
        MainTabFactory.createTabByData(mainTabLayout, mainTabDataBean);
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
    }
}
