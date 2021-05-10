public class CustomGoal {
    public String goal;
    public String username;
    public boolean completed;

    public CustomGoal() {
        this.goal = "";
        this.username = "";
        this.completed = false;
    }

    public CustomGoal(String goal, String username, boolean completed) {
        this.goal = goal;
        this.username = username;
        this.completed = completed;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getCompleted(){
        return this.completed;
    }

    public void setCompleted(Boolean completed){
        this.completed = completed;
    }
}
