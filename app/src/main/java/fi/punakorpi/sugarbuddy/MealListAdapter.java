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
    private ArrayList<Meal> mealList;



    public MealListAdapter(Context context, ArrayList<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }


    @NonNull
    @Override
    public MealItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MealItemViewHolder(LayoutInflater.from(context).inflate(R.layout.meal_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemViewHolder holder, int position) {
        holder.mealTypeInMealItemViewHolder.setText(mealList.get(position).getMealType());
        holder.bloodSugarInMealItemViewHolder.setText(String.valueOf(mealList.get(position).getBloodSugar()));
        holder.carbsTotalInMealItemViewHolder.setText(String.valueOf(mealList.get(position).calculateCarbsTotal()));
        holder.insuliiniannosTextInMealItemViewHolder.setText("Insuliiniannos:");
        holder.insulinDoseInMealItemViewHolder.setText(String.valueOf(mealList.get(position).getBloodSugar()));
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }



}
