package fi.punakorpi.sugarbuddy;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MealActivity extends AppCompatActivity {

    private EditText bloodSugar;
    private EditText foodIngredient;
    private EditText foodWeight;
    private RecyclerView rvFoodList;

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
    /* pitää muistaa kun tekee metodia "LISÄÄ" nappulalle,
    että se nappula pitää ensin hakee ingredient data Finelistä,
    sitten se converttaa EditTextistä tulleen painodatan sen ingredientPercentagen kanssa
    saaden aikaan Food TOTALgramsCarbsPerPortion eli gHH
    sit ku koko data on laskettu oikein se pitää tallentaa Food food olio listaan
    ja sit vielä onClikissä se, että se asettaa tyhjän kohdan
    ruoka-aineeseen ja grammamäärään, EditTextFoodName.setText("")
    että voidaan syöttää helposti seuraava ruoka-aine perään
    ajatuksena on myös, että lista pivittyy jatkuvasti
    ja samaan aikaan päivittyy myös EditText insulinDoseTotal sivun yläosassa painamatta mitään*/

}

