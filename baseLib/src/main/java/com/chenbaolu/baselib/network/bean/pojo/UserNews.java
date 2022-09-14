package com.chenbaolu.baselib.network.bean.pojo;


import java.sql.Timestamp;

public class UserNews {

  private long id;
  private long user_id;
  private UserData user_data;
  private UserData p_user_data;
  private long produce_user_id;
  private NewsType news_type;
  private long type;
  private long post_id;
  private java.sql.Timestamp create_date;
  private String content;
  private int is_view;

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


  public UserData getP_user_data() {
    return p_user_data;
  }

  public void setP_user_data(UserData p_user_data) {
    this.p_user_data = p_user_data;
  }

  public NewsType getNews_type() {
    return news_type;
  }

  public void setNews_type(NewsType news_type) {
    this.news_type = news_type;
  }

  public UserData getUser_data() {
    return user_data;
  }

  public void setUser_data(UserData user_data) {
    this.user_data = user_data;
  }

  public int getIs_view() {
    return is_view;
  }

  public void setIs_view(int is_view) {
    this.is_view = is_view;
  }

  @Override
  public String toString() {
    return "UserNews{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", user_data=" + user_data +
            ", p_user_data=" + p_user_data +
            ", produce_user_id=" + produce_user_id +
            ", news_type=" + news_type +
            ", type=" + type +
            ", post_id=" + post_id +
            ", create_date=" + create_date +
            ", content='" + content + '\'' +
            '}';
  }
}
