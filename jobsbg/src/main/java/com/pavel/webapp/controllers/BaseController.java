package com.pavel.webapp.controllers;

import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

    protected boolean isLogged(HttpServletRequest request) {
        return  request.getSession().getAttribute("userId") != null;
    }
}
