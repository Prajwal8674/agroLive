package com.prajwal.agrolive.userController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class CommoditiesController {

    @GetMapping("/commodities")
    public String getCommodityCategories(Model model) {

        Map<String, List<String>> categoryMap = getCategoryMap();

        model.addAttribute("categorizedCommodities", categoryMap);

        return "commodities";  // loads onlyCommodities.html
    }

    // FINAL CATEGORY MAP
    private Map<String, List<String>> getCategoryMap() {

        Map<String, List<String>> map = new LinkedHashMap<>();

        map.put("Foodgrains & Cereals", Arrays.asList(
                "Paddy", "Rice", "Wheat", "Jowar", "Bajra",
                "Maize", "Ragi", "Barley", "Korra", "Sama",
                "Varagu", "Broken Rice", "Wheat Atta", "Suji",
                "Maida", "Dalia", "Macaroni", "Vermicelli"
        ));

        map.put("Pulses", Arrays.asList(
                "Red Gram", "Green Gram", "Black Gram",
                "Bengal Gram", "Lentil", "Peas", "Moth",
                "Kabuli Chana", "Horse Gram"
        ));

        map.put("Oilseeds", Arrays.asList(
                "Groundnut", "Sesamum Seeds", "Mustard Seeds",
                "Sunflower Seed", "Soybean", "Castor Seed",
                "Linseed", "Rapeseed", "Niger Seed",
                "Safflower", "Kusum seed", "Pongam seeds"
        ));

        map.put("Edible Oils", Arrays.asList(
                "Mustard Oil", "Soybean Oil", "Sunflower Oil",
                "Groundnut Oil", "Cottonseed Oil", "Rice Bran Oil",
                "Sesame Oil", "Palm Oil", "Castor Oil", "Linseed Oil"
        ));

        map.put("Oil Cakes", Arrays.asList(
                "Groundnut Cake", "Mustard Cake", "Soybean Cake",
                "Cottonseed Cake", "Sunflower Cake",
                "Castor Cake", "Linseed Cake", "Rapeseed Cake"
        ));

        map.put("Vegetables", Arrays.asList(
                "Potato", "Onions", "Tomatoes", "Cabbage",
                "Cauliflower", "Capsicum", "Brinjal", "Beans",
                "Pumpkin", "Carrot", "Spinach", "Coriander Leaves",
                "Fenugreek Leaves", "Curry Leaves", "Drumsticks",
                "Green Chilli", "Red Chilli", "Bottle Gourd",
                "Bitter Gourd", "Snake Gourd"
        ));

        map.put("Fruits", Arrays.asList(
                "Apple", "Banana", "Grapes", "Mango",
                "Orange", "Sweet Lime", "Lemon", "Papaya",
                "Custard Apple", "Pear", "Plums",
                "Watermelon", "Melons", "Amla", "Litchi",
                "Pineapple", "Cherries", "Grapefruit"
        ));

        map.put("Spices & Condiments", Arrays.asList(
                "Turmeric", "Cardamom", "Pepper", "Clove",
                "Cumin", "Coriander", "Fenugreek", "Nutmeg",
                "Saffron", "Garlic", "Ginger",
                "Chillies", "Seedless Tamarind"
        ));

        map.put("Dry Fruits & Nuts", Arrays.asList(
                "Cashew Kernels", "Walnuts", "Arecanuts",
                "Ball Copra", "Cup Copra", "Dry Coconut"
        ));

        map.put("Fiber Crops", Arrays.asList(
                "Cotton", "Jute", "Hemp", "Kenaf", "Flax"
        ));

        map.put("Dairy & Poultry", Arrays.asList(
                "Milk", "Eggs", "Poultry Meat", "Honey"
        ));

        map.put("Other Products", Arrays.asList(
                "Gur (Jaggery)", "Sugar", "Tea", "Coffee",
                "Tamarind", "Gum / Resin", "Coconut"
        ));

        return map;
    }
    
    
}
