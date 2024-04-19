package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private Context context = this;
    private ConstraintLayout mainLayout;

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
        mainLayout = findViewById(R.id.main);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));
        foodListAdapter = new FoodListAdapter(this);
        rvFoodList.setAdapter(foodListAdapter);
        updateMealActivityView();
    }

    public void updateMealActivityView() {
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
        updateMealActivityView();
    }

    public void onStart() {
        super.onStart();
        updateMealActivityView();
    }
    private void handleIngredient(Ingredient ingredient) {
        float weight;
        try {
            weight = Float.parseFloat(foodWeight.getText().toString());
        } catch (NumberFormatException e) {
            foodWeight.setText("Syötä paino grammoissa");
            return;
        }
        if (weight <= 0) {
            foodWeight.setText("Anna paino oikein");
            return;
        }
        Food newFood = new Food(ingredient, weight);

        meal.addFood(newFood);
        meal.setBloodSugar(Float.parseFloat(bloodSugar.getText().toString()));
        foodListAdapter.notifyItemInserted(meal.getFoodListSize() - 1);
        foodIngredient.setText("");
        foodWeight.setText("");
        insulinTotal.setText(String.format("%.1f U", meal.calculateInsulinDose()));

    }
    // addFood() metodi painettaessa Lisää -nappia.
    // silloin se:
    // - lisää foodObjektin foods listaan, joka tulee näkyviin recyclerViewiin
    // - tyhjentää syötä ruoka-aine kentän ja syötä gramma kentän EditTextFoodName.setText("")
    // - päivittää EditText insulinDoseTotal kentän
    public void addFood(View view) {
        mainLayout.requestFocus();
        Boolean isFoodEmpty = foodIngredient.getText().toString().isEmpty();
        Boolean isWeightEmpty = foodWeight.getText().toString().isEmpty();
        if (isFoodEmpty) {
            Toast.makeText(context, isWeightEmpty ? "Ruoka-aine ja painotieto puuttuvat" : "Ruoka-aine puuttuu", Toast.LENGTH_LONG).show();
            foodIngredient.requestFocus();
            return;
        }
        if (isWeightEmpty) {
            Toast.makeText(context, "Painotieto puuttuu", Toast.LENGTH_LONG).show();
            foodWeight.requestFocus();
            return;
        }
        FineliData fineliData = new FineliData();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Ingredient> foundIngredients = fineliData.getIngredientsByName(foodIngredient.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (foundIngredients.isEmpty()) {
                            foodIngredient.setText("Ei löydy");
                            return;
                        }
                        if (foundIngredients.size() > 1) {
                            selectionDialog(foundIngredients);
                        } else {
                            handleIngredient(foundIngredients.get(0));
                        }
                    }
                });
            }
        });
        // TODO: addFood() virheilmoitus popup, jos syötä verensokerikenttä on tyhjä
    }

    // wow mikä efekti, kiitos copilot<3
    private void selectionDialog(ArrayList<Ingredient> foundIngredients) {
        ArrayList<String> foundIngredientNames = new ArrayList<>();
        for (Ingredient ingredient : foundIngredients) {
            foundIngredientNames.add(ingredient.getName());
        }
        CharSequence[] foundIngredientNamesAsCharSeq = foundIngredientNames.toArray(new CharSequence[foundIngredientNames.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Valitse seuraavista:");
        builder.setItems(foundIngredientNamesAsCharSeq, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleIngredient(foundIngredients.get(which));
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

