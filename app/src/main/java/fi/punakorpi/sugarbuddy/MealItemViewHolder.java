package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealItemViewHolder extends RecyclerView.ViewHolder {

    public class MealItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                switchToMealActivity(view);
            }
        }
    }

    public TextView mealTypeInMealItemViewHolder;
    public TextView bloodSugarInMealItemViewHolder;
    public TextView carbsTotalInMealItemViewHolder;
    public TextView insuliiniannosTextInMealItemViewHolder;
    public TextView insulinDoseInMealItemViewHolder;

    public MealItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new MealItemClickListener());
        mealTypeInMealItemViewHolder = itemView.findViewById(R.id.textViewMealTypeInMealItemView);
        bloodSugarInMealItemViewHolder = itemView.findViewById(R.id.textViewBloodSugarInMealItemView);
        carbsTotalInMealItemViewHolder = itemView.findViewById(R.id.textViewCarbsTotalInMealItemView);
        // insuliiniannosTextInMealItemViewHolder = "Insuliiniannos :"; does not function when this returns String and not TextView.
        insulinDoseInMealItemViewHolder = itemView.findViewById(R.id.textViewInsulinDoseInMealItemView);
    }
    public void switchToMealActivity(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, MealActivity.class);
        context.startActivity(intent);
    }

}
