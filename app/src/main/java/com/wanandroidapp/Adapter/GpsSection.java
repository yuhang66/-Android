package com.wanandroidapp.Adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.wanandroidapp.jiexi.GpsClass;

public class GpsSection extends SectionEntity<GpsClass.DataBean.ArticlesBean> {


    public GpsSection(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public GpsSection(GpsClass.DataBean.ArticlesBean bean){
        super(bean);
    }
}
