package com.prajwal.agrolive.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    // ADD THIS FOR TESTING
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "TEST SUCCESSFUL - Controllers are working!";
    }
}