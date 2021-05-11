public class Consumable {
    public String name;
    public int calories;

    public Consumable() {
        this.name = "";
        this.calories = 0;
    }
    public Consumable(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName(){return this.name;}

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return this.calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "Name='" + name + '\'' +
                ", Calories='" + calories + '\'' +
                '}';
    }
}
