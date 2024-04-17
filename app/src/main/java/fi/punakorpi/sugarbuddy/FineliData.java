package fi.punakorpi.sugarbuddy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class FineliData {
    private ArrayList<Ingredient> ingredients;

    public FineliData() {
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Kaurajuoma", 70 ));
        ingredients.add(new Ingredient("Kaurapuuro", 50));
        ingredients.add(new Ingredient("Sokeri", 100));
        ingredients.add(new Ingredient("Liha", 0));
    }

    public ArrayList<Ingredient> getIngredientsByName(String name) {
        ArrayList<Ingredient> ingredientsFromFineli = new ArrayList<>();
        String lowercaseName = name.toLowerCase(Locale.getDefault());
        for (Ingredient ingredient: ingredients) {
            String lowercaseIngredient = ingredient.getName().toLowerCase(Locale.getDefault());
            if (lowercaseIngredient.startsWith(lowercaseName)) {
                ingredientsFromFineli.add(ingredient);
            }
        }
        return ingredientsFromFineli;
    }


    /*public static void FineliData(String ingredientName) {
        "https://fineli.fi/fineli/api/v1/foods?q=" + ingredientName.toLowerCase();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode fineliData = null;
        try {
            fineliData = objectMapper.readTree(new URL("https://fineli.fi/fineli/api/v1/foods?q=" + ingredientName));
        }

    }*/


}
