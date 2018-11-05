package com.duoduo.main.classify;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.net.CommonNetErrorHandler;
import com.duoduo.main.R;
import com.duoduo.main.classify.controller.ClassifyController;
import com.duoduo.main.classify.data.ClassifySubTabDataBean;
import com.duoduo.main.classify.event.ClassifySubTabDataRequestEvent;
import com.duoduo.main.classify.view.ClassifyPagerSlidingTabStrip;
import com.duoduo.main.classify.view.ClassifySubFragmentFactory;
import com.duoduo.main.classify.view.ClassifySubFragmentPagerAdapter;
import com.duoduo.main.main.data.MainTabDataBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * 分类Fragment
 */
public class ClassifyFragment extends BaseFragment<MainTabDataBean.TabListEntity> {

    private ViewGroup mainView;

    private ClassifyPagerSlidingTabStrip tabStrip;
    private View recommendLayoutBaseline;

    private ViewPager subViewPager;
    private ClassifySubFragmentPagerAdapter subPagerAdapter;

    private ArrayList<BaseFragment> subFragmentList;

    //当前Fragment
    private BaseFragment curSubFragment;

    private ClassifyController controller;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        controller = new ClassifyController(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_fragment, container, false);
        initView();
        controller.requestClassifySubTabData();
        return mainView;
    }

    private void initView() {
        //状态栏背景占位
        View statusBarSpaceView = mainView.findViewById(R.id.status_bar_space_view);
        statusBarSpaceView.getLayoutParams().height = StatusBarUtils.getStatusBarHeightFit(getContext().getApplicationContext());

        //Viewpage
        subViewPager = (ViewPager) mainView.findViewById(R.id.tab_fragment_viewpager);
        subPagerAdapter = new ClassifySubFragmentPagerAdapter(getChildFragmentManager());
        //创建初始的fragment
        subFragmentList = ClassifySubFragmentFactory.createInitClassifySubFragmentList(getContext().getApplicationContext());
        subPagerAdapter.setFragments(subFragmentList);
        subViewPager.setAdapter(subPagerAdapter);
        subViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (curSubFragment != null) {
                    curSubFragment.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                //Fragment切换
                BaseFragment fragment = getFragmentByPosition(position);
                if (fragment != null) {
                    if (fragment != curSubFragment) {
                        fragment.onSelected();
                        if (curSubFragment != null) {
                            curSubFragment.onUnSelected();
                        }
                        curSubFragment = fragment;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (curSubFragment != null) {
                    curSubFragment.onPageScrollStateChanged(state);
                }
            }
        });

        //默认选中第一个Framgnet
        curSubFragment = getCurFragment();
        if (curSubFragment != null) {
            curSubFragment.onSelected();
        }

        //Tab
        recommendLayoutBaseline = mainView.findViewById(R.id.recommend_layout_baseline);
        tabStrip = (ClassifyPagerSlidingTabStrip) mainView.findViewById(R.id.tab_strip);
        tabStrip.setTypeface(null, Typeface.NORMAL);
        tabStrip.setViewPager(subViewPager);
        tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //切换到第一个fragment
                if (position == 0) {
                    recommendLayoutBaseline.setVisibility(View.VISIBLE);
                } else {
                    recommendLayoutBaseline.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onSelected() {
        super.onSelected();
    }

    @Override
    public void onUnSelected() {
        super.onUnSelected();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifySubTabDataRequestEvent(ClassifySubTabDataRequestEvent event) {
        if (isDestroy || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_START: {

            }
            break;
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_FINISH: {
                ClassifySubTabDataBean classifySubTabDataBean = event.getArg3();
                //创建数据下发的fragment
                ArrayList<BaseFragment> fragmentList = ClassifySubFragmentFactory.createClassifySubFragmentList(classifySubTabDataBean);
                if (fragmentList != null) {
                    subFragmentList.addAll(fragmentList);
                }
                subPagerAdapter.setFragments(subFragmentList);
                subViewPager.setAdapter(subPagerAdapter);
                tabStrip.setViewPager(subViewPager);
            }
            break;
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(getContext().getApplicationContext(), exception);
            }
            break;
            default:
                break;
        }
    }

    /**
     * 获取当前Fragment
     *
     * @return
     */
    private BaseFragment getCurFragment() {
        if (subViewPager == null) {
            return null;
        }
        int curPosition = subViewPager.getCurrentItem();
        return getFragmentByPosition(curPosition);
    }

    /**
     * 通过position获取Fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragmentByPosition(int position) {
        if (subFragmentList == null) {
            return null;
        }
        return subFragmentList.get(position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        mainView = null;
        controller = null;
        curSubFragment = null;
        recommendLayoutBaseline = null;

        if (subViewPager != null) {
            subViewPager.setAdapter(null);
            subViewPager = null;
        }

        if (tabStrip != null) {
            tabStrip.setOnPageChangeListener(null);
            tabStrip.setViewPager(null);
            tabStrip.removeAllViews();
            tabStrip = null;
        }

        if (subFragmentList != null) {
            subFragmentList.clear();
            subFragmentList = null;
        }

        if (subPagerAdapter != null) {
            subPagerAdapter.destory();
            subPagerAdapter = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
