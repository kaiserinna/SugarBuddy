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

    }
