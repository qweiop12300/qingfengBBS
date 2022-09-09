package com.chenbaolu.baselib.network.bean.pojo;

import java.sql.Timestamp;

public class UserData {

  private long user_id;
  private String avatar;
  private String name;
  private Boolean sex;
  private long type;
  private String user_describe;
  private java.sql.Timestamp create_date;

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getSex() {
    return sex;
  }

  public void setSex(Boolean sex) {
    this.sex = sex;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public String getUser_describe() {
    return user_describe;
  }

  public void setUser_describe(String user_describe) {
    this.user_describe = user_describe;
  }

  public Timestamp getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Timestamp create_date) {
    this.create_date = create_date;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "user_id=" + user_id +
            ", avatar='" + avatar + '\'' +
            ", name='" + name + '\'' +
            ", sex=" + sex +
            ", type=" + type +
            ", user_describe='" + user_describe + '\'' +
            ", create_date=" + create_date +
            '}';
  }
}
