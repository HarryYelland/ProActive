package com.company;

import java.sql.*;
import java.util.Scanner;

public class Food {

        public int addFood(String food, int caloriePerHunded)
        {
            DatabaseConnector connector = new DatabaseConnector();
            ResultSet rs;
            boolean exists = false;
            int id = 0;

            String query1 = "SELECT * FROM Consumable WHERE Name LIKE ?";
            String query2 = "INSERT INTO Consumable(Calories, Name) VALUES(?, ?)";
            String query3 = "SELECT ConsumableID FROM Consumable WHERE Name LIKE ?";

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
                    insert.setInt(1, caloriePerHunded);
                    insert.setString(2, food);
                    insert.execute();
                    System.out.println("New food added into database");
                    exists = true;
                }

                catch(SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }

            try
            {
                PreparedStatement getID = connector.getConnection().prepareStatement(query3);
                getID.setString(1, food);

                rs = getID.executeQuery();

                if (rs.next()) {
                    id = rs.findColumn(String.valueOf(1));
                }
            }
            catch(SQLException e)
            {
                System.out.println("Error in checking food type");
            }



            return id;
        }

    public boolean addLog(int UUID, int ConsumableID, Date Date)
    {
        DatabaseConnector connector = new DatabaseConnector();
        ResultSet rs;

        String query1 = "INSERT INTO ConsumableComp(UUID, ConsumableID, DATE) VALUES(?, ?, ?)";
        try(PreparedStatement insert = connector.getConnection().prepareStatement(query1))
        {
            insert.setInt(1, UUID);
            insert.setInt(2, ConsumableID);
            insert.setDate(3, Date);
            insert.execute();
            System.out.println("New food added into database");
        }
        catch(SQLException e)
        {
                System.out.println(e.getMessage());
        }
        return true;
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
