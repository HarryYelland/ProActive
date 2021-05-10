//package com.company;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;

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

    public boolean addGroup(String groupName, String type)
    {
        DatabaseConnector connector = new DatabaseConnector();
        ResultSet rs;
        boolean exists = false;

        int groupType = 0;
        if(type=="Exercise"){
            groupType = 1;
        } else if (type == "Custom"){
            groupType = 2;
        }

        String query1 = "SELECT * FROM Groups WHERE GroupName LIKE ?";
        String query2 = "INSERT INTO Groups(GroupName, GroupType, JoinCode) VALUES(?, ?, ?)";
        try
        {
            PreparedStatement checkFood = connector.getConnection().prepareStatement(query1);
            checkFood.setString(1, groupName);

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
                insert.setString(1, groupName);
                insert.setInt(2, groupType);
                insert.setString(3, this.createJoinCode());
                insert.execute();
                System.out.println("New Group added into database");
                exists = true;
            }

            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
        return exists;
    }


    int getGroupID(String groupName) throws SQLException {
        int groupID = -1;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT GroupID FROM Groups WHERE GroupName LIKE ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query);
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
            } else {
                System.out.println("No Group By That ID");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return groupName;
    }

    String createJoinCode(){
        String code = "";
        for(int i=0; i<5; i++){
            String random = Character.toString ((char) (Math.random() * 122 + 49));
            code += random;
        }
        return code;
    }

    public String getGroupCode(int id){
        PreparedStatement ps;
        ResultSet rs;
        String groupCode = "";

        String query1 = "SELECT JoinCode FROM Groups WHERE GroupID = ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                groupCode = rs.getString(1);
                return groupCode;
            } else {
                System.out.println("No Group By That ID");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return groupCode;
    }

    public Integer getIdFromCode(String code){
        PreparedStatement ps;
        ResultSet rs;
        int id = -1;

        String query1 = "SELECT GroupID FROM Groups WHERE JoinCode LIKE ?";

        try
        {
            ps = DatabaseConnector.getConnection().prepareStatement(query1);
            ps.setString(1, code);
            rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("Successful login!");
                id = rs.getInt(1);
                return id;
            } else {
                System.out.println("No Group By That code");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return id;
    }

    boolean checkUserExist(String email) throws SQLException {
        boolean UserExist = false;
        String query2 = "SELECT * FROM account WHERE email =?";
        PreparedStatement checkEmail = DatabaseConnector.getConnection().prepareStatement(query2);
        checkEmail.setString(1, email);
        ResultSet result = checkEmail.executeQuery();

        while (result.next()) {
            String dataBaseEmail = result.getString("email");
            if ((dataBaseEmail.contentEquals(email))) {
                System.out.println("User exist");
                UserExist = true;
            }

        }
        return UserExist;
    }


    public static ArrayList<String> showGroups(int uuid) throws  SQLException{
        String query2 = "SELECT groups.groupname FROM groups INNER JOIN groupmembers ON groups.groupid = groupmembers.groupid WHERE groupmembers.uuid =? ";
        PreparedStatement userGroups = DatabaseConnector.getConnection().prepareStatement(query2);
        userGroups.setInt(1,uuid);
        ResultSet resultSet = userGroups.executeQuery();
        ArrayList<String> groupList = new ArrayList<>();

        while (resultSet.next()){
            String getGroupName = resultSet.getString("groupname");
            groupList.add(getGroupName);
        }
        return groupList;

    }
}
