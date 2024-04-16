package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Food {
    private Ingredient ingredient;
    private float weight;


    public Food(Ingredient ingredient, float weight) {
        this.ingredient = ingredient;
        this.weight = weight;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float calculateCarbsInGrams() {
        return weight * ingredient.getCarbsPercentage() / 100;
    }
}
