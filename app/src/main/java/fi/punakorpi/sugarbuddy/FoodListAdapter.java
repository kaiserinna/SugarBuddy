package fi.punakorpi.sugarbuddy;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {
    private MealActivity mealActivity;
    private Meal meal = new Meal("Virhemeal");

    public FoodListAdapter(MealActivity mealActivity) {
        this.mealActivity = mealActivity;
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
        return new FoodItemViewHolder(LayoutInflater.from(mealActivity).inflate(R.layout.food_item_view, parent, false));
    }

    public void onBindViewHolder(FoodItemViewHolder holder, int position) {
        Food foodItem = meal.getFoodByIndex(position);
        holder.ingredientNameInFoodItemView.setText(foodItem.getIngredient().getName());
        holder.ingredientWeightInFoodItemView.setText(String.valueOf(foodItem.getWeight()));
        holder.totalCarbsInGramsInFoodItemView.setText(String.valueOf(foodItem.calculateCarbsInGrams()));
        holder.removeImageInFoodItemView.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            meal.removeFoodByIndex(position);
            notifyItemRemoved(pos);
            mealActivity.updateMealActivityView();
        });
    }

    @Override
    public int getItemCount() {
        return meal.getFoodListSize();
    }
}