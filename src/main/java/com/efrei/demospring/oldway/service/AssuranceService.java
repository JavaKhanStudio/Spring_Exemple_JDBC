package com.efrei.demospring.oldway.service;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import org.springframework.stereotype.Service;


public interface AssuranceService {

    Maison createHouse(Maison MaisonOld) ;
    Maison getHouseById(Long id) ;

    Personne createPersonne(Personne MaisonOld) ;
    Personne getPersonneByID(Long id) ;
}
