package com.kelin.app.struct_pro.module.view;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kelin.app.struct_pro.R;
import com.kelin.app.struct_pro.base.view.BaseActivity;
import com.kelin.app.struct_pro.comm.ImageUtils;
import com.kelin.app.struct_pro.module.listener.PerfectClickListener;

import butterknife.Bind;

public class MainActivity extends BaseActivity  implements View.OnClickListener{

    @Bind(R.id.fl_title_menu)
    FrameLayout flTitleMenu;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.setting)
    TextView setting;
    @Bind(R.id.quit)
    TextView quit;

    private ImageView ivAvatar;
    TextView tvUsername;
    private LinearLayout llNavScanDownload;
    private LinearLayout llNavFeedback;
    private LinearLayout llNavAbout;
    private LinearLayout llNavLogin;
    private LinearLayout llNavVideo;
    private LinearLayout llNavHomepage;
    private View view;
    private long time;

    @Override
    public int setContentView() {
        return R.layout.main_layout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_home, menu);
        return true;
    }


    @Override
    public void initView() {
        initBar();
        initNav();
    }

    private void initBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 初始化侧滑菜单
     */
    private void initNav() {
        view = navView.inflateHeaderView(R.layout.nav_header_main);
        ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        ImageUtils.showImageViewToCircle(this, R.mipmap.ic_person_logo, ivAvatar);
        tvUsername.setText("测试");
    }
    @Override
    public void initListener() {
        flTitleMenu.setOnClickListener(MainActivity.this);
        navView.setOnClickListener(MainActivity.this);
        //侧滑点击事件
        if (view != null) {
            ivAvatar.setOnClickListener(listener);
            tvUsername.setOnClickListener(listener);
            setting.setOnClickListener(listener);
            quit.setOnClickListener(listener);
        }
    }

    @Override
    public void initData() {

    }

    /**
     * 自定义菜单点击事件
     */
    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            drawerLayout.closeDrawer(GravityCompat.START);
            drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {
                        case R.id.iv_avatar:
                            toastUtils.showShort("我的头像");
                            break;
                        // 主页
                        case R.id.tv_username:
                            toastUtils.showShort("测试菜单");
                            break;
                        default:
                            break;
                    }
                }
            }, 0);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_title_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
    /**
     * 是当某个按键被按下是触发。所以也有人在点击返回键的时候去执行该方法来做判断
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("触摸监听", "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                //双击返回桌面
                if ((System.currentTimeMillis() - time > 1000)) {
                    toastUtils.showShort( "再按一次返回桌面");
                    time = System.currentTimeMillis();
                } else {
                    moveTaskToBack(true);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
