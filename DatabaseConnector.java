//package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //Reference from: https://stackoverflow.com/questions/56012948/intellij-problem-connecting-to-postgresql/56013789
    //                https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
    public static Connection getConnection() {
        String jdbc = "jdbc:postgresql://localhost:5432/ProActive";
        String user = "postgres";
        String pw = "computing";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbc, user, pw);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error in connecting to the database");
            e.printStackTrace();
        }
        return connection;
    }
}
