package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Meal {

    private ArrayList<Food> foods;
    private String mealType;
    private float bloodSugar;
    private Profile profile = Profile.getInstance();

    public Meal(String mealType) {
        this.mealType = mealType;
    }

    public void setBloodSugar(float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    // Tämä funkito palauttaa yhden aterian vaatiman insuliinimäärän.
    // Se koostuu veresnokeria korjaavasta insuliinimäärästä ja hiilihydraatteja vastaavasta insuliinimäärästä
    // Nämä lasketaan insuliiniherkkyystason ja hiilihydraattikertoimien avulla.
    public float calculateInsulinDose() {
        // first this method counts carbs corrective insulin:
        float correctCarbFactor = profile.getCarbFactorByMealName(mealType);
        float carbsInsulin = calculateCarbsTotal() / correctCarbFactor;
        // then the method counts bloodsugarlevel corrective insulin:
        float insulinSensitivityLevel = profile.getInsulinSensitivityLevel();
        int BloodSugarLevelTarget = 6;
        float bloodSugarToBeCorrected = bloodSugar - BloodSugarLevelTarget; //voi palauttaa - merkkisen arvon, silloin vs on pienempi kuin 6 ja insulinTotalista pitää vähentää oikea määrä insuliinia
        float bloodSugarInsulin = bloodSugarToBeCorrected / insulinSensitivityLevel;
        return carbsInsulin + bloodSugarInsulin;
    }

    public float calculateCarbsTotal() {
        float totalWeight = 0;
        for (Food food: foods) {
            totalWeight = totalWeight + food.getWeight();
        }
        return totalWeight;
    }
    public Food getFoodByIndex(int index) {
        return foods.get(index);
    }

    public void removeFoodByIndex(int index) {
        foods.remove(index);
    }

    public String getMealType() {
        return mealType;
    }

    public float getBloodSugar() {
        return bloodSugar;
    }
}


