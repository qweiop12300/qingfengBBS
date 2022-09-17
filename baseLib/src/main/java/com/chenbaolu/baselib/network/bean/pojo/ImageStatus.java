package com.chenbaolu.baselib.network.bean.pojo;

import android.net.Uri;


/**
 * 描述 :
 * 创建时间 : 2022/9/17 21:28
 * 作者 : 23128
 */
public class ImageStatus {
    private Uri uri;
    private String url;

    public ImageStatus() {
    }

    public ImageStatus(Uri uri, String url) {
        this.uri = uri;
        this.url = url;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
