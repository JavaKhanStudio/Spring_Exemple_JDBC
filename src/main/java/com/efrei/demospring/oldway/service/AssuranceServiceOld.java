package com.efrei.demospring.oldway.service;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;

import java.sql.SQLException;


public interface AssuranceServiceOld {

    Maison createHouse(Maison MaisonOld) ;
    Maison getHouseById(int id) ;

    String addPersonToHouse(int maisonID, int personneID) throws SQLException ;

    Personne createPersonne(Personne MaisonOld) throws SQLException;
    Personne getPersonneByID(int id) throws SQLException;
}
