package com.chenbaolu.qflt.pojo;


import java.sql.Timestamp;

public class UserNews {

  private long id;
  private long user_id;
  private long produce_user_id;
  private long type;
  private long post_id;
  private java.sql.Timestamp create_date;
  private String content;

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

  public long getProduce_user_id() {
    return produce_user_id;
  }

  public void setProduce_user_id(long produce_user_id) {
    this.produce_user_id = produce_user_id;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public long getPost_id() {
    return post_id;
  }

  public void setPost_id(long post_id) {
    this.post_id = post_id;
  }

  public Timestamp getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Timestamp create_date) {
    this.create_date = create_date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
