package com.efrei.demospring.oldway.controller;

import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.oldway.service.AssuranceServiceOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/personne/old")
public class PersonneOldController {

    @Autowired
    AssuranceServiceOld assuranceServiceOld;

    @PostMapping
    public ResponseEntity addPersonne(@RequestBody Personne personne){
        try {
            assuranceServiceOld.createPersonne(personne) ;
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Personne> getPersonne(@PathVariable int id) {
        try {
            Personne personne = assuranceServiceOld.getPersonneByID(id) ;
            if(personne != null) {
                return ResponseEntity.ok(personne);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
            }
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CREATED).build() ;
        }
    }

}
