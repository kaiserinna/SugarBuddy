package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {
    private Context context;
    private Meal meal = new Meal("Virhemeal");

    public FoodListAdapter(Context context) {
        this.context = context;
    }

    public void setMeal(Meal meal) {
        if (!this.meal.equals(meal)) {
            this.meal = meal;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item_view, parent, false));
    }

    public void onBindViewHolder(FoodItemViewHolder holder, int position) {
        Food foodItem = meal.getFoodByIndex(position);
        holder.ingredientNameInFoodItemView.setText(foodItem.getIngredient().getName());
        holder.ingredientWeightInFoodItemView.setText(String.valueOf(foodItem.getWeight()));
        holder.totalCarbsInGramsInFoodItemView.setText(String.valueOf(foodItem.calculateCarbsInGrams()));
    }

    @Override
    public int getItemCount() {
        return meal.getFoodListSize();
    }
}