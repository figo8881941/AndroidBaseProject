package com.duoduo.main.classify;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.duoduo.commonbase.utils.StatusBarUtils;
import com.duoduo.commonbase.utils.TextViewUtils;
import com.duoduo.commonbase.view.PagerSlidingTabStrip;
import com.duoduo.commonbusiness.fragment.BaseFragment;
import com.duoduo.commonbusiness.router.path.IGlobalPath;
import com.duoduo.commonbusiness.router.path.IWeexPath;
import com.duoduo.main.R;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.presenter.ClassifyPresenter;
import com.duoduo.main.classify.presenter.IClassifyPresenter;
import com.duoduo.main.classify.view.ClassifySubFragmentHelper;
import com.duoduo.main.classify.view.ClassifySubFragmentPagerAdapter;
import com.duoduo.main.classify.view.IClassifyView;
import com.duoduo.main.main.data.MainTabEntity;

import java.util.ArrayList;

/**
 * 分类Fragment
 */
public class ClassifyFragment extends BaseFragment<MainTabEntity.TabListEntity> implements IClassifyView {

    private IClassifyPresenter classifyPresenter;

    private ViewGroup mainView;

    private PagerSlidingTabStrip tabStrip;
    private TextView recommendText;
    private View recommendLayoutBaseline;
    private TextView currentSubTabItem;

    private ViewPager subViewPager;
    private ClassifySubFragmentPagerAdapter subPagerAdapter;

    private ArrayList<BaseFragment> subFragmentList;

    //当前Fragment
    private BaseFragment curSubFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classifyPresenter = new ClassifyPresenter(getActivity().getApplicationContext(), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = (ViewGroup) inflater.inflate(R.layout.main_classify_fragment, container, false);
        initView();
        classifyPresenter.requestClassifySubTabData();
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
        subFragmentList = ClassifySubFragmentHelper.createInitClassifySubFragmentList(getContext().getApplicationContext());
        subPagerAdapter.setFragments(subFragmentList);
        subViewPager.setAdapter(subPagerAdapter);
        subViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (isDestroy) {
                    return;
                }
                if (curSubFragment != null) {
                    curSubFragment.onPageScrolled(position, positionOffset, positionOffsetPixels);
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
                if (isDestroy) {
                    return;
                }
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
        recommendText = (TextView) mainView.findViewById(R.id.recommend_text);
        recommendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subViewPager != null) {
                    subViewPager.setCurrentItem(0, false);
                }
            }
        });
        TextViewUtils.setTextStyleAndStrokeWidth(recommendText, Paint.Style.FILL_AND_STROKE, 1);
        recommendLayoutBaseline = mainView.findViewById(R.id.recommend_layout_baseline);
        tabStrip = (PagerSlidingTabStrip) mainView.findViewById(R.id.tab_strip);
        tabStrip.setTypeface(null, Typeface.NORMAL);
        tabStrip.setViewPager(subViewPager);
        Resources resources = getResources();
        final int subTabItemTextSelectedColor = resources.getColor(R.color.main_classify_fragment_sub_tab_item_text_selected_color);
        final int subTabItemTextUnSelectedColor = resources.getColor(R.color.main_classify_fragment_sub_tab_item_text_unselected_color);
        tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (isDestroy) {
                    return;
                }
                TextView newCurSubTabItem = (TextView) tabStrip.getTabItem(position);
                if (position == 0) {
                    //切换到第一个fragment
                    recommendLayoutBaseline.setVisibility(View.VISIBLE);
                    TextViewUtils.setTextStyleAndStrokeWidth(recommendText, Paint.Style.FILL_AND_STROKE, 1);
                    recommendText.setTextColor(subTabItemTextSelectedColor);
                    if (currentSubTabItem != null) {
                        TextViewUtils.setTextStyleAndStrokeWidth(currentSubTabItem, Paint.Style.FILL_AND_STROKE, 0);
                        currentSubTabItem.setTextColor(subTabItemTextUnSelectedColor);
                    }
                } else {
                    recommendLayoutBaseline.setVisibility(View.INVISIBLE);
                    TextViewUtils.setTextStyleAndStrokeWidth(recommendText, Paint.Style.FILL_AND_STROKE, 0);
                    recommendText.setTextColor(subTabItemTextUnSelectedColor);
                    if (newCurSubTabItem != currentSubTabItem) {
                        TextViewUtils.setTextStyleAndStrokeWidth(newCurSubTabItem, Paint.Style.FILL_AND_STROKE, 1);
                        newCurSubTabItem.setTextColor(subTabItemTextSelectedColor);
                        if (currentSubTabItem != null) {
                            TextViewUtils.setTextStyleAndStrokeWidth(currentSubTabItem, Paint.Style.FILL_AND_STROKE, 0);
                            currentSubTabItem.setTextColor(subTabItemTextUnSelectedColor);
                        }
                    }
                }
                currentSubTabItem = newCurSubTabItem;
            }
        });
        currentSubTabItem = (TextView) tabStrip.getTabItem(0);

        View carLayout = mainView.findViewById(R.id.car_layout);
        carLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ARouter.getInstance().build("/main/demo/fileProviderTest").navigation();
