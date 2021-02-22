package com.noahgeren.trailangel.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noahgeren.trailangel.services.UserService;

@Controller
public class BaseController implements ErrorController {
	
	@Autowired
	private UserService userService;

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public Object register(HttpServletRequest request) {
		// TODO: Add registering here
		return null;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String login() {
		return "Hello World!";
	}
	
	@ResponseBody
	@RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // TODO: change this to return an object
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorpages/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorpages/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errorpages/403";
            }
        }

        // display generic error
        return "errorpages/error";
    }

}
