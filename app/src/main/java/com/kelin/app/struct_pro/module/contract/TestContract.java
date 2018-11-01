package com.kelin.app.struct_pro.module.contract;

import com.kelin.app.struct_pro.base.mvp.BasePresenter;
import com.kelin.app.struct_pro.base.mvp.BaseView;
import com.kelin.app.struct_pro.module.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

public interface TestContract {

    interface View extends BaseView {
        void showRecyclerView();
        void setView(List<DataBean.NewslistBean> list);
        void setViewMore(List<DataBean.NewslistBean> list);
        void stopMore();
        void setEmptyView();
        void setErrorView();
        void setNetworkErrorView();
    }

    //Presenter控制器
    interface Presenter extends BasePresenter {
        void getTxNews(int page ,int num);
        void locationPermissionsTask();
    }

}
