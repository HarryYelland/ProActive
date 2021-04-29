//package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Group {
    Boolean joinGroup(int UUID, int groupID){
        PreparedStatement ps;
        ResultSet rs;

        String query1 = "INSERT INTO GroupMembers (GroupID, UUID) VALUES (?, ?)";
        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, groupID);
            ps.setInt(2, UUID);
            rs = ps.executeQuery();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return false;
    }

    Boolean makeGroup(String groupName, String confirmName) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "INSERT INTO Groups (GroupName) VALUES (?)";
        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setString (1, groupName);
            ps.executeQuery();
            System.out.println("MADE GROUP");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    int getGroupID(String groupName) throws SQLException {
        int groupID = -1;
        PreparedStatement ps;
        ResultSet rs;
        String query2 = "SELECT GroupID FROM Groups WHERE GroupName LIKE ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query2);
        ps.setString (1, groupName);
        rs = ps.executeQuery();
        if (rs.next())
        {
            groupID = rs.getInt(1);
        }
        System.out.println("GOT GROUP ID");
        return groupID;
    }

    int getMemberUUID(String username) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        int uuid = -1;
        String query3 = "SELECT UUID FROM Account WHERE Username LIKE ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query3);
        ps.setString (1, username);
        rs = ps.executeQuery();
        if (rs.next())
        {
            uuid = rs.getInt(1);
            System.out.println("GOT USER ID");
        }
        return uuid;
    }

    Boolean insertGroupMember(int groupID, int uuid) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String query4 = "INSERT INTO GroupMembers (GroupID, UUID) VALUES (?, ?)";
        ps = DatabaseConnector.getConnection().prepareStatement(query4);
        ps.setInt (1, groupID);
        ps.setInt(2, uuid);
        ps.executeQuery();
        System.out.println("INSERTED GROUP MEMBER");
        return true;
    }

}
