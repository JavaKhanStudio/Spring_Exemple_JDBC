package com.efrei.demospring.oldway.mapper;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.enums.GenreEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaisonOldRowMapper implements RowMapper<Maison> {

    @Override
    public Maison mapRow(ResultSet rs, int rowNum) throws SQLException {
        Maison maison = new Maison() ;
        maison.setId(rs.getInt("id"));
        maison.setNomRue(rs.getString("nomRue"));
        maison.setNumRue(rs.getInt("numRue"));

        return maison ;
    }
}