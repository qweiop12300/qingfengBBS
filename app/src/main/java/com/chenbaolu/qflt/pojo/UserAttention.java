package com.chenbaolu.qflt.pojo;

public class UserAttention {

  private long id;
  private long user_id;
  private long followed_user_id;

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

  public long getFollowed_user_id() {
    return followed_user_id;
  }

  public void setFollowed_user_id(long followed_user_id) {
    this.followed_user_id = followed_user_id;
  }
}
