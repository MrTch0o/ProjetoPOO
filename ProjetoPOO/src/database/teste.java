/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author DEVENG
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try (Connection conn = DbProperties.getConnection(); Statement st = conn.createStatement();) {
            int rowAffected = st.executeUpdate("UPDATE `projetopoo`.`pessoa` SET `Nome` = 'pedro2' WHERE (`ID` = '2');");
            System.out.println("linhas afetadas" + rowAffected);
            try (ResultSet rs = st.executeQuery("select * from pessoa");) {
                int i = rs.getFetchDirection();
                System.out.println(i);
                System.out.println(rs.absolute(3));
                rs.beforeFirst();
                while (rs.next()) {
                    
                    System.out.println(rs.getInt("id") + ", " + rs.getString("nome") + ", " + rs.getString("telefone"));
                    
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
