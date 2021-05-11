//package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;

import javax.swing.text.TableView;
import java.sql.*;
import java.util.ArrayList;
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

    public int getConsumableID(String Name){
        PreparedStatement ps;
        ResultSet rs;
        int consumableID = -1;

        String query1 = "SELECT ConsumableID FROM Consumable WHERE Name LIKE ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setString(1, Name);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Consumable Found");
                consumableID = rs.getInt(1);
                return consumableID;
            } else {
                consumableID = -1;
                System.out.println("No Consumable By That ID");
            }
        }
        catch(SQLException e) {
        }
        return consumableID;
    }

    public ObservableList getFoodLog(Date date){
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT * FROM ConsumableComp WHERE CAST(date AS DATE) = ?";
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
    public ArrayList<Integer> getAllConsumables(){
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Integer> allFoods = new ArrayList<>();

        String query1 = "SELECT ConsumableID FROM Consumable";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            rs = ps.executeQuery();

            while (rs.next())
            {
                System.out.println("Consumable Found");
                allFoods.add(rs.getInt(1));
            }
        }
        catch(SQLException e) {
        }
        return allFoods;
    }

    public String getConsumableName(int ID){
        PreparedStatement ps;
        ResultSet rs;
        String consumableName  = "";

        String query1 = "SELECT Name FROM Consumable WHERE ConsumableID = ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, ID);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Consumable Found");
                consumableName = rs.getString(1);
                return consumableName;
            } else {
                System.out.println("No Consumable By That ID");
            }
        }
        catch(SQLException e) {
        }
        return consumableName;
    }



    public int getConsumableCalories(int ID){
        PreparedStatement ps;
        ResultSet rs;
        int calories = 0;

        String query1 = "SELECT Calories FROM Consumable WHERE ConsumableID = ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, ID);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Consumable Found");
                calories = rs.getInt(1);
                return calories;
            } else {
                System.out.println("No Consumable By That ID");
            }
        }
        catch(SQLException e) {
        }
        return calories;
    }

    public Consumable[] checkConsumables(int id, Date date) {
        ArrayList<Consumable> consumables = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT Name, Calories FROM CONSUMABLE INNER JOIN CONSUMABLECOMP ON CONSUMABLECOMP.CONSUMABLEID = CONSUMABLE.CONSUMABLEID WHERE CONSUMABLECOMP.UUID = ? AND CAST(date AS DATE) = ?";
        try
        {
            ps = Account.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consumable consumable = new Consumable();
                consumable.setName(rs.getString(1));
                consumable.setCalories(rs.getInt(2));
                consumables.add(consumable);
            }

        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return consumables.toArray(new Consumable[consumables.size()]);
    }
}
