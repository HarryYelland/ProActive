//package com.company;

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
        System.out.println("Method running..");
        Group group = new Group();
        if(!groupName.contentEquals(confirmName)){
            System.out.println("Group Name != confirmName");
            return false;
        }
        if(group.getGroupID(groupName) >= 0) {
            System.out.println("Group Name Already Taken");
            return false;
        }
        PreparedStatement ps;
       // ResultSet rs;
        //String query1 = "INSERT INTO groups(groupname) VALUES(?)";

        System.out.println("Method still running..");
        try
        {
            String query1 = "INSERT INTO groups(groupname) VALUES(?)";
            PreparedStatement insertGroup = DatabaseConnector.getConnection().prepareStatement(query1);
           // ps = DatabaseConnector.getConnection().prepareStatement(query1);
           // ps.setString (1, groupName);
            //rs = ps.executeQuery();
            //rs = insertGroup.executeQuery();

            insertGroup.setString (1, groupName);
            insertGroup.executeUpdate();

            System.out.println("MADE GROUP");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    int getGroupID(String groupName) throws SQLException {
        int groupID = -1;
        PreparedStatement ps;
        ResultSet rs;
        String query2 = "SELECT groupid FROM groups WHERE groupname LIKE ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query2);
        ps.setString (1, groupName);
        rs = ps.executeQuery();
        while (rs.next())
        {
            groupID = rs.getInt(1);
            System.out.println(groupID);
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
        //ResultSet rs;
        String query1 = "INSERT INTO groupmembers (groupid, uuid) VALUES (?, ?)";
        try {
            PreparedStatement insertMember = DatabaseConnector.getConnection().prepareStatement(query1);
            //ps = DatabaseConnector.getConnection().prepareStatement(query1);
           // ps.setInt(1, groupID);
           // ps.setInt(2, uuid);
           // rs = ps.executeQuery();
           // rs = insertMember.executeQuery();
            insertMember.setInt(1, groupID);
            insertMember.setInt(2, uuid);
            insertMember.executeUpdate();
            System.out.println("INSERTED GROUP MEMBER");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
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
