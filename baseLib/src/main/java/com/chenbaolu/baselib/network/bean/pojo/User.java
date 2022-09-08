package com.chenbaolu.baselib.network.bean.pojo;


public class User {

  private long id;
  private String account;
  private String password;
  private String email;
  private Boolean is_activation;
  private String activation_data;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getIs_activation() {
    return is_activation;
  }

  public void setIs_activation(Boolean is_activation) {
    this.is_activation = is_activation;
  }

  public String getActivation_data() {
    return activation_data;
  }

  public void setActivation_data(String activation_data) {
    this.activation_data = activation_data;
  }
}
