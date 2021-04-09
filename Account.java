

import java.sql.*;
import java.util.Scanner;

public class Account {

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




    public String getUsername(int id)
    {
        PreparedStatement ps;
        ResultSet rs;

        String query1 = "SELECT Username FROM Account WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                String username = rs.getString(1);;
                username = username.substring(0,1).toUpperCase() + username.substring(1).toLowerCase();
                return username;        //Returns ID of user
            }

            else
            {
                System.out.println("Error - No Current User Logged ");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return null;                                      //Returns error ID
    }

    public boolean setPassword(int id, String oldPassword, String password, String confirmPassword) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;
        String username, email;
        Boolean correctPass = false;

        String query1 = "SELECT Password From Account WHERE UUID = ?";
        String query2 = "UPDATE Account SET Password = ? WHERE UUID = ?";



        try {
            ps = Login.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (password.equals(confirmPassword) && !(password.equals(oldPassword))){
                    correctPass = true;
                }
                else{
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try
        {
            if (correctPass == true) {
                PreparedStatement setPassword = Registration.getConnection().prepareStatement(query2);
                ps = Account.getConnection().prepareStatement(query2);
                ps.setString(1, password);
                ps.setInt(2, id);
                rs = ps.executeQuery();
                rs = setPassword.executeQuery();
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error in Setting Password");
        }
        return false;
    }


    public static void main(String username, String password){
        Account account = new Account();
    }
}
