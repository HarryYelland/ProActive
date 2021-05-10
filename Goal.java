public class Goal {
    public String goalName;
    public Integer calorieGoal;

    public Goal() {
        this.goalName = "";
        this.calorieGoal = 0;
    }

    public Goal(String goalName, Integer calorieGoal) {
        this.goalName = goalName;
        this.calorieGoal = calorieGoal;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Integer getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(Integer calorieGoal) {
        this.calorieGoal = calorieGoal;
    }
}