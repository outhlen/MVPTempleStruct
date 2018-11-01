package com.kelin.app.struct_pro.recyclerview.event;

import android.view.View;

import com.kelin.app.struct_pro.recyclerview.adapter.RecyclerArrayAdapter;

public interface EventDelegateAble {

    void addData(int length);
    void clear();
    void stopLoadMore();
    void pauseLoadMore();
    void resumeLoadMore();

    void setMore(View view, RecyclerArrayAdapter.OnMoreListener listener);
    void setNoMore(View view, RecyclerArrayAdapter.OnNoMoreListener listener);
    void setErrorMore(View view, RecyclerArrayAdapter.OnErrorListener listener);
    void setMore(int res, RecyclerArrayAdapter.OnMoreListener listener);
    void setNoMore(int res, RecyclerArrayAdapter.OnNoMoreListener listener);
    void setErrorMore(int res, RecyclerArrayAdapter.OnErrorListener listener);

}
