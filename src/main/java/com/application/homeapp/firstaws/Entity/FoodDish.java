package com.application.homeapp.firstaws.Entity;

import java.util.List;

public class FoodDish {
    private int id;
    private String name;
    private String imageUrl;
    private List<String> ingredients;
    private String description;
    private String country;

    public FoodDish() {
    }

    public FoodDish(int id, String name, String imageUrl, List<String> ingredients, String description, String country) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.description = description;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
