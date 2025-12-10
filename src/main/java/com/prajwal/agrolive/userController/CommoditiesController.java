package com.prajwal.agrolive.userController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class CommoditiesController {

    @GetMapping("/market")
    public String getCommodityCategories(Model model) {

        Map<String, List<String>> categoryMap = getCategoryMap();

        model.addAttribute("categorizedCommodities", categoryMap);

        return "market";
    }

    private Map<String, List<String>> getCategoryMap() {

        Map<String, List<String>> map = new LinkedHashMap<>();

        // Fruits
        map.put("Fruits", Arrays.asList(
                "Nutmeg", "Tamarind Fruit", "Banana", "Kinnow", "Papaya(Raw)",
                "Apple", "Mango(Raw-Ripe)", "Mousambi(Sweet Lime)", "Pomegranate",
                "Banana - Green", "Coconut Seed", "Guava", "Pineapple", "Kiwi Fruit", "Papaya"
        ));

        // Vegetables
        map.put("Vegetables", Arrays.asList(
                "Rajgir", "Surat Beans(Papadi)", "Millets", "Methi(Leaves)", "Rat Tail Radish(Mogari)",
                "Turnip", "Snakeguard", "Potato", "Asparagus", "Mustard", "Marget", "Maize",
                "White Muesli", "Little gourd(Kundru)", "Karbuja(Musk Melon)", "Knool Khol",
                "Peas cod", "Brinjal", "Galgal(Lemon)", "Green Chilli", "Field Pea", "Green Avare(W)",
                "Ashgourd", "Tube Flower", "Sponge gourd", "Squash(Chappal Kadoo)", "Yam(Ratalu)",
                "Marigold(Calcutta)", "Water Melon", "Pumpkin", "Ridgeguard(Tori)", "Round gourd",
                "Cowpea(Veg)", "Ladies Finger (Bhindi)", "Green Peas", "French Beans(Frasbean)",
                "Indian Beans(Seam)", "Tomato", "Onion Green", "Bitter gourd", "Cauliflower",
                "Cucumbar(Kheera)", "Cowpea(Lobia/Karamani)", "Pointed gourd(Parval)", "Sweet Pumpkin"
        ));

        // Pulses / Legumes
        map.put("Pulses", Arrays.asList(
                "Kabuli Chana(Chickpeas-White)", "Masur Dal", "Moath Dal", "Black Gram(Urd Beans)(Whole)",
                "Pegeon Pea(Arhar Fali)", "Green Gram Dal(Moong Dal)", "Bengal Gram(Gram)(Whole)",
                "Peas Wet", "White Peas", "Arhar(Tur/Red Gram)(Whole)", "Other Pulses",
                "Bengal Gram Dal(Chana Dal)", "Black Gram Dal(Urd Dal)", "Lentil(Masur)(Whole)",
                "Green Gram(Moong)(Whole)"
        ));

        // Oilseeds / Seeds / Spices
        map.put("Oilseeds / Seeds / Spices", Arrays.asList(
                "Sesamum(Sesame,Gingelly,Til)", "Cummin Seed(Jeera)", "Guar Seed(Cluster Beans Seed)",
                "Cardamoms", "Nigella seeds", "Poppy seeds", "Mustard Oil", "Coriander(Leaves)",
                "Ajwan", "Methi Seeds", "Suva(Dill Seed)", "Linseed", "Neem Seed", "Coconut Oil"
        ));

        // Grains / Cereals / Millets
        map.put("Grains / Cereals / Millets", Arrays.asList(
                "Maize", "Paddy(Basmati)", "Jowar(Sorghum)", "Rice", "Bajra(Pearl Millet/Cumbu)",
                "Paddy(Common)", "Kodo Millet(Varagu)", "Barley(Jau)", "Wheat"
        ));

        // Herbs / Medicinal Plants
        map.put("Herbs / Medicinal Plants", Arrays.asList(
                "Giloy", "Asgand", "Mint(Pudina)", "Amla(Nelli Kai)", "Absinthe"
        ));

        // Flowers / Ornamentals
        map.put("Flowers / Ornamentals", Arrays.asList(
                "Rose(Local)", "Jasmine", "Chrysanthemum(Loose)", "Chrysanthemum",
                "Marigold(loose)", "Tube Rose(Loose)", "Tube Rose(Double)", "Tube Rose(Single)",
                "Carnation"
        ));

        // Dairy / Meat / Animal Products
        map.put("Dairy / Meat / Animal Products", Arrays.asList(
                "Fish", "Goat", "Cow", "She Buffalo", "Ghee", "Egg"
        ));

        // Others / Miscellaneous
        map.put("Others / Miscellaneous", Arrays.asList(
                "Dry Chillies", "Groundnut", "Groundnut pods(raw)", "Tapioca", "Sugar",
                "Dry Fodder", "Green Fodder", "Cotton", "Firewood", "Wood", "Rubber",
                "Cocoa", "Coffee", "Gur(Jaggery)"
        ));

        return map;
    }
}
