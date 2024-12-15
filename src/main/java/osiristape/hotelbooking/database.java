package osiristape.hotelbooking;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
*/
import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectDb() {
        try {
            // Use the new MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection URL, username, and password
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hotelbooking", "root", "");

            System.out.println("Database connected successfully!");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static Connection connectionDb() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


// http://localhost:8080/phpmyadmin/