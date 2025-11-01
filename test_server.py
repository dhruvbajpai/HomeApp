#!/usr/bin/env python3
"""
Simple test server for FoodGuesser game
This mocks the Spring Boot API so we can test the frontend
"""

from http.server import HTTPServer, SimpleHTTPRequestHandler
import json
import random
from urllib.parse import parse_qs, urlparse
import os

# Sample dishes data
DISHES = [
    {
        "id": 1,
        "name": "Sushi",
        "imageUrl": "https://images.unsplash.com/photo-1579584425555-c3ce17fd4351?w=800",
        "ingredients": ["Rice", "Nori (seaweed)", "Raw fish", "Wasabi", "Soy sauce", "Pickled ginger"],
        "description": "Vinegared rice combined with various ingredients, often featuring raw fish. A traditional Japanese dish known worldwide.",
        "country": "Japan"
    },
    {
        "id": 2,
        "name": "Tacos",
        "imageUrl": "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?w=800",
        "ingredients": ["Corn tortilla", "Beef or chicken", "Cilantro", "Onion", "Lime", "Salsa"],
        "description": "A traditional dish featuring a folded tortilla filled with various ingredients like meat, vegetables, and cheese.",
        "country": "Mexico"
    },
    {
        "id": 3,
        "name": "Pasta Carbonara",
        "imageUrl": "https://images.unsplash.com/photo-1612874742237-6526221588e3?w=800",
        "ingredients": ["Spaghetti", "Eggs", "Pecorino cheese", "Guanciale (pork)", "Black pepper"],
        "description": "A creamy pasta dish made with eggs, cheese, and cured pork. A classic Roman recipe.",
        "country": "Italy"
    },
    {
        "id": 4,
        "name": "Pad Thai",
        "imageUrl": "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=800",
        "ingredients": ["Rice noodles", "Shrimp or chicken", "Peanuts", "Egg", "Bean sprouts", "Tamarind", "Fish sauce"],
        "description": "Stir-fried rice noodles with a sweet and tangy sauce, topped with peanuts and lime.",
        "country": "Thailand"
    },
    {
        "id": 5,
        "name": "Croissant",
        "imageUrl": "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=800",
        "ingredients": ["Flour", "Butter", "Yeast", "Milk", "Sugar", "Salt"],
        "description": "A buttery, flaky, crescent-shaped pastry, perfect for breakfast with coffee.",
        "country": "France"
    },
    {
        "id": 6,
        "name": "Butter Chicken",
        "imageUrl": "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
        "ingredients": ["Chicken", "Tomato sauce", "Butter", "Cream", "Garam masala", "Ginger", "Garlic"],
        "description": "Tender chicken pieces in a rich, creamy tomato-based sauce with aromatic spices.",
        "country": "India"
    },
    {
        "id": 7,
        "name": "Feijoada",
        "imageUrl": "https://images.unsplash.com/photo-1628184706209-c43ce8df7ed0?w=800",
        "ingredients": ["Black beans", "Pork", "Beef", "Sausage", "Rice", "Orange slices", "Collard greens"],
        "description": "A hearty stew of black beans with various pork and beef cuts, served with rice and orange slices.",
        "country": "Brazil"
    },
    {
        "id": 8,
        "name": "Moussaka",
        "imageUrl": "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=800",
        "ingredients": ["Eggplant", "Ground lamb or beef", "Tomato sauce", "Béchamel sauce", "Onion", "Garlic"],
        "description": "Layered casserole with eggplant, spiced meat sauce, and creamy béchamel topping.",
        "country": "Greece"
    },
    {
        "id": 9,
        "name": "Peking Duck",
        "imageUrl": "https://images.unsplash.com/photo-1567981164413-4491e98a8eac?w=800",
        "ingredients": ["Duck", "Maltose syrup", "Pancakes", "Cucumber", "Scallions", "Hoisin sauce"],
        "description": "Roasted duck with crispy skin, served with thin pancakes, vegetables, and sweet sauce.",
        "country": "China"
    },
    {
        "id": 10,
        "name": "Paella",
        "imageUrl": "https://images.unsplash.com/photo-1630384670686-913034e08921?w=800",
        "ingredients": ["Rice", "Saffron", "Chicken", "Seafood", "Bell peppers", "Peas", "Paprika"],
        "description": "A vibrant rice dish cooked with saffron, combining meats or seafood with vegetables.",
        "country": "Spain"
    },
    {
        "id": 11,
        "name": "Pho",
        "imageUrl": "https://images.unsplash.com/photo-1591814468924-caf88d1232e1?w=800",
        "ingredients": ["Rice noodles", "Beef or chicken broth", "Meat slices", "Bean sprouts", "Basil", "Lime", "Chili"],
        "description": "Aromatic noodle soup with a clear broth, fresh herbs, and tender meat slices.",
        "country": "Vietnam"
    },
    {
        "id": 12,
        "name": "Schnitzel",
        "imageUrl": "https://images.unsplash.com/photo-1632158140256-bdf6c1960aad?w=800",
        "ingredients": ["Veal or pork", "Flour", "Eggs", "Breadcrumbs", "Lemon"],
        "description": "Breaded and fried meat cutlet, traditionally served with lemon wedges.",
        "country": "Austria"
    }
]

