package com.efrei.demospring.oldway.mapper;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.enums.GenreEnum;
import com.efrei.demospring.oldway.repo.DatabaseUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaisonOldRowMapper implements RowMapper<Maison> {

    @Override
    public Maison mapRow(ResultSet rs, int rowNum) throws SQLException {
        Maison maison = new Maison() ;


        maison.setId(rs.getInt("maison.id"));
        maison.setNomRue(rs.getString("maison.nomRue"));
        maison.setNumRue(rs.getInt("maison.numRue"));

        List<Personne> occupants = new ArrayList<>();
        do  {
            Personne personne = new Personne();
            personne.setId(rs.getInt("personne.id")); // Assuming "personne_id" is the column name in the result set
            personne.setNom(rs.getString("personne.nom"));
            personne.setGenre(GenreEnum.valueOf(rs.getString("personne.genre")));
            personne.setAge(rs.getInt("personne.age"));
            personne.setMaisonId(rs.getInt("personne.maison_id"));

            occupants.add(personne);

            // Check if next row belongs to the same Maison
        } while (rs.next() && rs.getInt("maison.id") == maison.getId()) ;

        // Move cursor back by one because the next Maison's first Personne was read
        if (!rs.isAfterLast()) {
            rs.previous();
        }

        maison.setOccupants(occupants);


        return maison ;
    }
}