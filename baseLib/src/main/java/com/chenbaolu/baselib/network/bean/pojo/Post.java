package com.chenbaolu.baselib.network.bean.pojo;


import java.sql.Timestamp;

public class Post {

  private long id;
  private long user_id;
  private UserDataConcise user_data;
  private long type_id;
  private PostType post_type;
  private String images;
  private String title;
  private String content;
  private long comments;
  private long collects;
  private long view;
  private long like;
  private java.sql.Timestamp create_date;
  private java.sql.Timestamp update_date;
  private Boolean top;
  private Boolean essence;
  private Long plu;
  private Long pcu;



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

  public long getType_id() {
    return type_id;
  }

  public void setType_id(long type_id) {
    this.type_id = type_id;
  }

  public PostType getPost_type() {
    return post_type;
  }

  public void setPost_type(PostType post_type) {
    this.post_type = post_type;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getComments() {
    return comments;
  }

  public void setComments(long comments) {
    this.comments = comments;
  }

  public long getCollects() {
    return collects;
  }

  public void setCollects(long collects) {
    this.collects = collects;
  }

  public long getView() {
    return view;
  }

  public void setView(long view) {
    this.view = view;
  }

  public long getLike() {
    return like;
  }

  public void setLike(long like) {
    this.like = like;
  }

  public Timestamp getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Timestamp create_date) {
    this.create_date = create_date;
  }

  public Timestamp getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(Timestamp update_date) {
    this.update_date = update_date;
  }

  public Boolean getTop() {
    return top;
  }

  public void setTop(Boolean top) {
    this.top = top;
  }

  public Boolean getEssence() {
    return essence;
  }

  public void setEssence(Boolean essence) {
    this.essence = essence;
  }

  public Long getPlu() {
    return plu;
  }

  public void setPlu(Long plu) {
    this.plu = plu;
  }

  public Long getPcu() {
    return pcu;
  }

  public void setPcu(Long pcu) {
    this.pcu = pcu;
  }
}
