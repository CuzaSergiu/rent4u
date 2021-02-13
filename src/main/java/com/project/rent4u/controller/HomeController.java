package com.project.rent4u.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // == methods ==
    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

}
