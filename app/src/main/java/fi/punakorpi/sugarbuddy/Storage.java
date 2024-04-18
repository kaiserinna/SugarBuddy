package fi.punakorpi.sugarbuddy;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage {
    private static Storage instance = null;
    private Meals meals;
    private FineliData fineliData;


    public Storage() {
        meals = new Meals();
        fineliData = new FineliData();
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    public Profile getProfile() {
        return Profile.getInstance();
    }

    public Meals getMeals() {
        return meals;
    }

    public FineliData getFineliData() {
        return fineliData;
    }
}
