package com.duoduo.main.classify.presenter;

import android.content.Context;

import com.duoduo.commonbase.mvp.presenter.BasePresenter;
import com.duoduo.commonbusiness.net.common.CommonNetErrorHandler;
import com.duoduo.main.classify.data.ClassifySubTabEntity;
import com.duoduo.main.classify.event.ClassifySubTabDataRequestEvent;
import com.duoduo.main.classify.model.ClassifyModel;
import com.duoduo.main.classify.model.IClassifyModel;
import com.duoduo.main.classify.view.IClassifyView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 分类Presenter
 */
public class ClassifyPresenter extends BasePresenter<IClassifyView, IClassifyModel> implements IClassifyPresenter {


    public ClassifyPresenter(Context context, IClassifyView view) {
        super(context, view);
        EventBus.getDefault().register(this);
    }

    @Override
    public IClassifyModel createModel() {
        return new ClassifyModel(context);
    }

    @Override
    public void requestClassifySubTabData() {
        if (model != null) {
            model.requestClassifySubTabData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleClassifySubTabDataRequestEvent(ClassifySubTabDataRequestEvent event) {
        if (view == null || view.isDestroy() || event == null) {
            return;
        }
        int what = event.getWhat();
        switch (what) {
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_START: {

            }
            break;
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_SUCCESS: {
                ClassifySubTabEntity classifySubTabEntity = event.getArg3();
                view.updateSubTabByData(classifySubTabEntity);
            }
            break;
            case ClassifySubTabDataRequestEvent.EVENT_CLASSIFY_SUB_TAB_DATA_REQUEST_ERROR: {
                Exception exception = event.getArg4();
                CommonNetErrorHandler.handleNetError(context, exception);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        EventBus.getDefault().unregister(this);
    }

}
