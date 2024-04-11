package fi.punakorpi.sugarbuddy;

public class CarbFactor {
    private String mealName;
    private float carbFactor;

    public CarbFactor(String mealName) {
        this.mealName = mealName;
        carbFactor = -1;
    }
    public void setCarbFactor(float carbFactor) {
        this.carbFactor = carbFactor;
    }

    public float getCarbFactor() {
        return carbFactor;
    }

    public String getMealName() {
        return mealName;
    }
}
