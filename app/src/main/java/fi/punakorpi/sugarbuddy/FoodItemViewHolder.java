                                                                                                                      package fi.punakorpi.sugarbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FoodItemViewHolder extends RecyclerView.ViewHolder {
    TextView ingredientNameInFoodItemView;
    TextView ingredientWeightInFoodItemView;
    TextView totalCarbsInGramsInFoodItemView;

    public FoodItemViewHolder(View view) {
        super(view);
        ingredientNameInFoodItemView = view.findViewById(R.id.textViewIngredientNameInFoodItemView);
        ingredientWeightInFoodItemView = view.findViewById(R.id.textViewIngredientWeightInFoodItemView);
        totalCarbsInGramsInFoodItemView = view.findViewById(R.id.textViewCarbsInGramsInFoodItemView);
    }
}
