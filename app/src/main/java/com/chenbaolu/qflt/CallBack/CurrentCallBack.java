package com.chenbaolu.qflt.CallBack;

import androidx.recyclerview.widget.RecyclerView;

import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;

/**
 * 描述 :
 * 创建时间 : 2022/9/9 09:45
 * 作者 : 23128
 */
public interface CurrentCallBack {
    void refresh(Integer pg,Integer pz,int typeId, RecyclerView.Adapter adapter);
}
