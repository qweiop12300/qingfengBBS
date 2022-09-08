package com.chenbaolu.baselib.network.bean.pojo;


import java.sql.Timestamp;

public class PostComments {

  private long id;
  private long user_id;
  private UserDataConcise user_data;
  private long post_id;
  private long reply_id;
  private java.sql.Timestamp create_date;
  private long like;
  private String title;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public UserDataConcise getUser_data() {
    return user_data;
  }

  public void setUser_data(UserDataConcise user_data) {
    this.user_data = user_data;
  }

  public long getPost_id() {
    return post_id;
  }

  public void setPost_id(long post_id) {
    this.post_id = post_id;
  }

  public long getReply_id() {
    return reply_id;
  }

  public void setReply_id(long reply_id) {
    this.reply_id = reply_id;
  }

  public Timestamp getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Timestamp create_date) {
    this.create_date = create_date;
  }

  public long getLike() {
    return like;
  }

  public void setLike(long like) {
    this.like = like;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
