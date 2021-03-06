//package com.company;

import java.sql.*;
import java.util.ArrayList;
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

    public int getWeight(int id){
        PreparedStatement ps;
        ResultSet rs;
        int weight = 0;

        String query1 = "SELECT Weight FROM Details WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                weight = rs.getInt(1);
                return weight;        //caloriegoal
            }

            else
            {
                System.out.println("No Current Weight Set - Setting To 0");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return weight;
    }

    public String getEmail(int id){
        PreparedStatement ps;
        ResultSet rs;
        String email = "No Email Found";

        String query1 = "SELECT Email FROM Account WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                email = rs.getString(1);
                return email;        //caloriegoal
            }

            else
            {
                System.out.println("No Current Email Set");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return email;
    }

    public int getHeight(int id){
        PreparedStatement ps;
        ResultSet rs;
        int height = 0;

        String query1 = "SELECT Height FROM Details WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                height = rs.getInt(1);
                return height;        //caloriegoal
            }

            else
            {
                System.out.println("No Current Height Set - Setting To 0");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return height;
    }

    public int getCalorieGoal(int id){
        PreparedStatement ps;
        ResultSet rs;
        int calories = 0;

        String query1 = "SELECT CalorieGoal FROM Details WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                calories = rs.getInt(1);
                return calories;        //caloriegoal
            }

            else
            {
                System.out.println("No Current Calorie Goal - Setting To 0");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return calories;
    }

    public String getCustomGoal(int id){
        PreparedStatement ps;
        ResultSet rs;
        String goal = "";

        String query1 = "SELECT CustomGoal FROM Details WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                goal = rs.getString(1);
                return goal;        //goal
            }

            else
            {
                System.out.println("No Current Calorie Goal - Setting To 0");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return goal;
    }

    public Boolean getCustomMet(int id){
        PreparedStatement ps;
        ResultSet rs;
        Boolean goal = false;

        String query1 = "SELECT CustomMet FROM Details WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                goal = rs.getBoolean(1);
                return goal;        //goal
            }

            else
            {
                System.out.println("No Current Goal Check - Setting To False");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return goal;
    }

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


    public boolean setEmail(int id, String newEmail) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;

        String query1 = "UPDATE Account SET Email = ? WHERE UUID = ?";

        try
        {
                PreparedStatement setEmail = Registration.getConnection().prepareStatement(query1);
                ps = Account.getConnection().prepareStatement(query1);
                ps.setString(1, newEmail);
                ps.setInt(2, id);
                rs = ps.executeQuery();
                rs = setEmail.executeQuery();
                return true;
        }
        catch(SQLException e)
        {
        }
        return false;
    }


    public boolean setFirstDetails(int id) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;

        String query1 = "INSERT INTO Details (UUID, CalorieGoal, Weight, Height, CustomGoal, CustomMet) VALUES (?, 0, 0, 0, ?, ?)";
        try
        {
            PreparedStatement setDetails = Registration.getConnection().prepareStatement(query1);
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            ps.setString(2, "");
            ps.setBoolean(3, false);
            rs = ps.executeQuery();
            rs = setDetails.executeQuery();
            return true;
        }
        catch(SQLException e)
        {
        }
        return false;
    }

    public boolean setDetails(int id, int calorieGoal, int weight, int height, String customGoal, Boolean customMet) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;

        String query1 = "UPDATE Details SET CalorieGoal = ?, Weight = ?, Height = ?, CustomGoal = ?, CustomMet = ? WHERE UUID = ?";

        try
        {
            PreparedStatement setCalorieGoal = Registration.getConnection().prepareStatement(query1);
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, calorieGoal);
            ps.setInt(2, weight);
            ps.setInt(3, height);
            ps.setString(4, customGoal);
            ps.setBoolean(5, customMet);
            ps.setInt(6, id);
            rs = ps.executeQuery();
            rs = setCalorieGoal.executeQuery();
            return true;
        }
        catch(SQLException e)
        {
        }
        return false;
    }

    public Goal getGoal(int id) {
        Goal goals = new Goal();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT * FROM DETAILS WHERE UUID = ?";

        try {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                goals.setGoalName(rs.getString(6));
                goals.setCalorieGoal(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return goals;
    }

    public SingleExercise[] getExercises(int id) {
        ArrayList<SingleExercise> exercises = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT * FROM EXERCISECOMP JOIN EXERCISE ON EXERCISE.EXERCISEID = EXERCISECOMP.EXCOMPID WHERE UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                SingleExercise se = new SingleExercise();
                se.setCalories(rs.getInt(6));
                se.setDate(rs.getDate(4).toString());
                se.setName(rs.getString(7));
                se.setReps(rs.getInt(8));
                exercises.add(se);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return exercises.toArray(new SingleExercise[0]);
    }

    public Achievement[] checkAchievements(int id) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT * FROM CONSUMABLE INNER JOIN CONSUMABLECOMP ON CONSUMABLECOMP.CONSUMABLEID = CONSUMABLE.CONSUMABLEID AND CONSUMABLECOMP.UUID = ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Goal g = getGoal(id);

            int totalCalories = 0;
            while (rs.next())
            {
                int calories = rs.getInt(2);
                totalCalories += calories;

                if (g.getCalorieGoal() <= totalCalories) {
                    Achievement achievement = new Achievement();
                    achievement.setAchievement("Achieved: " + g.getGoalName());
                    achievement.setDate(rs.getDate(7).toString());
                    achievement.setPoints(1);
                    achievements.add(achievement);
                }
            }

            SingleExercise exercises[] = getExercises(id);
            for (SingleExercise se : exercises) {
                Achievement achievement = new Achievement();
                achievement.setAchievement("Burn " + se.getCalories() + " calories with " + se.getReps() + " reps of " + se.getName());
                achievement.setDate(se.getDate());
                achievement.setPoints(1);
                achievements.add(achievement);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return achievements.toArray(new Achievement[0]);
    }

    public int getIDFromEmail(String email){
        PreparedStatement ps;
        ResultSet rs;
        int id = -1;

        String query1 = "SELECT uuid FROM Account WHERE email LIKE ?";

        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                id = rs.getInt(1);
                return id;        //caloriegoal
            }

            else
            {
                System.out.println("No Current Email Set");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return id;
    }

    public boolean resetPassword(int id, String password) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;
        Boolean correctPass = true;

        String query1 = "UPDATE Account SET Password = ? WHERE UUID = ?";
        try
        {
            if (correctPass == true) {
                PreparedStatement setPassword = Registration.getConnection().prepareStatement(query1);
                ps = Account.getConnection().prepareStatement(query1);
                ps.setString(1, password);
                ps.setInt(2, id);
                rs = ps.executeQuery();
                rs = setPassword.executeQuery();
                return true;
            } else {
                return false;
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
