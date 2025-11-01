# FoodGuesser - Quick Start Guide

## Running the Game

### Option 1: Python Test Server (Instant - Currently Running!)

The test server is **already running** at http://localhost:8080

```bash
# Start the server (if not running)
python3 test_server.py

# Open in browser
# Navigate to: http://localhost:8080
```

**Status:** ✅ Running on port 8080

---

### Option 2: Spring Boot (Production)

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Or run the JAR directly
java -jar target/firstaws-0.0.1-SNAPSHOT.jar

# Open browser to: http://localhost:8080
```

---

## Game Instructions

1. **View the Dish**
   - A random dish image will appear
   - Read the description and ingredients

2. **Guess the Country**
   - Click on one of the 4 country options
   - You have one guess per dish

3. **Get Feedback**
   - Correct: Green highlight + congratulations
   - Wrong: Red highlight + correct answer revealed

4. **Track Your Score**
   - Correct answers counter
   - Wrong answers counter
   - Total questions answered

5. **Next Dish**
   - Click "Next Dish" to continue
   - Try to beat your high score!

---

## API Endpoints

### Get a Random Question
```bash
curl http://localhost:8080/api/foodguesser/question
```

### Submit a Guess
```bash
curl "http://localhost:8080/api/foodguesser/guess?dishId=1&country=Japan"
```

### Get All Dishes
```bash
curl http://localhost:8080/api/foodguesser/dishes
```

---

## Files Created

### Backend (Java/Spring Boot)
```
src/main/java/com/application/homeapp/firstaws/
├── Entity/
│   ├── FoodDish.java           - Dish entity model
│   ├── FoodDishQuestion.java   - Question DTO
│   └── GuessResult.java        - Result DTO
├── service/
│   └── FoodGuesserService.java - Game logic
└── controller/
    └── FoodGuesserController.java - REST API
```

### Frontend
```
src/main/resources/static/
└── index.html                  - Complete game UI
```

### Testing
```
├── test_server.py              - Python mock server
├── TEST_REPORT.md              - Complete test results
├── FOODGUESSER_README.md       - Full documentation
└── QUICK_START.md              - This file
```

---

## Available Dishes

1. Sushi (Japan)
2. Tacos (Mexico)
3. Pasta Carbonara (Italy)
4. Pad Thai (Thailand)
5. Croissant (France)
6. Butter Chicken (India)
7. Feijoada (Brazil)
8. Moussaka (Greece)
9. Peking Duck (China)
10. Paella (Spain)
11. Pho (Vietnam)
12. Schnitzel (Austria)

---

## Screenshots

**Game Interface Features:**
- Beautiful purple gradient background
- White card with shadow effects
- Score tracking at the top
- Large food image
- Dish name and description
- Ingredients list
- 4 multiple choice buttons
- Color-coded feedback
- Next button for new questions
- Fully responsive design

---

## Troubleshooting

**Problem:** Server won't start
**Solution:** Check if port 8080 is already in use
```bash
lsof -i :8080
```

**Problem:** Images not loading
**Solution:** Check internet connection (images from Unsplash)

**Problem:** Maven build fails
**Solution:** Use the Python test server instead:
```bash
python3 test_server.py
```

---

## Current Status

✅ **Server Running:** http://localhost:8080
✅ **All Tests Passed:** 6/6
✅ **Ready to Play:** YES

**Have fun guessing!** 🍽️🌍
