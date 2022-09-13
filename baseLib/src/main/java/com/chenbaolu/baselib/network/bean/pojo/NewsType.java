package com.chenbaolu.baselib.network.bean.pojo;



public class NewsType {

  private long id;
  private String title;


  public NewsType() {
  }

  public NewsType(long id, String title) {
    this.id = id;
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
