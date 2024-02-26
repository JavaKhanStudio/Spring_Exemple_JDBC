package com.efrei.demospring.oldway.controller;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.oldway.service.AssuranceServiceOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/maison/old")
public class MaisonOldController {

    @Autowired
    AssuranceServiceOld assuranceServiceOld;

    @PostMapping
    public ResponseEntity addMaison(@RequestBody Maison maison){
        try {
            assuranceServiceOld.createHouse(maison) ;
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<Maison> getMaison(@PathVariable int maisonId){

        Maison maison = assuranceServiceOld.getHouseById(maisonId) ;
        if(maison != null) {
            return ResponseEntity.ok(maison);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
        }
    }

    @GetMapping(path = "/{maisonId}/{personneID}")
    public ResponseEntity<Personne> getPersonne(@PathVariable int maisonId, @PathVariable int personneID) {
        try {
            Personne personne = assuranceServiceOld.addPersonToHouse(maisonId, personneID) ;
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
