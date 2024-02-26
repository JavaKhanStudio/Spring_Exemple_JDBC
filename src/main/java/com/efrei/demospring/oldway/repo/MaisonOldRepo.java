package com.efrei.demospring.oldway.repo;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.oldway.mapper.MaisonOldRowMapper;
import com.efrei.demospring.oldway.mapper.PersonneOldRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MaisonOldRepo {

    String insertMaison = "INSERT INTO maison (nomRue, numRue) VALUES (?, ?)";
    // Ok mais ne retourne pas les habitants
    // String getMaisonByID = "SELECT * FROM maison WHERE id = ?";
    String getMaisonByID = "SELECT * FROM maison LEFT JOIN personne ON personne.maison_id = maison.id WHERE maison.id = ?";

    String setMaisonForPersonne = "UPDATE Personne SET maison_id = ? WHERE id = ?" ;

    public Maison createHouse(Maison maisonOld) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();

            pstmt = conn.prepareStatement(insertMaison, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, maisonOld.getNomRue());
            pstmt.setInt(2, maisonOld.getNumRue());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating house failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maisonOld;
    }

    public Maison getHouseById(int id) {
        Maison maisonOld = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();


            pstmt = conn.prepareStatement(getMaisonByID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            MaisonOldRowMapper mapper = new MaisonOldRowMapper();

            DatabaseUtil.printRaw(rs);

            rs.next();
            maisonOld = mapper.mapRow(rs, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maisonOld;
    }


    public void setMaisonDePersonne(int newMaisonId, int personneId) {

        try (Connection conn = DatabaseUtil.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(setMaisonForPersonne)) {

            pstmt.setInt(1, newMaisonId);
            pstmt.setInt(2, personneId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed: Personne not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
