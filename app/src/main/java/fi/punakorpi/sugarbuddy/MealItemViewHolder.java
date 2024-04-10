package fi.punakorpi.sugarbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealItemViewHolder extends RecyclerView.ViewHolder {
    public TextView mealTypeInMealItemViewHolder;
    public TextView bloodSugarInMealItemViewHolder;
    public TextView carbsTotalInMealItemViewHolder;
    public TextView insuliiniannosTextInMealItemViewHolder;
    public TextView insulinDoseInMealItemViewHolder;

    public MealItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mealTypeInMealItemViewHolder = itemView.findViewById(R.id.textViewMealTypeInMealItemView);
        bloodSugarInMealItemViewHolder = itemView.findViewById(R.id.textViewBloodSugarInMealItemView);
        carbsTotalInMealItemViewHolder = itemView.findViewById(R.id.textViewCarbsTotalInMealItemView);
        // insuliiniannosTextInMealItemViewHolder = "Insuliiniannos :"; does not function when this returns String and not TextView.
        insulinDoseInMealItemViewHolder = itemView.findViewById(R.id.textViewInsulinDoseInMealItemView);
    }
}
