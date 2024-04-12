package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Storage {
    private static Storage instance = null;
    private Profile profile;
    private ArrayList<Meal> meals;

    public Storage() {
        profile = Profile.getInstance();
        // hardkoodataan meals listaan aterioiden järjestys
        // aamiainen = [0], lounas = [1], jne.
        meals = new ArrayList<Meal>();
        meals.add(new Meal("Aamupala"));
        meals.add(new Meal("Lounas"));
        meals.add(new Meal("Välipala"));
        meals.add(new Meal("Päivällinen"));
        meals.add(new Meal("Iltapala"));
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    public Profile getProfile() {
        return profile;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

}
