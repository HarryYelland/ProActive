public class SingleExercise {
    public String name;
    public Integer calories;
    public String date;
    public Integer reps;

    public SingleExercise() {
        this.name = "";
        this.calories = 0;
        this.date = "";
        this.reps = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public SingleExercise(String name, Integer calories, String date, Integer reps) {
        this.name = name;
        this.calories = calories;
        this.date = date;
        this.reps = reps;
    }
}
