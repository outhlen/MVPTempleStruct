package com.kelin.app.struct_pro.module.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelin.app.struct_pro.R;
import com.kelin.app.struct_pro.comm.ImageUtils;
import com.kelin.app.struct_pro.module.bean.DataBean;
import com.kelin.app.struct_pro.recyclerview.adapter.RecyclerArrayAdapter;
import com.kelin.app.struct_pro.recyclerview.viewholder.BaseViewHolder;

public class TestNewsAdapter extends RecyclerArrayAdapter<DataBean.NewslistBean> {

    public TestNewsAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExpressDeliveryViewHolder(parent);
    }

    private class ExpressDeliveryViewHolder extends BaseViewHolder<DataBean.NewslistBean> {

        ImageView iv_logo;
        TextView tv_title , tv_time;

        ExpressDeliveryViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_tx_news_list);
            tv_title = getView(R.id.tv_title);
            tv_time = getView(R.id.tv_time);
            iv_logo = getView(R.id.iv_logo);
        }

        @Override
        public void setData(DataBean.NewslistBean data) {
            super.setData(data);
            if(data.getTitle()!=null && data.getTitle().length()>0){
                tv_title.setText(data.getTitle());
            }
            if(data.getCtime()!=null && data.getCtime().length()>0){
                tv_time.setText(data.getCtime());
            }
            ImageUtils.showImageView(getContext(),data.getPicUrl(), iv_logo);
        }
    }
}
