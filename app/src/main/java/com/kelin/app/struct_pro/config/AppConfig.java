package com.kelin.app.struct_pro.config;

import com.kelin.app.struct_pro.comm.Constant;
import com.kelin.app.struct_pro.comm.SPUtils;

/**
 * <pre>
 *     desc         所有的配置
 *     revise
 *     GitHub       https://github.com/yangchong211
 * </pre>
 */
public class AppConfig {

    private boolean isLogin;
    private String bannerUrl;
    private boolean isNight;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        SPUtils.getInstance(Constant.SP_NAME).put(Constant.KEY_IS_LOGIN,login);
        this.isLogin = login;
    }
    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        SPUtils.getInstance(Constant.SP_NAME).put(Constant.KEY_BANNER_URL,bannerUrl);
        this.bannerUrl = bannerUrl;
    }

}
