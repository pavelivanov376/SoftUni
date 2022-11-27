package com.pavel.webapp.controllers;

import com.pavel.webapp.entities.JobOffer;
import com.pavel.webapp.repositories.JobOfferRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController extends BaseController {
    private final JobOfferRepository jobOfferRepository;

    public HomeController(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        if (isLogged(request)) {
            return "redirect:/home";
        }
        return "index.html";
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        if (isLogged(request)) {
            List<JobOffer> jobs = jobOfferRepository.findAll();
            model.addAttribute("jobs", jobs);
            return "home.html";
        }
        return "redirect:/";
    }
}
