package com.efrei.demospring.oldway.service;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import org.springframework.stereotype.Service;

import java.sql.SQLException;


public interface AssuranceService {

    Maison createHouse(Maison MaisonOld) ;
    Maison getHouseById(Long id) ;

    Personne addPersonToHouse(Long maisonID, Long personneID) throws SQLException ;

    Personne createPersonne(Personne MaisonOld) throws SQLException;
    Personne getPersonneByID(Long id) throws SQLException;
}
