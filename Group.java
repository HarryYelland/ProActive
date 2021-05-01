package com.company;

import javax.xml.crypto.Data;
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
        Group group = new Group();
        if(groupName != confirmName){
            return false;
        }
        if(group.getGroupID(groupName) >= 0) {
            System.out.println("Group Name Already Taken");
            return false;
        }
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "INSERT INTO Groups (GroupName) VALUES (?)";
        try
        {
            PreparedStatement insertGroup = DatabaseConnector.getConnection().prepareStatement(query1);
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setString (1, groupName);
            rs = ps.executeQuery();
            rs = insertGroup.executeQuery();
            System.out.println("MADE GROUP");
            return true;
        } catch (SQLException throwables) {
        }
        return false;
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
        String query1 = "INSERT INTO GroupMembers (GroupID, UUID) VALUES (?, ?)";
        try {
            PreparedStatement insertMember = DatabaseConnector.getConnection().prepareStatement(query1);
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, groupID);
            ps.setInt(2, uuid);
            rs = ps.executeQuery();
            rs = insertMember.executeQuery();
            System.out.println("INSERTED GROUP MEMBER");
            return true;
        } catch (SQLException e){
        }
            return false;
    }

    public String getGroupName(int id){
        PreparedStatement ps;
        ResultSet rs;
        String groupName = "";

        String query1 = "SELECT GroupName FROM Groups WHERE GroupID = ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                groupName = rs.getString(1);
                return groupName;
            }

            else
            {
                System.out.println("No Group By That ID");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return groupName;
    }
}
