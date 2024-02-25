package com.efrei.demospring.oldway.repo;

import com.efrei.demospring.entity.Personne;
import com.efrei.demospring.oldway.PersonneOldRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonneOldRepo {

    private static final String insertPerson = "INSERT INTO personne (nom,genre,age) VALUES (?, ?, ?)" ;

    private static final String findPersonByName = "SELECT * FROM personneOld WHERE nom = ? ORDER BY age DESC";

    private static final String findPersonbyID = "SELECT * FROM personne WHERE id = ?";

    private static final String findPersonByAgeRange = "SELECT * FROM personneOld WHERE age <= ? AND age >= ?";

    @Autowired
    private JdbcClient jdbcClient;


    public Personne createPersonne(Personne personneOld) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();

            pstmt = conn.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);

            // Set parameters for pstmt based on PersonneOld properties
            // pstmt.setString(1, personneOld.getProperty());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personneOld;
    }

    public Personne getPersonneByID(Long id) {
        Personne personneOld = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();

            pstmt = conn.prepareStatement(findPersonbyID);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Map ResultSet to PersonneOld, assuming you have a constructor or setters to set properties
                personneOld = new Personne(/* Map properties from ResultSet */);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
