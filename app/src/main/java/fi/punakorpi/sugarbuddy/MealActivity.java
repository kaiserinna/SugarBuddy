package fi.punakorpi.sugarbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    private TextView mealName;
    private EditText bloodSugar;
    private EditText foodIngredient;
    private EditText foodWeight;
    private RecyclerView rvFoodList;
    private Button addButton;
    private TextView insulinTotal;
    private Meal meal;
    private FoodListAdapter foodListAdapter;

    private Storage storage = Storage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mealName = findViewById(R.id.textMealTypeInMealActivity);
        bloodSugar = findViewById(R.id.editBloodSugarInMealActivity);
        foodIngredient = findViewById(R.id.editTextIngredientNameInMealActivity);
        foodWeight = findViewById(R.id.editTextNumberDecimalIngredientWeight);
        rvFoodList = findViewById(R.id.rvFoodListInMealActivity);
        insulinTotal = findViewById(R.id.textViewTotalInsulinLevelStringInMealActivity);
        addButton = findViewById(R.id.buttonAddFoodItemToRecyclerViewInMealActivity);

        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        foodListAdapter = new FoodListAdapter(getApplicationContext());
        rvFoodList.setAdapter(foodListAdapter);
        updateScreen();
    }

    private void updateScreen() {
        int index = getIntent().getIntExtra("mealTypeIndex", -1);
        meal = storage.getMeals().getMealByIndex(index);
        mealName.setText(meal.getMealType());
        float bs = meal.getBloodSugar();
        bloodSugar.setText(bs <= 0 ? "" : String.format("%.1f", bs));
        foodIngredient.setText("");
        foodWeight.setText("");
        foodListAdapter.setMeal(meal);
        if (meal.calculateInsulinDose() == 0) {
            insulinTotal.setText("-- U");
            return;
        }
        insulinTotal.setText(String.format("%.1f U", meal.calculateInsulinDose()));
    }
    public void onResume() {
        super.onResume();
        updateScreen();
    }

    public void onStart() {
        super.onStart();
        updateScreen();
    }


    // addFood() metodi painettaessa Lisää -nappia.
    // silloin se:
    // - lisää foodObjektin foods listaan, joka tulee näkyviin recyclerViewiin
    // - tyhjentää syötä ruoka-aine kentän ja syötä gramma kentän EditTextFoodName.setText("")
    // - päivittää EditText insulinDoseTotal kentän
    public void addFood(View view) {
        ArrayList<Ingredient> foundIngredients = storage.getFineliData().getIngredientsByName(foodIngredient.getText().toString());
        if (foundIngredients.size() == 0) {
           foodIngredient.setText("Ei löydy");
           return;
        }
        if (foundIngredients.size() > 1) {
            foodIngredient.setText(("Löytyi monta"));
            // TODO: jos haussa löytyy monia ruoka-aineita, käyttäjä voi valita haluamansa
            return;
        }
        //ensin etsitään FineliDatasta editTextin nimellä se getIngredientsByname
        //siitä tulee ingredient
        float weight;
        try {
            weight = Float.parseFloat(foodWeight.getText().toString());
        } catch (NumberFormatException e) {
            foodWeight.setText("Ei numero");
            return;
        }
        if (weight <= 0) {
            foodWeight.setText("Anna paino oikein");
            return;
        }
        Food newFood = new Food(foundIngredients.get(0), weight);

        meal.addFood(newFood);
        meal.setBloodSugar(Float.parseFloat(bloodSugar.getText().toString()));
        foodListAdapter.notifyItemInserted(meal.getFoodListSize() - 1);
        foodIngredient.setText("");
        foodWeight.setText("");
        insulinTotal.setText(String.format("%.1f U", meal.calculateInsulinDose()));
    }
}

