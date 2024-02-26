package com.efrei.demospring.oldway.repo;

import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.oldway.mapper.PersonneOldRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonneOldRepo {

    private static final String insertPerson = "INSERT INTO PERSONNE (nom,genre,age) VALUES (?, ?, ?)" ;

    private static final String findPersonByName = "SELECT * FROM PERSONNE WHERE nom = ? ORDER BY age DESC";

    private static final String findPersonbyID = "SELECT * FROM PERSONNE WHERE id = ?";

    private static final String findPersonByAgeRange = "SELECT * FROM PERSONNE WHERE age <= ? AND age >= ?";

    @Autowired
    private JdbcClient jdbcClient;


    public Personne createPersonne(Personne personneOld) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = DatabaseUtil.getDatabaseConnection();

        pstmt = conn.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);

        // Set parameters for pstmt based on PersonneOld properties
        pstmt.setString(1, personneOld.getNom());
        pstmt.setString(2, personneOld.getGenre().toString());
        pstmt.setInt(3, personneOld.getAge());

        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating person failed, no rows affected.");
        }

        return personneOld;
    }

    public Personne getPersonneByID(int id) throws SQLException {
        Personne personneOld = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        conn = DatabaseUtil.getDatabaseConnection();

        pstmt = conn.prepareStatement(findPersonbyID);
        pstmt.setLong(1, id);

        rs = pstmt.executeQuery();

        PersonneOldRowMapper mapper = new PersonneOldRowMapper();
        rs.next();
        personneOld = mapper.mapRow(rs, 0);

        return personneOld;
    }

    public List<Personne> findByAgeRange(int ageMin, int ageMax) {
        List<Personne> personnes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();

            String sql = findPersonByAgeRange;

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ageMin);
            pstmt.setInt(2, ageMax);

            rs = pstmt.executeQuery();

            PersonneOldRowMapper mapper = new PersonneOldRowMapper();
            int rowNum = 0;

            while (rs.next()) {
                Personne personne = mapper.mapRow(rs, rowNum++);
                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnes;
    }


}
