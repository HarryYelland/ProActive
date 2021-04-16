package com.company;

import java.sql.*;
import java.util.Scanner;

    public class Exercise {

        public boolean addExercise(String exercise, int calorieBurntPerHour)
        {
            DatabaseConnector connector = new DatabaseConnector();
            ResultSet rs;
            boolean exists = false;

            String query1 = "SELECT * FROM exercise WHERE exercise =?";
            String query2 = "INSERT INTO exercise(exercise, calories_burn_per_hour) VALUES(?, ?)";

            try
            {
                PreparedStatement checkFood = connector.getConnection().prepareStatement(query1);
                checkFood.setString(1, exercise);

                rs = checkFood.executeQuery();

                if (rs.next()) {
                    System.out.println("Exercise already exists");
                    exists = true;
                }
            }
            catch(SQLException e)
            {
                System.out.println("Error in checking exercise type");
            }

            while (exists == false)
            {
                try(PreparedStatement insert = connector.getConnection().prepareStatement(query2))
                {
                    insert.setString(1, exercise);
                    insert.setInt(2, calorieBurntPerHour);
                    insert.execute();
                    System.out.println("New exercise added into database");
                    exists = true;
                }

                catch(SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            return exists;
        }

        public static void main(String[] args) throws SQLException {
            Exercise addExercise = new Exercise();

            Scanner s = new Scanner(System.in);
            System.out.println("Enter exercise type: ");
            String a = s.nextLine();
            System.out.println("Enter calorie burn per hour: ");
            int b = Integer.parseInt(s.nextLine());

            addExercise.addExercise(a,b);

        }
    }
