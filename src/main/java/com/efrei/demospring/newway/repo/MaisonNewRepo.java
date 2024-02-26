package com.efrei.demospring.newway.repo;

import com.efrei.demospring.entity.Maison;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MaisonNewRepo {

    private final static String addMaison = "INSERT INTO Maison (nomRue, numRue) VALUES (?, ?)";
    private final static String selectMaison = "SELECT * FROM Maison WHERE id = ?" ;

    private final static String addPersonToMaison = "UPDATE Personne SET maison_id = ? WHERE id = ?" ;
    private final JdbcClient jdbcClient;

    public MaisonNewRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public Maison createHouse(Maison maison) {
        long generatedId = jdbcClient.sql(addMaison)
                .param(1, maison.getNomRue())
                .param(2, maison.getNumRue())
                .update() ;

        return maison;
    }

    public Maison getHouseById(int id) {
        return jdbcClient.sql(selectMaison)
                .param(1, id)
                .query(Maison.class)
                .single() ;
    }

    public String setMaisonDePersonne(int maisonID, int personneID) {
        int affected = jdbcClient.sql(addPersonToMaison)
                .param(1, maisonID)
                .param(2, personneID)
                .update();

        return affected > 0 ? "Success" : "Failure";
    }


}
