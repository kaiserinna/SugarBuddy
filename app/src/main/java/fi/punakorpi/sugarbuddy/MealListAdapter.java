package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealListAdapter extends RecyclerView.Adapter<MealItemViewHolder> {
    private Context context;
    private Meal mealList;

    public MealListAdapter(Context context) {
        this.context = context;
    //    this.mealList = Meal.
    }

    @NonNull
    @Override
    public MealItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
