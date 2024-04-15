package fi.punakorpi.sugarbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private Storage storage = Storage.getInstance();
    private ImageView sugarBuddyImage;
    private ImageView profileButton;
    private RecyclerView mealRecyclerView;
    private MealListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mealRecyclerView = findViewById(R.id.rvMealListInMainActivity);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MealListAdapter(getApplicationContext(), storage.getMeals());
        mealRecyclerView.setAdapter(adapter);
        updateScreen();
    }
    private void updateScreen() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    public void onResume() {
        super.onResume();
        updateScreen();
    }

    public void onStart() {
        super.onStart();
        updateScreen();
    }

    public void switchToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}