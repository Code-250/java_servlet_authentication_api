package com.richard.db;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.richard.user.models.User;

import lombok.Getter;

@Getter
public class Users {
  private static Map<String, User> all = new LinkedHashMap<>();

  public static void addUser(User user) {
    all.put(user.getUserName(), user);
  }

  public static User findUser(String userName) {
    return all.get(userName);
  }

  public static List<User> getUsers() {
    return new ArrayList<>(all.values());
  }

}
