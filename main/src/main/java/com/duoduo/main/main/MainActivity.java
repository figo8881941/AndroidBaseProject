package com.duoduo.main.main;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

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
    private ViewPager mainViewPager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    private ArrayList<BaseFragment> mainFragmentList;

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
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(mainFragmentPagerAdapter);
        mainTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
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
        mainFragmentList = MainFragmentFactory.createMainFragmentList(mainTabDataBean);
        mainFragmentPagerAdapter.setFragments(mainFragmentList);
        mainFragmentPagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
