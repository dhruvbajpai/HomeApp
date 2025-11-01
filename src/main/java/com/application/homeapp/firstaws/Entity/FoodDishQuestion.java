package com.application.homeapp.firstaws.Entity;

import java.util.List;

/**
 * DTO for food dish questions - excludes the country answer
 */
public class FoodDishQuestion {
    private int id;
    private String name;
    private String imageUrl;
    private List<String> ingredients;
    private String description;
    private List<String> options; // Multiple choice country options

    public FoodDishQuestion() {
    }

    public FoodDishQuestion(int id, String name, String imageUrl, List<String> ingredients,
                           String description, List<String> options) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.description = description;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
