package com.hongwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class IndexController {
    @RequestMapping(path = {"/index.do", "/"})
    @ResponseBody
    public String index() {
        return "Hello My SpringBoot! - Home Page Service";
    }

    @RequestMapping(path = {"/testAuthorise.do"})
    @ResponseBody
    public String testAuthorise() {
        return "Hello Hongwei! Authorisation test success! Your token is validated!";
    }
}
