package com.prajwal.agrolive.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

@Controller
public class RegisterController {

  @Autowired private UserRepo userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @GetMapping("/register")
  public String showRegisterPage(Model model) {
    return "register";
  }

  @PostMapping("/register")
  public String registerUser(
      @RequestParam String fullName,
      @RequestParam String email,
      @RequestParam String phone,
      @RequestParam String password,
      @RequestParam String confirmPassword,
      Model model) {

    System.out.println("Registration attempt for: " + email);

    // Validation
    if (fullName == null || fullName.trim().isEmpty()) {
      model.addAttribute("error", "Full name is required!");
      return "register";
    }

    if (email == null || email.trim().isEmpty()) {
      model.addAttribute("error", "Email is required!");
      return "register";
    }

    if (phone == null || phone.trim().isEmpty()) {
      model.addAttribute("error", "Phone number is required!");
      return "register";
    }

    if (password == null || password.length() < 6) {
      model.addAttribute("error", "Password must be at least 6 characters!");
      return "register";
    }

    if (!password.equals(confirmPassword)) {
      model.addAttribute("error", "Passwords do not match!");
      return "register";
    }

    // Check if user already exists
    if (userRepository.existsByEmail(email)) {
      model.addAttribute("error", "Email already registered!");
      return "register";
    }

    try {
      // Create new user with encrypted password
      User newUser = new User();
      newUser.setName(fullName);
      newUser.setEmail(email);
      newUser.setPhone(phone);
      newUser.setPassword(passwordEncoder.encode(password)); 
      newUser.setRole("ROLE_USER");
      newUser.setEnabled(true);

      userRepository.save(newUser);

      model.addAttribute("success", "Registration successful! Please login.");
      return "login";

    } catch (Exception e) {
      model.addAttribute("error", "Registration failed! Please try again.");
      e.printStackTrace();
      return "register";
    }
  }
}
