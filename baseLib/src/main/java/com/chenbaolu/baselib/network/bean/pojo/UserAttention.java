package com.chenbaolu.baselib.network.bean.pojo;

public class UserAttention {

  private long id;
  private long user_id;
  private UserData user_data;
  private long followed_user_id;
  private UserData fan_user_data;

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

  public UserData getUser_data() {
    return user_data;
  }

  public void setUser_data(UserData user_data) {
    this.user_data = user_data;
  }

  public UserData getFan_user_data() {
    return fan_user_data;
  }

  public void setFan_user_data(UserData fan_user_data) {
    this.fan_user_data = fan_user_data;
  }
}
