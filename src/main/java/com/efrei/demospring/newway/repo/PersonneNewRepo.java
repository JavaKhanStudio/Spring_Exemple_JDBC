package com.efrei.demospring.newway.repo;

import com.efrei.demospring.entity.Personne;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PersonneNewRepo {

    private static final String addPersonne = "INSERT INTO Personne (nom, genre, age) VALUES (?, ?, ?)" ;

    private final JdbcClient jdbcClient;

    public PersonneNewRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Personne createPersonne(Personne personne) {
        int generatedId = jdbcClient.sql(addPersonne)
                .param(1, personne.getNom())
                .param(2, personne.getGenre().name())
                .param(3, personne.getAge())
                .update()
               ;
        return personne;
    }

    public Personne getPersonneByID(int id) {
        return jdbcClient.sql("SELECT * FROM Personne WHERE id = ?")
                .param(1, id)
                .query(Personne.class)
                .single();
    }

}