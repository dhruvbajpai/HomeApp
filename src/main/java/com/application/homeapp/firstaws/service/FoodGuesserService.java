package com.application.homeapp.firstaws.service;

import com.application.homeapp.firstaws.Entity.FoodDish;
import com.application.homeapp.firstaws.Entity.FoodDishQuestion;
import com.application.homeapp.firstaws.Entity.GuessResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FoodGuesserService {

    private List<FoodDish> dishes;
    private Map<Integer, FoodDish> dishMap;
    private Random random;

    public FoodGuesserService() {
        this.dishes = new ArrayList<>();
        this.dishMap = new HashMap<>();
        this.random = new Random();
    }

    @PostConstruct
    public void initializeDishes() {
        // Initialize with sample dishes from various countries
        addDish(1, "Sushi",
                "https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=800",
                Arrays.asList("Rice", "Nori (seaweed)", "Raw fish", "Wasabi", "Soy sauce", "Pickled ginger"),
                "Vinegared rice combined with various ingredients, often featuring raw fish. A traditional Japanese dish known worldwide.",
                "Japan");

        addDish(2, "Tacos",
                "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?w=800",
                Arrays.asList("Corn tortilla", "Beef or chicken", "Cilantro", "Onion", "Lime", "Salsa"),
                "A traditional dish featuring a folded tortilla filled with various ingredients like meat, vegetables, and cheese.",
                "Mexico");

        addDish(3, "Pasta Carbonara",
                "https://images.unsplash.com/photo-1612874742237-6526221588e3?w=800",
                Arrays.asList("Spaghetti", "Eggs", "Pecorino cheese", "Guanciale (pork)", "Black pepper"),
                "A creamy pasta dish made with eggs, cheese, and cured pork. A classic Roman recipe.",
                "Italy");

        addDish(4, "Pad Thai",
                "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=800",
                Arrays.asList("Rice noodles", "Shrimp or chicken", "Peanuts", "Egg", "Bean sprouts", "Tamarind", "Fish sauce"),
                "Stir-fried rice noodles with a sweet and tangy sauce, topped with peanuts and lime.",
                "Thailand");

        addDish(5, "Croissant",
                "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=800",
                Arrays.asList("Flour", "Butter", "Yeast", "Milk", "Sugar", "Salt"),
                "A buttery, flaky, crescent-shaped pastry, perfect for breakfast with coffee.",
                "France");

        addDish(6, "Butter Chicken",
                "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
                Arrays.asList("Chicken", "Tomato sauce", "Butter", "Cream", "Garam masala", "Ginger", "Garlic"),
                "Tender chicken pieces in a rich, creamy tomato-based sauce with aromatic spices.",
                "India");

        addDish(7, "Feijoada",
                "https://images.unsplash.com/photo-1628184706209-c43ce8df7ed0?w=800",
                Arrays.asList("Black beans", "Pork", "Beef", "Sausage", "Rice", "Orange slices", "Collard greens"),
                "A hearty stew of black beans with various pork and beef cuts, served with rice and orange slices.",
                "Brazil");

        addDish(8, "Moussaka",
                "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=800",
                Arrays.asList("Eggplant", "Ground lamb or beef", "Tomato sauce", "Béchamel sauce", "Onion", "Garlic"),
                "Layered casserole with eggplant, spiced meat sauce, and creamy béchamel topping.",
                "Greece");

        addDish(9, "Peking Duck",
                "https://images.unsplash.com/photo-1567981164413-4491e98a8eac?w=800",
                Arrays.asList("Duck", "Maltose syrup", "Pancakes", "Cucumber", "Scallions", "Hoisin sauce"),
                "Roasted duck with crispy skin, served with thin pancakes, vegetables, and sweet sauce.",
                "China");

        addDish(10, "Paella",
                "https://images.unsplash.com/photo-1630384670686-913034e08921?w=800",
                Arrays.asList("Rice", "Saffron", "Chicken", "Seafood", "Bell peppers", "Peas", "Paprika"),
                "A vibrant rice dish cooked with saffron, combining meats or seafood with vegetables.",
                "Spain");

        addDish(11, "Pho",
                "https://images.unsplash.com/photo-1591814468924-caf88d1232e1?w=800",
                Arrays.asList("Rice noodles", "Beef or chicken broth", "Meat slices", "Bean sprouts", "Basil", "Lime", "Chili"),
                "Aromatic noodle soup with a clear broth, fresh herbs, and tender meat slices.",
                "Vietnam");

        addDish(12, "Schnitzel",
                "https://images.unsplash.com/photo-1632158140256-bdf6c1960aad?w=800",
                Arrays.asList("Veal or pork", "Flour", "Eggs", "Breadcrumbs", "Lemon"),
                "Breaded and fried meat cutlet, traditionally served with lemon wedges.",
                "Austria");
    }

    private void addDish(int id, String name, String imageUrl, List<String> ingredients,
                        String description, String country) {
        FoodDish dish = new FoodDish(id, name, imageUrl, ingredients, description, country);
        dishes.add(dish);
        dishMap.put(id, dish);
    }

    public FoodDishQuestion getRandomDish() {
        if (dishes.isEmpty()) {
            return null;
        }

        FoodDish dish = dishes.get(random.nextInt(dishes.size()));
        List<String> options = generateOptions(dish.getCountry());

        return new FoodDishQuestion(
            dish.getId(),
            dish.getName(),
            dish.getImageUrl(),
            dish.getIngredients(),
            dish.getDescription(),
            options
        );
    }

    public GuessResult checkGuess(int dishId, String guessedCountry) {
        FoodDish dish = dishMap.get(dishId);

        if (dish == null) {
            return new GuessResult(false, "Unknown", "Invalid dish ID");
        }

        boolean isCorrect = dish.getCountry().equalsIgnoreCase(guessedCountry.trim());
        String message = isCorrect ?
            "Correct! Well done!" :
            "Sorry, that's not correct. The dish is from " + dish.getCountry();

        return new GuessResult(isCorrect, dish.getCountry(), message);
    }

    private List<String> generateOptions(String correctCountry) {
        // Get all unique countries
        Set<String> allCountries = dishes.stream()
            .map(FoodDish::getCountry)
            .collect(Collectors.toSet());

        // Remove the correct answer
        allCountries.remove(correctCountry);

        // Convert to list and shuffle
        List<String> wrongOptions = new ArrayList<>(allCountries);
        Collections.shuffle(wrongOptions);

        // Take 3 wrong options
        List<String> options = wrongOptions.stream()
            .limit(3)
            .collect(Collectors.toList());

        // Add the correct answer
        options.add(correctCountry);

        // Shuffle again so correct answer is in random position
        Collections.shuffle(options);

        return options;
    }

    public List<FoodDish> getAllDishes() {
        return new ArrayList<>(dishes);
    }
}
