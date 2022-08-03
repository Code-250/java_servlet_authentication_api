package com.richard.user.models;

import java.util.regex.Pattern;

import javax.naming.AuthenticationException;

import com.richard.db.Users;
import com.richard.user.utils.ApiResponse;

public class Physician extends User {
  @Override
  public ApiResponse<User> signup() throws Exception {
    if (Users.findUser(getUserName()) != null) {
      throw new Exception("Physician already exists");
    }
    if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{5,}$", getPassword())) {
      throw new Exception("Password rules does not match");
    }
    encryptPassword();
    Users.addUser(this);
    return new ApiResponse<>("physician signup Successfully", Users.findUser(getUserName()));
  };

  @Override
  public ApiResponse<User> login(String userName, String password) throws AuthenticationException {
    User foundUser = Users.findUser(userName);
    if (foundUser == null)
      throw new AuthenticationException("invalid credentials");
    if (!password.equals(foundUser.decryptPassword()))
      throw new AuthenticationException("Invalid credentials");
    return new ApiResponse<>("Successfully loggedIn", foundUser);
  }
}
