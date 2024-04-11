package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Profile {
    private String userName;
    private float insulinSensitivityLevel;
    private ArrayList<CarbFactor> carbFactors;
    private static Profile instance = null;


    public static Profile getInstance() {
        if (instance == null) {
            instance = new Profile();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getInsulinSensitivityLevel() {
        return insulinSensitivityLevel;
    }

    public void setInsulinSensitivityLevel(float insulinSensitivityLevel) {
        this.insulinSensitivityLevel = insulinSensitivityLevel;
    }

    public boolean setCarbFactorByMealName(String mealName, float carbFactor) {
        for (CarbFactor carbFactorObject : carbFactors) {
            if (carbFactorObject.getMealName().equals(mealName)) {
                carbFactorObject.setCarbFactor(carbFactor);
                return true;
            }
        }
        return false;
    }


    public float getCarbFactorByMealName(String mealName) {    //TODO: try catch tähän
        float carbFactor;
        for (CarbFactor carbFactorObject :carbFactors) {
            if (carbFactorObject.getMealName().equals(mealName)) {
                carbFactor = carbFactorObject.getCarbFactor();
                return carbFactor;
            }
        }
        return -1;
    }

    public Profile() {
        carbFactors = new ArrayList<>();
        carbFactors.add(new CarbFactor("Aamupala"));
        carbFactors.add(new CarbFactor("Lounas"));
        carbFactors.add(new CarbFactor("Välipala"));
        carbFactors.add(new CarbFactor("Päivällinen"));
        carbFactors.add(new CarbFactor("Iltapala"));

    }

    public ArrayList<CarbFactor> getCarbFactors() {
        return carbFactors;
    }
}
