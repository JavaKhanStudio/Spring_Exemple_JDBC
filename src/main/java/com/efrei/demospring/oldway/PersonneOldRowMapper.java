package com.efrei.demospring.oldway;

import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.enums.GenreEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonneOldRowMapper implements RowMapper<Personne> {

    @Override
    public Personne mapRow(ResultSet rs, int rowNum) throws SQLException {
        Personne personne = new Personne();
        personne.setId(rs.getInt("id"));
        personne.setNom(rs.getString("nom"));
        personne.setGenre(GenreEnum.valueOf(rs.getString("genre")));
        personne.setAge(rs.getInt("age"));
        return personne;
    }
}