package fi.punakorpi.sugarbuddy;

import java.io.Serializable;

public class CarbFactor implements Serializable {
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
