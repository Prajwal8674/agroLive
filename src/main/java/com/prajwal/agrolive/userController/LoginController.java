package com.prajwal.agrolive.userController;
import com.prajwal.agrolive.userEntity.User;
import com.prajwal.agrolive.userServices.LoginService;
import jakarta.servlet.http.HttpSession;
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
                            HttpSession session,
                            Model model) {

        // 🔹 Get User instead of boolean
        User user = loginService.loginCheckAndGetUser(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }

        // ✅ SESSION CREATED
        session.setAttribute("loggedInUser", user);
        session.setAttribute("email", user.getEmail());

        return "redirect:/";
    }
}
