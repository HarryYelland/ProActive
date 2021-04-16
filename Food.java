package com.company;

import java.sql.*;
import java.util.Scanner;

public class Food {

        public boolean addFood(String food, int caloriePerHunded)
        {
            DatabaseConnector connector = new DatabaseConnector();
            ResultSet rs;
            boolean exists = false;

            String query1 = "SELECT * FROM diet WHERE food =?";
            String query2 = "INSERT INTO diet(food, calories) VALUES(?, ?)";

            try
            {
                PreparedStatement checkFood = connector.getConnection().prepareStatement(query1);
                checkFood.setString(1, food);

                rs = checkFood.executeQuery();

                if (rs.next()) {
                    System.out.println("Food already exists");
                    exists = true;
                }
            }
            catch(SQLException e)
            {
                System.out.println("Error in checking food type");
            }

            while (exists == false)
            {
                try(PreparedStatement insert = connector.getConnection().prepareStatement(query2))
                {
                    insert.setString(1, food);
                    insert.setInt(2, caloriePerHunded);
                    insert.execute();
                    System.out.println("New food added into database");
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
            Food addFood = new Food();

            Scanner s = new Scanner(System.in);
            System.out.println("Enter food type: ");
            String a = s.nextLine();
            System.out.println("Enter calorie per packet: ");
            int b = Integer.parseInt(s.nextLine());

            addFood.addFood(a, b);

        }

}
