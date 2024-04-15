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
        bloodSugar.setText(String.valueOf(meal.getBloodSugar()));
        foodIngredient.setText("");
        foodWeight.setText("");
        foodListAdapter.setMeal(meal);
        insulinTotal.setText(String.valueOf(meal.calculateInsulinDose()));
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
        meal.addFood(new Food((foodIngredient.getText().toString()), Float.parseFloat(foodWeight.getText().toString())));
        foodListAdapter.notifyItemInserted(meal.getFoodListSize() - 1);
        foodIngredient.setText("");
        foodWeight.setText("");
        insulinTotal.setText(String.valueOf(meal.calculateInsulinDose()));
    }
}

