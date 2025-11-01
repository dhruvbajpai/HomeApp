package com.application.homeapp.firstaws.controller;

import com.application.homeapp.firstaws.Entity.FoodDish;
import com.application.homeapp.firstaws.Entity.FoodDishQuestion;
import com.application.homeapp.firstaws.Entity.GuessResult;
import com.application.homeapp.firstaws.service.FoodGuesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foodguesser")
@CrossOrigin(origins = "*") // Allow CORS for frontend
public class FoodGuesserController {

    @Autowired
    private FoodGuesserService foodGuesserService;

    /**
     * Get a random food dish question
     */
    @GetMapping("/question")
    public FoodDishQuestion getQuestion() {
        return foodGuesserService.getRandomDish();
    }

    /**
     * Submit a guess for a dish
     */
    @PostMapping("/guess")
    public GuessResult submitGuess(@RequestParam int dishId, @RequestParam String country) {
        return foodGuesserService.checkGuess(dishId, country);
    }

    /**
     * Get all dishes (for reference/admin)
     */
    @GetMapping("/dishes")
    public List<FoodDish> getAllDishes() {
        return foodGuesserService.getAllDishes();
    }
}
