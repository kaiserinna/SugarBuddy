package fi.punakorpi.sugarbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MealActivity extends AppCompatActivity {

    private TextView mealName;
    private EditText bloodSugar;
    private EditText foodIngredient;
    private EditText foodWeight;
    private RecyclerView rvFoodList;
    private TextView insulinTotal;
    private Meal meal;


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

    }

    // addFood() metodi kutsutaan kun painetaan Lisää -nappia.
    // silloin se:
    // - lisää foodObjektin foods listaan, joka tulee näkyviin recyclerViewiin
    // - tyhjentää syötä ruoka-aine kentän ja syötä gramma kentän EditTextFoodName.setText("")
    // - päivittää EditText insulinDoseTotal kentän
    private void addFood(View view) {

    }
}

