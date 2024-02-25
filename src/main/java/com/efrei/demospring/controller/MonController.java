package com.efrei.demospring.controller;

import com.efrei.demospring.entity.Maison;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/moncontroller" )
public class MonController {

    @GetMapping(path = "/bonjour")
    public Maison getRessource() {
        return Maison.builder().nomProprio("Robert").numRue("2023 rue du Bonjour").build();
    }


    @GetMapping(path = "/recherche")
    public String getRecherche(@RequestParam(defaultValue = "Je n'ai rien") String text, @RequestParam String location) {
        return "Mon Text " + text + " ma location " + location;
    }

}
