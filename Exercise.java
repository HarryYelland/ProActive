package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

    public class Exercise {

        public boolean addExercise(String exercise, int calorieBurntPerHour)
        {
            DatabaseConnector connector = new DatabaseConnector();
            ResultSet rs;
            boolean exists = false;

            String query1 = "SELECT * FROM exercise WHERE name LIKE ?";
            String query2 = "INSERT INTO exercise(name, calories) VALUES(?, ?)";

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

        public boolean addLog(int UUID, int exerciseID, Date Date)
        {
            DatabaseConnector connector = new DatabaseConnector();
            ResultSet rs;

            String query1 = "INSERT INTO ExerciseComp(UUID, ExerciseID, DATE) VALUES(?, ?, ?)";
            try(PreparedStatement insert = connector.getConnection().prepareStatement(query1))
            {
                insert.setInt(1, UUID);
                insert.setInt(2, exerciseID);
                insert.setDate(3, Date);
                insert.execute();
                System.out.println("New Exercise added into database");
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
            return true;
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

        public int getExerciseID(String Name){
            PreparedStatement ps;
            ResultSet rs;
            int exerciseID = -1;

            String query1 = "SELECT ExerciseID FROM Exercise WHERE Name LIKE ?";

            try
            {
                ps = DatabaseConnector.getConnection().prepareStatement(query1);
                ps.setString(1, Name);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    System.out.println("Exercise Found");
                    exerciseID = rs.getInt(1);
                    return exerciseID;
                } else {
                    exerciseID = -1;
                    System.out.println("No Exercise By That ID");
                }
            }
            catch(SQLException e) {
            }
            return exerciseID;
        }

        public String getExerciseName(int ID){
            PreparedStatement ps;
            ResultSet rs;
            String exerciseName  = "";

            String query1 = "SELECT Name FROM Exercise WHERE ExerciseID = ?";

            try
            {
                ps = DatabaseConnector.getConnection().prepareStatement(query1);
                ps.setInt(1, ID);
                rs = ps.executeQuery();

                if (rs.next())
                {
                    System.out.println("Exercise Found");
                    exerciseName = rs.getString(1);
                    return exerciseName;
                } else {
                    System.out.println("No Exercise By That ID");
                }
            }
            catch(SQLException e) {
            }
            return exerciseName;
        }

        public ObservableList getExerciseLog(Date date){
            PreparedStatement ps;
            ResultSet rs;
            String query1 = "SELECT * FROM ExerciseComp WHERE CAST(date AS DATE) = ?";
            ObservableList data = FXCollections.observableArrayList();

            try
            {
                ps = DatabaseConnector.getConnection().prepareStatement(query1);
                ps.setDate(1, date);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                        //Iterate Column
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return data;
        }




        public ArrayList<Integer> getAllExercises(){
            PreparedStatement ps;
            ResultSet rs;
            ArrayList<Integer> allExercises = new ArrayList<>();

            String query1 = "SELECT ExerciseID FROM Exercise";

            try
            {
                ps = DatabaseConnector.getConnection().prepareStatement(query1);
                rs = ps.executeQuery();

                while (rs.next())
                {
                    System.out.println("Exercise Found");
                    allExercises.add(rs.getInt(1));
                }
            }
            catch(SQLException e) {
            }
            return allExercises;
        }

    }
