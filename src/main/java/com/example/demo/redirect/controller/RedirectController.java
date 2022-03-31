package com.example.demo.redirect.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class RedirectController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() {
        return new String("Home");
    }

}
