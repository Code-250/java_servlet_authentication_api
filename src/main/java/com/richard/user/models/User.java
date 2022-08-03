package com.richard.user.models;

import java.util.UUID;

import javax.naming.AuthenticationException;

import com.richard.user.utils.ApiResponse;
import com.google.gson.annotations.Expose;
import com.richard.user.dtos.Gender;
import com.richard.user.dtos.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public abstract class User {
  private String id;
  @NonNull
  private String firstName;
  @NonNull
  private String lastName;
  @NonNull
  private String userName;
  @NonNull
  @Expose(serialize = false)
  protected String password;
  private Gender gender;
  private Integer age;
  private Long phone;
  private Role role;

  public User() {
    id = UUID.randomUUID().toString();
  }

  public abstract ApiResponse<User> signup() throws Exception;

  public abstract ApiResponse<User> login(String userName, String password) throws AuthenticationException;

  public void encryptPassword() {
    String encryptedPassword = "";
    for (int i = 0; i < password.length(); i++) {
      encryptedPassword = password.charAt(i) + encryptedPassword;
    }
    encryptedPassword = "**" + encryptedPassword + "<>" + age + "**";
    this.setPassword(encryptedPassword);
  }

  public String decryptPassword() {
    String decryptPassword = "";
    String passwordReverse = password.substring(2, password.length() - 2).split("<>")[0];
    for (int i = 0; i < passwordReverse.length(); i++) {
      decryptPassword = passwordReverse.charAt(i) + decryptPassword;
    }
    return decryptPassword;
  }
}
