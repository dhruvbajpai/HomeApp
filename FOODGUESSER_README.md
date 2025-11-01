# 🍽️ FoodGuesser - Country Guessing Game

A fun interactive web game where players guess which country a dish is from based on its picture, ingredients, and description!

## Features

- **12 International Dishes** from countries around the world (Japan, Mexico, Italy, Thailand, France, India, Brazil, Greece, China, Spain, Vietnam, Austria)
- **Beautiful UI** with responsive design
- **Real-time Scoring** tracking correct/incorrect answers
- **Multiple Choice Questions** with 4 country options per dish
- **High-quality Food Images** from Unsplash
- **RESTful API** backend built with Spring Boot

## Game Screenshots

The game displays:
- A mouth-watering image of the dish
- The dish name
- A short description of the dish
- List of ingredients
- 4 country options to choose from
- Real-time feedback on your guess
- Running score (correct, wrong, total)

## Technology Stack

### Backend
- **Java 8**
- **Spring Boot 2.0.3**
- **Maven** for dependency management

### Frontend
- **HTML5**
- **CSS3** with gradient backgrounds and animations
- **Vanilla JavaScript** (no frameworks needed)
- **Responsive Design** for mobile and desktop

## Project Structure

```
src/main/java/com/application/homeapp/firstaws/
├── Entity/
│   ├── FoodDish.java           # Main dish entity
│   ├── FoodDishQuestion.java   # DTO for questions (hides answer)
│   └── GuessResult.java        # DTO for guess responses
├── service/
│   └── FoodGuesserService.java # Game logic and data
├── controller/
│   └── FoodGuesserController.java # REST API endpoints
└── FirstawsApplication.java    # Spring Boot main class

src/main/resources/static/
└── index.html                   # Game frontend
```

## API Endpoints

### GET `/api/foodguesser/question`
Returns a random food dish question with 4 country options.

**Response:**
```json
{
  "id": 1,
  "name": "Sushi",
  "imageUrl": "https://...",
  "ingredients": ["Rice", "Nori", "Raw fish", "Wasabi", "Soy sauce"],
  "description": "Vinegared rice combined with...",
  "options": ["Japan", "China", "Korea", "Thailand"]
}
```

### POST `/api/foodguesser/guess?dishId={id}&country={country}`
Submit a guess for a dish.

**Response:**
```json
{
  "correct": true,
  "correctAnswer": "Japan",
  "message": "Correct! Well done!"
}
```

### GET `/api/foodguesser/dishes`
Returns all available dishes (for admin/reference).

## How to Run

### Prerequisites
- Java 8 or higher
- Maven 3.x

### Steps

1. **Clone the repository**
   ```bash
   cd /path/to/HomeApp
   ```

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR directly:
   ```bash
   java -jar target/firstaws-0.0.1-SNAPSHOT.jar
   ```

4. **Open your browser**
   ```
   http://localhost:8080
   ```

5. **Start playing!**
   - View the dish image, description, and ingredients
   - Click on the country you think the dish is from
   - Get instant feedback
   - Click "Next Dish" to continue playing

## Game Flow

1. A random dish is displayed with its image, description, and ingredients
2. Player sees 4 country options (one correct, three wrong)
3. Player selects a country
4. Game provides immediate feedback:
   - ✓ Correct: Green highlight with congratulations
   - ✗ Wrong: Red highlight with correct answer revealed
5. Score is updated (correct/wrong/total)
6. Player clicks "Next Dish" to continue
7. Process repeats with a new random dish

## Dishes Included

1. **Sushi** - Japan
2. **Tacos** - Mexico
3. **Pasta Carbonara** - Italy
4. **Pad Thai** - Thailand
5. **Croissant** - France
6. **Butter Chicken** - India
7. **Feijoada** - Brazil
8. **Moussaka** - Greece
9. **Peking Duck** - China
10. **Paella** - Spain
11. **Pho** - Vietnam
12. **Schnitzel** - Austria

## Customization

### Adding More Dishes

Edit `FoodGuesserService.java` and add new dishes in the `initializeDishes()` method:

```java
addDish(13, "Dish Name",
    "https://image-url.com",
    Arrays.asList("Ingredient 1", "Ingredient 2", "..."),
    "Description of the dish",
    "Country Name");
```

### Changing Styling

Edit `/src/main/resources/static/index.html` - all CSS is embedded in the `<style>` section.

### Modifying Game Logic

Edit `FoodGuesserService.java`:
- Change number of options: Modify `generateOptions()` method
- Add difficulty levels
- Implement hint system
- Add timer functionality

## CORS Configuration

The API has CORS enabled (`@CrossOrigin(origins = "*")`) to allow frontend development on different ports. For production, restrict this to specific origins:

```java
@CrossOrigin(origins = "https://yourdomain.com")
```

## Future Enhancements

- [ ] User authentication and profiles
- [ ] Global leaderboard
- [ ] Daily challenges
- [ ] Difficulty levels (show fewer ingredients for harder mode)
- [ ] Timer-based scoring
- [ ] Hints system
- [ ] Share results on social media
- [ ] More dishes (expand to 50+ dishes)
- [ ] Regional variations (show different dishes from same country)
- [ ] Educational mode (learn about cuisines)

## License

This is a demo project for educational purposes.

## Credits

- Food images from [Unsplash](https://unsplash.com)
- Built with Spring Boot and love for food! 🍜

---

**Enjoy playing FoodGuesser and expanding your culinary geography knowledge!** 🌍🍕
