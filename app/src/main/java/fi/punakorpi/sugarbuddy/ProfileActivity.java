package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity {

    private EditText userName;
    private EditText insulinSensitivityLevel;
    private EditText carbFactorBreakfast;
    private EditText carbFactorLunch;
    private EditText carbFactorSnack;
    private EditText carbFactorDinner;
    private EditText carbFactorEveningSnack;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        profile = Profile.getInstance();
        userName = findViewById(R.id.editTextNameInProfile);
        insulinSensitivityLevel = findViewById(R.id.editTextNumberDecimalSugarSensitivityLevelInProfile);
        carbFactorBreakfast = findViewById(R.id.editTextNumberDecimalCarbFactorBreakfast);
        carbFactorLunch = findViewById(R.id.editTextNumberDecimalCarbFactorLunch);
        carbFactorSnack = findViewById(R.id.editTextNumberDecimalCarbFactorSnack);
        carbFactorDinner = findViewById(R.id.editTextNumberDecimalCarbFactorDinner);
        carbFactorEveningSnack = findViewById(R.id.editTextNumberDecimalCarbFactorEveningSnack);

        loadProfile();

    }
    // kun käyttäjä on syöttänyt arvot kenttiin, ja painaa "tallenna" nappia, niin silloin onClikistä tapahtuu saveProfile
    // saveProfilen tehtävä on siirtää tieto käyttöliittymäkomponenteista Profile-objektiin
    public void saveProfile(View view) {
        profile.setUserName(userName.getText().toString());
        try {
            String insulinSensitivityLevelString = insulinSensitivityLevel.getText().toString();
            float insulinSensitivityLevelFloat = Float.parseFloat(insulinSensitivityLevelString);
            profile.setInsulinSensitivityLevel(insulinSensitivityLevelFloat);
        } catch (NumberFormatException e) {
            //TODO: error ilmoitukset puuttuu
            insulinSensitivityLevel.setText("Virhe");
        }
        saveCarbFactorDataToMealType(carbFactorBreakfast, "Aamupala");
        saveCarbFactorDataToMealType(carbFactorLunch, "Lounas");
        saveCarbFactorDataToMealType(carbFactorSnack, "Välipala");
        saveCarbFactorDataToMealType(carbFactorDinner, "Päivällinen");
        saveCarbFactorDataToMealType(carbFactorEveningSnack, "Iltapala");
    }

    // tämä on apufunktio saveProfilelle, joka tallettaa carbFactor -komponentin tiedot profiilidataan
    private void saveCarbFactorDataToMealType(EditText component, String mealName) {
        try {
            String carbText = component.getText().toString();
            float carbFloat = Float.parseFloat(carbText);
            profile.setCarbFactorByMealName(mealName, carbFloat);

        } catch (NumberFormatException e) {
            //TODO: error ilmoitukset puuttuu
            component.setText("Virhe");
        }
    }

    // kun käyttäjä tulee ProfileActivityyn (oli tietoja tallennettu tai ei) niin silloin tätä kutsutaan
    // Nyt tehdään niin, että kutsutaan tätä onCreatessa.
    // loadProfilen tehtävä on ladata tiedot Profile objektista käyttöliittymäkomponentteihin, jotka ovat tässä propertyinä
    //
    public void loadProfile() {
        userName.setText(profile.getUserName());
        float insulinSensitivityLevelFloat = profile.getInsulinSensitivityLevel();
        if (insulinSensitivityLevelFloat > 0) {
            insulinSensitivityLevel.setText(String.valueOf(insulinSensitivityLevelFloat));
        }
        loadCarbFactorByMealName(carbFactorBreakfast, "Aamupala");
        loadCarbFactorByMealName(carbFactorLunch, "Lounas");
        loadCarbFactorByMealName(carbFactorSnack, "Välipala");
        loadCarbFactorByMealName(carbFactorDinner, "Päivällinen");
        loadCarbFactorByMealName(carbFactorEveningSnack, "Iltapala");
    }
    private void loadCarbFactorByMealName(EditText component, String mealName) {
        float carbFactorFloat = profile.getCarbFactorByMealName(mealName);
        if (carbFactorFloat >= 0) {
            component.setText(String.valueOf(carbFactorFloat));
        }
    }
    public void onPaused() {
        try {
            Profile.saveToStream(this.openFileOutput(Profile.profileFileName, Context.MODE_PRIVATE));
        } catch (FileNotFoundException ignored) {
        }
        super.onPause();
    }
}