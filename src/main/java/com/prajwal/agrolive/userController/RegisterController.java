package com.prajwal.agrolive.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userRepository.UserRepo;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Add any model attributes if needed
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {

        System.out.println("Registration attempt for: " + email); // Debug

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