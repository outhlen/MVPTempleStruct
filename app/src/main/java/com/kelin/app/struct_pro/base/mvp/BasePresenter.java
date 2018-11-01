package com.kelin.app.struct_pro.base.mvp;

public interface BasePresenter {
    //绑定数据
    void subscribe();
    //解除绑定
    void unSubscribe();
}
