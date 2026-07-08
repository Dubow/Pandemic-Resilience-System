package com.prs.backend.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login-page")
    public String login() {
        return "login";
    }

    @GetMapping("/register-page")
    public String register() {
        return "register";
    }

    @GetMapping("/government-dashboard")
    public String governmentDashboard() {
        return "government-dashboard";
    }

    @GetMapping("/merchant-dashboard")
    public String merchantDashboard() {
        return "merchant-dashboard";
    }

    @GetMapping("/public-dashboard")
    public String publicDashboard() {
        return "public-dashboard";
    }
}