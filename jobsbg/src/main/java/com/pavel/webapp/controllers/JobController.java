package com.pavel.webapp.controllers;

import com.pavel.webapp.dto.CreateJobDto;
import com.pavel.webapp.services.JobOfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController extends BaseController {
    private final JobOfferService jobOfferService;

    public JobController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping("/create/job")
    public String createJob(HttpServletRequest request) {
//        if (!isLogged(request)) {
//            return "redirect:/";
//        }
        return "jobs/createJob.html";
    }

    @PostMapping("/create/job")
    public String createJob(CreateJobDto jobDto, Model model, HttpServletRequest request) {
//        if (!isLogged(request)) {
//            return "redirect:/";
//        }

        jobOfferService.create(jobDto);
        return "redirect:/";
    }
}