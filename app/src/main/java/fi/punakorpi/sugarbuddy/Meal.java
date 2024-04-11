package fi.punakorpi.sugarbuddy;

import android.view.View;

import java.util.ArrayList;

public class Meal {

    private ArrayList<Food> foods;
    String mealType;
    int mealIndexFromProfile;
    float bloodSugar;
    float carbsTotal;
    String insulinDoseText;
    float insulinDoseTotal;
    Profile profile;


    /*meal koostuu mealType, bloodSugar, carbsTotal, "insuliiniannos:"tekstistä, insulinDoseTotal*/
    /*public Meal createMeal(Food food) {
        foods.add(food);
        mealIndexFromProfile = mealIndexFromProfile;// mealType = indexi mealItemViewHolderilta, hae mainissa asuvasta mealTypeListasta indexillä oikea String (?)
        bloodSugar = findViewByID(R.id.editBlooSugarInMealActivity(?))
        for (food:foods) {
            carbsTotal =+ food.getCarbs();
        }
        insulinDoseText = "Insuliiniannos :";
        insulinDoseTotal = //lasketaan tässä
    }
    private float countBloodCorrectiveInsulin() {
        float correctiveBloodSugar = bloodSugar - 6; //voi palauttaa - merkkisen arvon, silloin vs on pienempi kuin 6 ja insulinTotalista pitää vähentää oikea määrä insuliinia
        profile.getCarbFactor(mealTypeIndex)*/

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getMealIndexFromProfile() {
        return mealIndexFromProfile;
    }

    public void setMealIndexFromProfile(int mealIndexFromProfile) {
        this.mealIndexFromProfile = mealIndexFromProfile;
    }

    public float getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public float getCarbsTotal() {
        return carbsTotal;
    }

    public void setCarbsTotal(float carbsTotal) {
        this.carbsTotal = carbsTotal;
    }

    public String getInsulinDoseText() {
        return insulinDoseText;
    }

    public void setInsulinDoseText(String insulinDoseText) {
        this.insulinDoseText = insulinDoseText;
    }

    public float getInsulinDoseTotal() {
        return insulinDoseTotal;
    }

    public void setInsulinDoseTotal(float insulinDoseTotal) {
        this.insulinDoseTotal = insulinDoseTotal;
    }
}
