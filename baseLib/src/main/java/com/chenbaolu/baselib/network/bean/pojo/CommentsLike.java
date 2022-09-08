package com.chenbaolu.baselib.network.bean.pojo;

public class CommentsLike {

  private long post_id;
  private long comments_id;
  private long user_id;


  public long getPost_id() {
    return post_id;
  }

  public void setPost_id(long post_id) {
    this.post_id = post_id;
  }

  public long getComments_id() {
    return comments_id;
  }

  public void setComments_id(long comments_id) {
    this.comments_id = comments_id;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }
}
