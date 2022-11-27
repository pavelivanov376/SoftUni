package com.pavel.webapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        if (isLogged(request)) {
            return "redirect:/home";
        }
        return "index.html";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        if (isLogged(request)) {
            return "home.html";
        }
        return "redirect:/";
    }
}
