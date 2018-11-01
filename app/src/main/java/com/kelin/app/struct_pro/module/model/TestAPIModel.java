package com.kelin.app.struct_pro.module.model;

import com.kelin.app.struct_pro.bean.Response;
import com.kelin.app.struct_pro.comm.Constant;
import com.kelin.app.struct_pro.module.bean.DataBean;
import com.kelin.app.struct_pro.network.RetrofitWrapper;

import io.reactivex.Observable;

public class TestAPIModel implements TestAPI  {

    private static TestAPIModel model;
    private TestAPI mApiService;

    private TestAPIModel() {
        mApiService = RetrofitWrapper
                .getInstance(Constant.TX_HTTP)
                .create(TestAPI.class);
    }

    public static TestAPIModel getInstance(){
        if(model == null) {
            model = new TestAPIModel();
        }
        return model;
    }


    @Override
    public Observable<DataBean> getWeChatNews(String key, int num, int page) {
        return mApiService.getWeChatNews(key, num, page);
    }

    public Observable<Response> getVersionInfo() {
        return mApiService.getVersionInfo();
    }
}
