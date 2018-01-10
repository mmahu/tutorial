package com.mmahu.templates.broker.rabbit.gateway.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

}
