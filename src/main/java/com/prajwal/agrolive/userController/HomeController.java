package com.prajwal.agrolive.userController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    // Get current authenticated user
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null
        && authentication.isAuthenticated()
        && !authentication.getPrincipal().equals("anonymousUser")) {
      String email = authentication.getName();
      model.addAttribute("loggedInUser", email);
      model.addAttribute("isAuthenticated", true);
    } else {
      model.addAttribute("isAuthenticated", false);
    }

    return "index";
  }

  @GetMapping("/test")
  @ResponseBody
  public String test() {
    return "TEST SUCCESSFUL - Spring Security is working!";
  }
}
