package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class Food {
    private Ingredient ingredient;
    private float weight;


    public Food(String ingredientName, float weight) {
        // this.ingredient; //json juttua;
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
}
