package com.chenbaolu.qflt.CallBack;

import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 18:14
 * 作者 : 23128
 */
public  interface PostCommentsCallBack {
    void setComments(PostCommentsDto postCommentsDto);
    void backContent(String title);
}
