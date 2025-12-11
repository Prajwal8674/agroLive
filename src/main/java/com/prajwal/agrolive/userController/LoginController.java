package com.prajwal.agrolive.userController;

import com.prajwal.agrolive.userServices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {

        boolean isValid = loginService.loginCheck(email, password);

        if (!isValid) {
            model.addAttribute("error", "Invalid email or password!");
            return "login"; // stay on login page
        }

        // login success
        return "redirect:/"; // redirect to dashboard page
    }
}
