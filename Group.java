//package com.company;

import javax.xml.crypto.Data;
import java.sql.*;
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
        for(int i=0; i<10; i++){
            String random = String.valueOf ((int) (Math.random() * 9 + 0));
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

    public GroupGoal[] checkCalorieGoal(ArrayList<Integer> ids, Date date) {
        ArrayList<GroupGoal> goals = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT * FROM CONSUMABLE INNER JOIN CONSUMABLECOMP ON CONSUMABLECOMP.CONSUMABLEID = CONSUMABLE.CONSUMABLEID AND CONSUMABLECOMP.UUID = ? WHERE Date = ?";
        for(int i=0; i < ids.size(); i++){
            try
            {
                ps = Account.getConnection().prepareStatement(query1);
                ps.setInt(1, ids.get(i));
                ps.setDate(2, date);
                rs = ps.executeQuery();
                int totalCalories = 0;
                Account account = new Account();
                while (rs.next())
                {
                    int calories = rs.getInt(2);
                    totalCalories += calories;

                    if (account.getCalorieGoal(ids.get(i)) >= totalCalories) {
                        GroupGoal goal = new GroupGoal();
                        goal.setGoalName("Calorie Goal Reached: " + totalCalories + " / " + account.getCalorieGoal(ids.get(i)));
                        goal.setUsername(account.getUsername(ids.get(i)));
                        goal.setDate(date);
                        goals.add(goal);
                    }
                }
            }
            catch(SQLException e)
            {
                System.out.println(e.getStackTrace());
            }
        }
        return goals.toArray(new GroupGoal[goals.size()]);
    }


    public GroupGoal[] checkExerciseGoal(Date date, ArrayList<Integer> ids){
        ArrayList<GroupGoal> goals = new ArrayList<>();
        Exercise exercise = new Exercise();
        Account account = new Account();

        for(int i=0; i<ids.size(); i++){
            SingleExercise exercises[] = exercise.checkExercises(ids.get(i), date);
            for (SingleExercise se : exercises) {
                GroupGoal groupGoal = new GroupGoal();
                groupGoal.setUsername(account.getUsername(ids.get(i)));
                groupGoal.setGoalName("Performed: " + se.getName() + " for " + se.getReps() + " reps");
                groupGoal.setDate(date);
                goals.add(groupGoal);
            }
        }
        return goals.toArray(new GroupGoal[goals.size()]);
    }

    public CustomGoal[] checkCustomGoal(ArrayList<Integer> ids){
        ArrayList<CustomGoal> goals = new ArrayList<>();
        CustomGoal custom = new CustomGoal();
        PreparedStatement ps;
        ResultSet rs;
        String query1 = "SELECT CustomGoal, CustomMet FROM Details WHERE uuid = ?";
        try
        {
            for(int i = 0; i<ids.size(); i++){
                ps = Account.getConnection().prepareStatement(query1);
                ps.setInt(1, ids.get(i));
                rs = ps.executeQuery();
                int totalCalories = 0;
                Account account = new Account();
                while (rs.next())
                {
                    custom.setGoal(rs.getString(1));
                    custom.setCompleted(rs.getBoolean(2));
                    custom.setUsername(account.getUsername(ids.get(i)));
                    goals.add(custom);
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getStackTrace());
        }
        return goals.toArray(new CustomGoal[goals.size()]);
    }

    int getGroupType(int groupID) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        int type = -1;
        String query = "SELECT GroupType FROM Groups WHERE GroupID = ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query);
        ps.setInt (1, groupID);
        rs = ps.executeQuery();
        if (rs.next())
        {
            type = rs.getInt(1);
        }
        System.out.println("GOT GROUP TYPE");
        return type;
    }

    ArrayList<Integer> getAllGroupMembers(int groupID) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Integer> ids = new ArrayList<>();
        String query = "SELECT uuid FROM groupmembers WHERE groupid = ?";
        ps = DatabaseConnector.getConnection().prepareStatement(query);
        ps.setInt (1, groupID);
        rs = ps.executeQuery();
        while (rs.next())
        {
            ids.add(rs.getInt(1));
        }
        System.out.println("GOT IDS IN GROUP");
        return ids;
    }

    boolean leaveGroup(int uuid, int groupid) throws SQLException {
        Boolean left = false;
        String query1 = "DELETE FROM GroupMembers WHERE uuid = ? AND groupId = ?";
        PreparedStatement leaveGroup = DatabaseConnector.getConnection().prepareStatement(query1);
        leaveGroup.setInt(1, uuid);
        leaveGroup.setInt(2, groupid);
        leaveGroup.execute();
        left = true;
        return left;
    }

    boolean deleteGroup(int groupid) throws SQLException {
        Boolean left = false;
        String query1 = "DELETE FROM Groups WHERE groupId = ?";
        PreparedStatement leaveGroup = DatabaseConnector.getConnection().prepareStatement(query1);
        leaveGroup.setInt(1, groupid);
        leaveGroup.execute();
        left = true;
        return left;
    }
}
