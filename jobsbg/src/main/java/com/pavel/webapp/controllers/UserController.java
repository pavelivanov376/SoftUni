package com.pavel.webapp.controllers;

import com.pavel.webapp.dto.UserLoginDto;
import com.pavel.webapp.dto.UserRegisterDto;
import com.pavel.webapp.services.PersonAccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final PersonAccountService personAccountService;

    public UserController(PersonAccountService personAccountService) {
        this.personAccountService = personAccountService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register.html";
    }

    @PostMapping("/users/register")
    public String register(UserRegisterDto user, Model model) {
        boolean isRegistered = personAccountService.register(user);
        if (isRegistered) {
            return "redirect:/users/login";
        }
        model.addAttribute("error", "There is an error!");
        return "user/register";
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login.html";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDto userLoginDto, Model model, HttpServletRequest httpServletRequest) {

        var userId = personAccountService.validateUserLogin(userLoginDto);
        if (userId == null) {
            model.addAttribute("error", "There is an error!");
            return "user/login.html";
        }
        httpServletRequest.getSession().setAttribute("userId", userId);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("userId");
        return "redirect:/";
    }
}
