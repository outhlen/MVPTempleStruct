package com.kelin.app.struct_pro.module.view;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kelin.app.struct_pro.R;
import com.kelin.app.struct_pro.base.view.BaseActivity;
import com.kelin.app.struct_pro.comm.DensityUtil;
import com.kelin.app.struct_pro.comm.LogUtils;
import com.kelin.app.struct_pro.comm.SizeUtils;
import com.kelin.app.struct_pro.comm.ToastUtils;
import com.kelin.app.struct_pro.module.adapter.TestNewsAdapter;
import com.kelin.app.struct_pro.module.bean.DataBean;
import com.kelin.app.struct_pro.module.contract.TestContract;
import com.kelin.app.struct_pro.module.persenter.TestPresenter;
import com.kelin.app.struct_pro.recyclerview.RecycleViewItemLine;
import com.kelin.app.struct_pro.recyclerview.RefreshRecyclerView;
import com.kelin.app.struct_pro.recyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class TestActivity extends BaseActivity<TestPresenter> implements EasyPermissions.PermissionCallbacks, TestContract.View {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.recycler_view)
    RefreshRecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolBar;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    private long time;
    private int page = 1;
    private int num = 10;
    public static final int RC_LOCATION_CONTACTS_PERM = 124;
    private static final String[] LOCATION_AND_CONTACTS = {
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private TestNewsAdapter adapter;
    private TestContract.Presenter presenter = new TestPresenter(this);
    private View headerView;

    @OnClick(R.id.button)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                /// getServerData();
                break;
        }
    }

    private void getServerData() {
        page = 1;
        recyclerView.showProgress();
        presenter.getTxNews(page, num);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolBar);
        toolbarTitle.setText("新闻");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initPermission();
        initRecyclerView();
    }

    private void initRecyclerView() {

        recyclerView.setRefreshing(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestNewsAdapter(this);
        final RecycleViewItemLine line = new RecycleViewItemLine(this, LinearLayout.HORIZONTAL,
                DensityUtil.dp2px(this, 5.0f), Color.parseColor("#f5f5f7"));
        recyclerView.addItemDecoration(line);
        recyclerView.setAdapter(adapter);
        //initAddHeader();
    }

    private void initAddHeader() {

        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                headerView = View.inflate(getBaseContext(), R.layout.head_view, null);
                return headerView;
            }
            @Override
            public void onBindView(View header) {
                Button tvHomeFirst = (Button) header.findViewById(R.id.button);
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.button:
                                toastUtils.showShort("say hello");
                                break;
                            default:
                                break;
                        }
                    }
                };
                tvHomeFirst.setOnClickListener(listener);
            }
        });

    }

    private void initPermission() {
        //presenter.locationPermissionsTask();
        if (hasPermissions()) {
            //具备权限 直接进行操作

        } else {
            //权限拒绝 申请权限
            EasyPermissions.requestPermissions(this, getResources().getString(R.string.easy_permissions),
                    RC_LOCATION_CONTACTS_PERM, LOCATION_AND_CONTACTS);
        }
    }

    private boolean hasPermissions() {
        return EasyPermissions.hasPermissions(this, LOCATION_AND_CONTACTS);
    }

    @Override
    public void initListener() {
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                adapter.clear();
                recyclerView.showProgress();
                presenter.getTxNews(page, num);
                LogUtils.printJson("onRefresh", "刷新操作===");
            }
        });
        adapter.setMore(R.layout.view_recycle_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (networkUtils.isConnected()) {
                    page = page + 1;
                    presenter.getTxNews(page, num);
                } else {
                    adapter.pauseMore();
                    toastUtils.showShort("网络异常");
                }
            }

            @Override
            public void onMoreClick() {

            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                  toastUtils.showShort("点击条目："+position);
            }
        });


    }

    @Override
    public void initData() {
        getServerData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("触摸监听", "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - time > 1000)) {
                toastUtils.showShort("再按一次返回桌面");
                time = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode,
                permissions, grantResults, TestActivity.this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d("权限", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //某些权限已被拒绝
        Log.d("权限", "onPermissionsDenied:" + requestCode + ":" + perms.size());
//        if (EasyPermissions.somePermissionPermanentlyDenied(TestActivity.this, perms)) {
//            AppSettingsDialog.Builder builder = new AppSettingsDialog.Builder(TestActivity.this);
//            builder.setTitle("允许权限")
//                    .setRationale("没有该权限，此应用程序部分功能可能无法正常工作。打开应用设置界面以修改应用权限")
//                    .setPositiveButton("去设置")
//                    .setNegativeButton("取消")
//                    .setRequestCode(TestPresenter.RC_LOCATION_CONTACTS_PERM)
//                    .build()
//                    .show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            // 当用户从应用设置界面返回的时候，可以做一些事情，比如弹出一个土司。
            Log.d("权限", "onPermissionsDenied:" + requestCode + ":");
        }
    }

    @Override
    public void showRecyclerView() {

    }

    @Override
    public void setView(List<DataBean.NewslistBean> list) {
        if (adapter == null) {
            adapter = new TestNewsAdapter(this);
        }
        adapter.addAll(list);
    }

    @Override
    public void setViewMore(List<DataBean.NewslistBean> list) {
        page++;
        if (list.size() > 0) {
            presenter.getTxNews(page, num);
            adapter.addAll(list);
        } else {
            adapter.pauseMore();
        }

    }

    @Override
    public void stopMore() {
        adapter.pauseMore();
    }

    @Override
    public void setEmptyView() {
        if (recyclerView != null) {
            recyclerView.showEmpty();
            recyclerView.setEmptyView(R.layout.view_custom_empty_data);
        }
    }

    @Override
    public void setErrorView() {
        if (recyclerView != null) {
            recyclerView.showError();
            recyclerView.setErrorView(R.layout.view_custom_empty_data);
        }
    }

    @Override
    public void setNetworkErrorView() {
        recyclerView.showError();
        recyclerView.setErrorView(R.layout.view_custom_empty_data);
    }
}
