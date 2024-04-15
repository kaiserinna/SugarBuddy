package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Meals {
    ArrayList<Meal> meals = new ArrayList<>();

    public Meals() {
        meals.add(new Meal("Aamupala"));
        meals.add(new Meal("Lounas"));
        meals.add(new Meal("Välipala"));
        meals.add(new Meal("Päivällinen"));
        meals.add(new Meal("Iltapala"));
    }
    public Meal getMealByIndex(int index) {
        return meals.get(index);
    }
    public int getSize() {
        return meals.size();
    }
}
