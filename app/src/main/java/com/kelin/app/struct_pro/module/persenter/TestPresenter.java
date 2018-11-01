package com.kelin.app.struct_pro.module.persenter;

import android.Manifest;
import android.app.Activity;

import com.kelin.app.struct_pro.R;
import com.kelin.app.struct_pro.comm.Constant;
import com.kelin.app.struct_pro.comm.NetworkUtils;
import com.kelin.app.struct_pro.module.bean.DataBean;
import com.kelin.app.struct_pro.module.contract.TestContract;
import com.kelin.app.struct_pro.module.model.TestAPIModel;
import com.kelin.app.struct_pro.module.view.TestActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pub.devrel.easypermissions.EasyPermissions;

public class TestPresenter implements TestContract.Presenter{

    protected Disposable disposable;
    private TestContract.View mView;
    private Activity activity;

    public TestPresenter(TestContract.View androidView) {
        this.mView = androidView;
        this.activity = (Activity) androidView;
    }

    @Override
    public void getTxNews(int page,int num) {
        TestAPIModel.getInstance().getWeChatNews(Constant.TX_KEY, num, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }
                    @Override
                    public void onNext(DataBean data) {
                        if (data != null && data.getNewslist() != null
                                && data.getNewslist().size() > 0) {
                            mView.setView(data.getNewslist());
                        } else {
                            mView.setEmptyView();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.setErrorView();
                        if (disposable != null && !disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }
                    @Override
                    public void onComplete() {
                        mView.showRecyclerView();
                        if (disposable != null && !disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }
                });
    }

    @Override
    public void locationPermissionsTask() {
//        if (hasPermissions()) {
//            //具备权限 直接进行操作
//        } else {
//            //权限拒绝 申请权限
//            //第二个参数是被拒绝后再次申请该权限的解释
//            //第三个参数是请求码
//            //第四个参数是要申请的权限
//            EasyPermissions.requestPermissions(activity,
//                    activity.getResources().getString(R.string.easy_permissions),
//                    RC_LOCATION_CONTACTS_PERM, LOCATION_AND_CONTACTS);
//        }
    }
    @Override
    public void subscribe() {

    }


    @Override
    public void unSubscribe() {
        if(activity!=null){
            activity = null;
        }
    }

}
