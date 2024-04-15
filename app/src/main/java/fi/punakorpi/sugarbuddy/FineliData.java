package fi.punakorpi.sugarbuddy;

import java.util.ArrayList;

public class FineliData {
    private ArrayList<Ingredient> ingredients;

    public FineliData() {
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Kaurapuuro", 50));
        ingredients.add(new Ingredient("Sokeri", 100));
        ingredients.add(new Ingredient("Liha", 0));
    }

    public Ingredient getIngredientsByName(String name) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }
}
