package com.efrei.demospring.oldway.service;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.oldway.repo.MaisonOldRepo;
import com.efrei.demospring.oldway.repo.PersonneOldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssuranceServiceImpl implements AssuranceService{

    @Autowired
    PersonneOldRepo personneOldRepo ;

    @Autowired

    MaisonOldRepo maisonOldRepo;

    @Override
    public Maison createHouse(Maison maisonOld) {
        return maisonOldRepo.createHouse(maisonOld);
    }

    @Override
    public Maison getHouseById(Long id) {
        return maisonOldRepo.getHouseById(id);
    }

    @Override
    public Personne createPersonne(Personne personneOld) {
        return personneOldRepo.createPersonne(personneOld);
    }

    @Override
    public Personne getPersonneByID(Long id) {
        return personneOldRepo.getPersonneByID(id);
    }
}
