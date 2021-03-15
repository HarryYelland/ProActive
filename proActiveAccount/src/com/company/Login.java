package com.company;

import java.sql.*;
import java.util.Scanner;

public class Login {

    //Reference from: https://stackoverflow.com/questions/56012948/intellij-problem-connecting-to-postgresql/56013789
    //                https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
    public static Connection getConnection() {
        String jdbc = "jdbc:postgresql://localhost:5432/proActive";
        String user = "postgres";
        String pw = "password";

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

    //References from: https://1bestcsharp.blogspot.com/2018/05/java-login-and-register-form-with-mysql-database.html
    //                 https://stackoverflow.com/questions/42454582/check-if-value-accountnumber-exist-in-a-java-database
    //                 https://stackoverflow.com/questions/16099382/java-mysql-check-if-value-exists-in-database
    public void getUser(String username, String password)
    {
        PreparedStatement ps;
        ResultSet rs;

        String query1 = "SELECT * FROM appuser WHERE username =? AND password =?";

        try
        {
            ps = Login.getConnection().prepareStatement(query1);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successfully login!");
            }

            else
            {
                System.out.println("Wrong username or password");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    public static void main(String[] args){
        Login login = new Login();

        Scanner s = new Scanner(System.in);
        System.out.println("Enter username: ");
        String a = s.nextLine();
        System.out.println("Enter password: ");
        String b = s.nextLine();

        login.getUser(a, b);
    }
}
