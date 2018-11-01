package com.kelin.app.struct_pro.api;

import com.kelin.app.struct_pro.bean.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  Request {

    @FormUrlEncoded
    @POST("?service=sser.getList")
    Observable<Response<List<Object>>> getList(@Query("id") String id);

}
