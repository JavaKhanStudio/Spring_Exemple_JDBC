package com.efrei.demospring.newway.controller;

import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.newway.service.AssuranceServiceNew;
import com.efrei.demospring.oldway.service.AssuranceServiceOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/personne/new")
public class PersonneNewController {

    private final AssuranceServiceNew assuranceServiceNew;

    public PersonneNewController(AssuranceServiceNew assuranceServiceNew) {
        this.assuranceServiceNew = assuranceServiceNew;
    }

    @PostMapping
    public ResponseEntity addPersonne(@RequestBody Personne personne){
        try {
            assuranceServiceNew.createPersonne(personne) ;
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPersonne(@PathVariable int id) {
        try {
            Personne personne = assuranceServiceNew.getPersonneByID(id) ;
            if(personne != null) {
                return ResponseEntity.ok(personne);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
            }
        } catch(Exception e) {
            System.out.println("I am an exception");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
