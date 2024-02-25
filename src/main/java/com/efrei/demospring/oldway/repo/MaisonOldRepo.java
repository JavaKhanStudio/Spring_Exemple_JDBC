package com.efrei.demospring.oldway.repo;

import com.efrei.demospring.entity.Maison;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MaisonOldRepo {

    public Maison createHouse(Maison maisonOld) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection(); // You need to implement this method to get a connection

            String sql = "INSERT INTO maisonOld (numRue, nomProprio) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, maisonOld.getNumRue());
            pstmt.setString(2, maisonOld.getNomProprio());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating house failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maisonOld;
    }

    public Maison getHouseById(Long id) {
        Maison maisonOld = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getDatabaseConnection();

            String sql = "SELECT * FROM maison WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Map ResultSet to MaisonOld, assuming you have a constructor or setters to set properties
                maisonOld = new Maison(/* Map properties from ResultSet */);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maisonOld;
    }

}
