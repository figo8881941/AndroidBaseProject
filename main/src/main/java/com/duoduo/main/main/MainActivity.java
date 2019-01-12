package com.duoduo.main.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbase.view.NoScrollViewPager;
import com.duoduo.commonbusiness.activity.BaseActivity;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.main.R;
import com.duoduo.main.main.data.MainTabEntity;
import com.duoduo.main.main.presenter.IMainPresenter;
import com.duoduo.main.main.presenter.MainPresenter;
import com.duoduo.main.main.view.IMainView;
import com.duoduo.main.main.view.MainFragmentHelper;
import com.duoduo.main.main.view.MainFragmentPagerAdapter;
import com.duoduo.main.main.view.MainTabHelper;

import java.util.ArrayList;

/**
 * 主activity
 */
public class MainActivity extends BaseActivity implements IMainView {

    private IMainPresenter mainPresenter;

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

        //初始化界面
        initView();

        mainPresenter = new MainPresenter(getApplicationContext(), this);
        //请求Tab数据
        mainPresenter.requestTabData();

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
     * 根据tab数据更新界面的方法
     *
     * @param mainTabEntity
     */
    public void updateViewByMainTabData(MainTabEntity mainTabEntity) {
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

        if (mainPresenter != null) {
            mainPresenter.destroy();
            mainPresenter = null;
        }

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
