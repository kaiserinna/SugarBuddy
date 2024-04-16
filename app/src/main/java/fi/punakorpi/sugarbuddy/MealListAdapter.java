package fi.punakorpi.sugarbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealListAdapter extends RecyclerView.Adapter<MealItemViewHolder> {



    private Context context;
    private Meals meals;



    public MealListAdapter(Context context, Meals meals) {
        this.context = context;
        this.meals = meals;
    }


    @NonNull
    @Override
    public MealItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealItemViewHolder(LayoutInflater.from(context).inflate(R.layout.meal_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemViewHolder holder, int position) {
        holder.mealTypeInMealItemViewHolder.setText(meals.getMealByIndex(position).getMealType());
        holder.bloodSugarInMealItemViewHolder.setText(String.format("Verensokeri: %.1f", meals.getMealByIndex(position).getBloodSugar()));
        holder.carbsTotalInMealItemViewHolder.setText(String.format("Hiilihydraatit: %.1f", meals.getMealByIndex(position).calculateCarbsTotal()));
        holder.insulinDoseInMealItemViewHolder.setText(String.format("%.1f U", meals.getMealByIndex(position).calculateInsulinDose()));
    }

    @Override
    public int getItemCount() {
        return meals.getSize();
    }



}
