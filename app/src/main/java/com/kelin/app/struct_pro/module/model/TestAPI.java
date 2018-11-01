package com.kelin.app.struct_pro.module.model;

import com.kelin.app.struct_pro.bean.Response;
import com.kelin.app.struct_pro.module.bean.DataBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TestAPI {

    @GET("wxnew/")
    Observable<DataBean> getWeChatNews(@Query("key") String key,
                                       @Query("num") int num,
                                       @Query("page") int page);

    @FormUrlEncoded
    @POST("version")
    Observable<Response> getVersionInfo();


}
