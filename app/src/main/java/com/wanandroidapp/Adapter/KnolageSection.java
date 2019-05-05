package com.wanandroidapp.Adapter;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.wanandroidapp.jiexi.KnolageClass;

public class KnolageSection extends SectionEntity<KnolageClass.DataBean.ChildrenBean> {
    public KnolageSection(boolean isHeader, String header) {
        super(isHeader, header);
    }
    public KnolageSection(KnolageClass.DataBean.ChildrenBean childrenBean){
        super(childrenBean);
    }
}
