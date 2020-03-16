// This is a class that connects to a database by using JDBC.

package com.CS401;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.math.BigDecimal;

public class DBConnect {

    public static void main (String [] args) {
        try {
            String host = "jdbc:mysql://localhost:3306/dbgame";
            String username = ""; // Your username on MySQL
            String password = ""; // Your password on MySQL
            Connection con = DriverManager.getConnection(host, username, password);

            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM games";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int game_id = rs.getInt("game_id");
                String title = rs.getString("title");
                String platform = rs.getString("platform");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String genre = rs.getString("genre");
                System.out.println("Game ID: " + game_id + "\nTitle: " + title + "\nPlatform: " + platform);
                System.out.println("Price: " + price + "\nQuantity: " + quantity + "\nGenre: " + genre + "\n");
            }
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
}