//                ARouter.getInstance()
//                        .build(IWebPath.COMMON_WEBVIEW_ACTIVITY)
//                        .withString("htmlUrl", "http://www.baidu.com")
//                        .withBoolean("controlPageBack",true)
//                        .withBoolean("showTitle", true)
//                        //.withBoolean("showToolbar", true)
//                        .navigation();
//                try {
//                    String url = IGlobalPath.GLOBAL_SCHEME_HOST + IWebPath.COMMON_WEBVIEW_ACTIVITY +
//                    "?showTitle=true&withHead=false&controlPageBack=true&usePost=false&htmlUrl=" + URLEncoder.encode("http://www.baidu.com", "UTF-8");
//                    ARouter.getInstance()
//                            .build(Uri.parse(url))
//                            .navigation();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                try {
                    String url = IGlobalPath.GLOBAL_SCHEME_HOST + IWeexPath.COMMON_WEEX_ACTIVITY;
                    ARouter.getInstance()
                            .build(Uri.parse(url))
                            .navigation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                try {
//                    String url = IGlobalPath.GLOBAL_SCHEME_HOST + IWebPath.COMMON_WEBVIEW_ACTIVITY +
//                            "?showTitle=true&withHead=false&controlPageBack=true&usePost=false&htmlUrl=" + URLEncoder.encode("http://47.101.55.211:14444/frontend_service/common?funid=2033&appid=2&service=static_pages&isapp=1&prd_id=4000", "UTF-8");
//                    ARouter.getInstance()
//                            .build(Uri.parse(url))
//                            .navigation();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * 数据初始化子分类Tab
     *
     * @param classifySubTabEntity
     */
    @Override
    public void updateSubTabByData(ClassifySubTabEntity classifySubTabEntity) {
        //创建数据下发的fragment
        ArrayList<BaseFragment> fragmentList = ClassifySubFragmentHelper.createClassifySubFragmentList(classifySubTabEntity);
        if (fragmentList != null) {
            subFragmentList.addAll(fragmentList);
        }
        subPagerAdapter.setFragments(subFragmentList);
        subPagerAdapter.notifyDataSetChanged();
        tabStrip.notifyDataSetChanged();
    }

    @Override
    public void onSelected() {
        super.onSelected();
    }

    @Override
    public void onUnSelected() {
        super.onUnSelected();
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

        if (classifyPresenter != null) {
            classifyPresenter.destroy();
            classifyPresenter = null;
        }

        mainView = null;
        curSubFragment = null;
        recommendLayoutBaseline = null;

        if (subViewPager != null) {
            subViewPager.setAdapter(null);
            subViewPager = null;
        }

        if (tabStrip != null) {
            tabStrip.setOnPageChangeListener(null);
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
