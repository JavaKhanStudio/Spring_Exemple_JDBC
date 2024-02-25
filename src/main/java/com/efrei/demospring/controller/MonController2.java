package com.efrei.demospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/mon" )
public class MonController2 {

    @GetMapping(path = "/bonjour")
    public String getRessource() {
        return "String Mon Controller 2" ;
    }


}
