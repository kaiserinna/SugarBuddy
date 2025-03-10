package fi.punakorpi.sugarbuddy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String userName;
    private float insulinSensitivityLevel;
    private ArrayList<CarbFactor> carbFactors;
    private static volatile Profile instance = null;
    // private static final long serialVersionUID = 2498729462L;
    public static String profileFileName = "profile.serialized";


    public static synchronized Profile getInstance() {
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

    private Profile() {
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

    public static synchronized void saveToStream(OutputStream outputStream) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(instance);
            out.close();
        } catch (IOException ignored) {
        }
    }
    public static synchronized void loadFromStream(InputStream inputStream) {
        try {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            instance = (Profile) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ignored) {
        }

    }
}
