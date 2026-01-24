package com.prajwal.agrolive.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

@Service
public class UserRegister {

  @Autowired private UserRepo userRepository;

  public String demo(
      String fullName,
      String email,
      String phone,
      String password,
      String confirmPassword,
      Model model) {

    // Validation
    if (!password.equals(confirmPassword)) {
      model.addAttribute("error", "Passwords do not match");
      return "register";
    }

    try {
      User user = new User();
      user.setName(fullName);
      user.setEmail(email);
      user.setPhone(phone);
      user.setPassword(password);

      userRepository.save(user);

      System.out.println("User saved successfully: " + email); // Debug

      return "redirect:/login?success";

    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("error", "Registration failed. Please try again.");
      return "register";
    }
  }
}
