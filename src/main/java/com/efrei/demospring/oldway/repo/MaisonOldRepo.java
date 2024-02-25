package com.efrei.demospring.oldway.repo;

import com.efrei.demospring.entity.Maison;
import com.efrei.demospring.oldway.mapper.MaisonOldRowMapper;
import com.efrei.demospring.oldway.mapper.PersonneOldRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MaisonOldRepo {

    String insertMaison = "INSERT INTO maisonOld (nomRue, numRue) VALUES (?, ?)";

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
            MaisonOldRowMapper mapper = new MaisonOldRowMapper();
            rs.next();
            maisonOld = mapper.mapRow(rs, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maisonOld;
    }

}
