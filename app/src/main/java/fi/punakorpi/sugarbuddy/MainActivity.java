package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private Storage storage = Storage.getInstance();
    private ImageView sugarBuddyImage;
    private TextView userName;
    private ImageView profileButton;
    private RecyclerView mealRecyclerView;
    private MealListAdapter adapter;
    private Context context;


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
        context = this;
        userName = findViewById(R.id.textViewNameInMain);
        mealRecyclerView = findViewById(R.id.rvMealListInMainActivity);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MealListAdapter(getApplicationContext(), storage.getMeals());
        mealRecyclerView.setAdapter(adapter);
        try {
            Profile.loadFromStream(context.openFileInput(Profile.profileFileName));
        } catch (FileNotFoundException ignored) {
            switchToProfileActivity(null);
        }
    }
    private void updateScreen() {
        String name = storage.getProfile().getUserName();
        userName.setText(name);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
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