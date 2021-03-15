package sample;

import java.sql.*;

public class Registration {

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
    //                 https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/

    public int register(String username, String email, String password) throws SQLException {
        ResultSet rs;
        boolean exists = false;


        String query1 = "SELECT * FROM Account WHERE Username =?";
        String query2 = "SELECT * FROM Account WHERE Email =?";
        String query3 = "INSERT INTO Account(Username, Email, Password) VALUES(?, ?, ?)";

        try
        {
            PreparedStatement checkUsername = Registration.getConnection().prepareStatement(query1);
            checkUsername.setString(1, username);

            rs = checkUsername.executeQuery();

            if (rs.next()) {
                System.out.println("Username used");
                exists = true;
                return -1;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error in checking username");
        }

        try
        {
            PreparedStatement checkEmail = Registration.getConnection().prepareStatement(query2);
            checkEmail.setString(1, email);

            rs = checkEmail.executeQuery();

            if (rs.next()) {
                System.out.println("Email used");
                exists = true;
                return -2;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error in checking email");
        }

        if (exists == false)
        {
            try(PreparedStatement insert = Registration.getConnection().prepareStatement(query3))
            {
                insert.setString(1, username);
                insert.setString(2, email);
                insert.setString(3, password);
                insert.execute();
                System.out.println("New user added into database");
                exists = true;
                return 1;
            }
            catch(SQLException e)
            {
                System.out.println("Error Adding User To Database: " + e);
            }
        }
        return 0;
    }

    public static int main(String username, String email, String password) throws SQLException {
        Registration registration = new Registration();

        //Scanner s = new Scanner(System.in);
        //System.out.println("Enter username: ");
        //String a = s.nextLine();
        //System.out.println("Enter email: ");
        //String b = s.nextLine();
        //System.out.println("Enter password: ");
        //String c = s.nextLine();
        //System.out.println("Confirm password");
        //String d = s.nextLine();

        //if (!(d.equals(c)))
        //{
        //    System.out.println("Password incorrect");
        //}

        //else
        //{
        return registration.register(username.toLowerCase(), email.toLowerCase(), password);
        //}

    }

}

