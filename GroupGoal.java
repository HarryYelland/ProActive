import java.sql.Date;

public class GroupGoal {
    public String goalName;
    public String username;
    public Date date;

    public GroupGoal() {
        this.goalName = "";
        this.username = "";
    }

    public GroupGoal(String goalName, String username, Date date) {
        this.goalName = goalName;
        this.username = username;
        this.date = date;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
