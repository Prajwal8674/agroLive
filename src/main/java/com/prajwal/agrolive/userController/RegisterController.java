package com.prajwal.agrolive.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.prajwal.agrolive.userRepository.UserRepo;
import com.prajwal.agrolive.userServices.UserRegister;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private UserRegister userRegister;

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

        return userRegister.demo(fullName, email, phone, password, confirmPassword, model);
     
    }
}