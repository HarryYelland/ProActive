package com.company;

import java.sql.*;
import java.util.Scanner;

public class Login {

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

    //References from: https://1bestcsharp.blogspot.com/2018/05/java-login-and-register-form-with-mysql-database.html
    //                 https://stackoverflow.com/questions/42454582/check-if-value-accountnumber-exist-in-a-java-database
    //                 https://stackoverflow.com/questions/16099382/java-mysql-check-if-value-exists-in-database
    public int getUser(String username, String password)
    {
        PreparedStatement ps;
        ResultSet rs;

        String query1 = "SELECT * FROM Account WHERE Username =? AND Password =?";

        try
        {
            ps = Login.getConnection().prepareStatement(query1);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                return rs.getInt(1);        //Returns ID of user
            }

            else
            {
                System.out.println("Wrong Username or Password");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return -1;                                      //Returns error ID
    }

    public static int main(String username, String password){
        Login login = new Login();
        return(login.getUser(username.toLowerCase(), password));
    }
}