ALL_COUNTRIES = list(set(dish["country"] for dish in DISHES))

def generate_options(correct_country):
    """Generate 4 multiple choice options"""
    wrong_options = [c for c in ALL_COUNTRIES if c != correct_country]
    random.shuffle(wrong_options)
    options = wrong_options[:3] + [correct_country]
    random.shuffle(options)
    return options

class FoodGuesserHandler(SimpleHTTPRequestHandler):
    def do_GET(self):
        parsed_path = urlparse(self.path)
        path = parsed_path.path

        # API endpoints
        if path == '/api/foodguesser/question':
            self.handle_question()
        elif path == '/api/foodguesser/dishes':
            self.handle_all_dishes()
        elif path.startswith('/api/foodguesser/guess'):
            # Extract query parameters
            query = parse_qs(parsed_path.query)
            dish_id = int(query.get('dishId', [0])[0])
            country = query.get('country', [''])[0]
            self.handle_guess(dish_id, country)
        else:
            # Serve static files
            if path == '/':
                self.path = '/index.html'
            super().do_GET()

    def do_POST(self):
        parsed_path = urlparse(self.path)
        if parsed_path.path.startswith('/api/foodguesser/guess'):
            query = parse_qs(parsed_path.query)
            dish_id = int(query.get('dishId', [0])[0])
            country = query.get('country', [''])[0]
            self.handle_guess(dish_id, country)

    def handle_question(self):
        """Return a random dish question"""
        dish = random.choice(DISHES)
        options = generate_options(dish["country"])

        question = {
            "id": dish["id"],
            "name": dish["name"],
            "imageUrl": dish["imageUrl"],
            "ingredients": dish["ingredients"],
            "description": dish["description"],
            "options": options
        }

        self.send_json_response(question)

    def handle_guess(self, dish_id, country):
        """Handle a guess submission"""
        dish = next((d for d in DISHES if d["id"] == dish_id), None)

        if dish is None:
            result = {
                "correct": False,
                "correctAnswer": "Unknown",
                "message": "Invalid dish ID"
            }
        else:
            is_correct = dish["country"].lower() == country.lower().strip()
            result = {
                "correct": is_correct,
                "correctAnswer": dish["country"],
                "message": "Correct! Well done!" if is_correct else f"Sorry, that's not correct. The dish is from {dish['country']}"
            }

        self.send_json_response(result)

    def handle_all_dishes(self):
        """Return all dishes"""
        self.send_json_response(DISHES)

    def send_json_response(self, data):
        """Send JSON response with CORS headers"""
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()
        self.wfile.write(json.dumps(data).encode())

    def log_message(self, format, *args):
        """Custom log format"""
        print(f"[{self.log_date_time_string()}] {format % args}")

def run_server(port=8080):
    """Run the test server"""
    # Change to static resources directory
    os.chdir('/home/user/HomeApp/src/main/resources/static')

    server_address = ('', port)
    httpd = HTTPServer(server_address, FoodGuesserHandler)

    print(f"=" * 60)
    print(f"FoodGuesser Test Server Running!")
    print(f"=" * 60)
    print(f"Server: http://localhost:{port}")
    print(f"API: http://localhost:{port}/api/foodguesser/question")
    print(f"=" * 60)
    print(f"Press Ctrl+C to stop the server")
    print(f"=" * 60)

    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        print("\nServer stopped.")

if __name__ == '__main__':
    run_server()
