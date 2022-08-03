package com.richard.user.servlets;

import com.richard.user.dtos.Role;
import com.richard.user.models.Admin;
import com.richard.user.models.Patient;
import com.richard.user.models.Pharmacist;
import com.richard.user.models.Physician;
import com.richard.user.models.User;
import com.richard.user.utils.ApiResponse;
import com.richard.user.utils.JsonUtil;
import com.richard.user.utils.ResponseFormat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/signup")
public class SignupServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = new JsonUtil().parseBodyJson(req, Patient.class);
    try {
      ApiResponse<User> result;

      if (user.getRole() == Role.ADMIN) {
        Admin admin = new Admin();
        admin.setFirstName(user.getFirstName());
        admin.setGender(user.getGender());
        admin.setUserName(user.getUserName());
        admin.setRole(user.getRole());
        admin.setLastName(user.getLastName());
        admin.setAge(user.getAge());
        admin.setPhone(user.getPhone());
        admin.setPassword(user.getPassword());
        result = admin.signup();
      } else if (user.getRole() == Role.PATIENT) {
        Patient patient = new Patient();
        patient.setFirstName(user.getFirstName());
        patient.setRole(user.getRole());
        patient.setGender(user.getGender());
        patient.setUserName(user.getUserName());
        patient.setLastName(user.getLastName());
        patient.setAge(user.getAge());
        patient.setPhone(user.getPhone());
        patient.setPassword(user.getPassword());
        result = patient.signup();
      } else if (user.getRole() == Role.PHARMACIST) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setFirstName(user.getFirstName());
        pharmacist.setRole(user.getRole());
        pharmacist.setGender(user.getGender());
        pharmacist.setUserName(user.getUserName());
        pharmacist.setLastName(user.getLastName());
        pharmacist.setAge(user.getAge());
        pharmacist.setPhone(user.getPhone());
        pharmacist.setPassword(user.getPassword());
        result = pharmacist.signup();
      } else {
        Physician physician = new Physician();
        physician.setFirstName(user.getFirstName());
        physician.setGender(user.getGender());
        physician.setUserName(user.getUserName());
        physician.setRole(user.getRole());
        physician.setLastName(user.getLastName());
        physician.setAge(user.getAge());
        physician.setPhone(user.getPhone());
        physician.setPassword(user.getPassword());
        result = physician.signup();
      }
      ResponseFormat.response(resp, result, HttpServletResponse.SC_CREATED);
    } catch (Exception error) {
      error.printStackTrace();
      ResponseFormat.response(resp, new ApiResponse<>(error.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
    }
  }

}
