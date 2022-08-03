package com.richard.user.models;

import java.util.regex.Pattern;

import javax.naming.AuthenticationException;

import com.richard.db.Users;
import com.richard.user.utils.ApiResponse;

public class Admin extends User {
  @Override
  public ApiResponse<User> signup() throws Exception {
    if (Users.findUser(getUserName()) != null) {
      throw new Exception("Admin already exists");
    }
    if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$", getPassword())) {
      throw new Exception("Password rule does not match");
    }
    encryptPassword();
    Users.addUser(this);
    return new ApiResponse<>("Admin signup Successfully", Users.findUser(getUserName()));
  }

  @Override
  public ApiResponse<User> login(String userName, String password) throws AuthenticationException {
    User foundUser = Users.findUser(userName);
    if (foundUser == null)
      throw new AuthenticationException("invalid credentials user does not match");
    if (!password.equals(foundUser.decryptPassword()))
      throw new AuthenticationException("Invalid credentials password does not match");
    return new ApiResponse<>("Successfully loggedIn", foundUser);
  }
}
