package com.efrei.demospring.newway.service;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.newway.repo.MaisonNewRepo;
import com.efrei.demospring.newway.repo.PersonneNewRepo;
import com.efrei.demospring.oldway.repo.MaisonOldRepo;
import com.efrei.demospring.oldway.repo.PersonneOldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AssuranceServiceNew {

    @Autowired
    PersonneNewRepo personneNewRepo;

    @Autowired
    MaisonNewRepo maisonNewRepo;

    public Maison createHouse(Maison maisonOld) {
        return maisonNewRepo.createHouse(maisonOld);
    }

    public Maison getHouseById(int id) {
        return maisonNewRepo.getHouseById(id);
    }

    public String addPersonToHouse(int maisonID, int personneID) throws SQLException {

        maisonNewRepo.setMaisonDePersonne(maisonID, personneID) ;

        return "OK";
    }

    public Personne createPersonne(Personne personneOld) throws SQLException {
        return personneNewRepo.createPersonne(personneOld);
    }

    public Personne getPersonneByID(int id) throws SQLException {
        return personneNewRepo.getPersonneByID(id);
    }

}
