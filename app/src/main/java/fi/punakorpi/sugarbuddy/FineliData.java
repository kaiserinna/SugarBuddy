package fi.punakorpi.sugarbuddy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.net.URI;
import java.net.URISyntaxException;

public class FineliData {

    public ArrayList<Ingredient> getIngredientsByName(String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode fineliData = null;
        try {
            fineliData = objectMapper.readTree(new URL("https://fineli.fi/fineli/api/v1/foods?q=" + name.toLowerCase().replace(" ", "%20")));
        } catch (MalformedURLException e) {
            return new ArrayList<>(); //TODO: virhekäsittely?
        } catch (IOException e) {
            return new ArrayList<>(); //TODO: virhekäsittely?
        }
        ArrayList<Ingredient> ingredientsFromFineli = new ArrayList<>();
        for (JsonNode ingredientData : fineliData) {
            String foodName = ingredientData.get("name").get("fi").asText();
            float carbsPercentage = (float) ingredientData.get("carbohydrate").asDouble();
            ingredientsFromFineli.add(new Ingredient(foodName, carbsPercentage));
        }
        return ingredientsFromFineli;
    }
}
