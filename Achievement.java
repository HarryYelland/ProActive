public class Achievement {
    public String date;
    public String achievement;
    public Integer points;

    Achievement() {
        this.date = "N/A";
        this.achievement = "N/A";
        this.points = 0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "date='" + date + '\'' +
                ", achievement='" + achievement + '\'' +
                ", points=" + points +
                '}';
    }
}