package fi.punakorpi.sugarbuddy;

public class Ingredient { //Fineli datan sisältö
    private String name;
    private float carbsPercentage;

    public Ingredient(String name, float carbsPercentage) {
        this.name = name;
        this.carbsPercentage = carbsPercentage;
    }
    public String getName() {
        return name;
    }

    public float getCarbsPercentage() {
        return carbsPercentage;
    }
}
