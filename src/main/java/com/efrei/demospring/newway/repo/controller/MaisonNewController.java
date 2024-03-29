package com.efrei.demospring.newway.repo.controller;

import com.efrei.demospring.dto.ResponseMessage;
import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.newway.service.AssuranceServiceNew;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/maison/new")
public class MaisonNewController {

    private final AssuranceServiceNew assuranceServiceNew;

    public MaisonNewController(AssuranceServiceNew assuranceServiceNew) {
        this.assuranceServiceNew = assuranceServiceNew;
    }

    @PostMapping
    public ResponseEntity addMaison(@RequestBody Maison maison){
        try {
            Number id = assuranceServiceNew.createHouse(maison) ;
            // You would not the send ID to the client, this is for education.
            ResponseMessage responseBody = new ResponseMessage("The ID of the inserted Object is : " + id);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping(path = "/{maisonId}")
    public ResponseEntity<Maison> getMaison(@PathVariable int maisonId){

        Maison maison = assuranceServiceNew.getHouseById(maisonId) ;
        if(maison != null) {
            return ResponseEntity.ok(maison);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
        }
    }

    @PostMapping(path = "/{maisonId}/{personneID}")
    public ResponseEntity setHouseForPersonne(@PathVariable int maisonId, @PathVariable int personneID) {
        try {
            String reponse = assuranceServiceNew.addPersonToHouse(maisonId, personneID) ;
            if(reponse != null) {
                return ResponseEntity.ok(reponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
            }
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CREATED).build() ;
        }
    }

}
