package com.prajwal.agrolive.userController;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommoditiesController {

  @GetMapping("/market")
  public String getCommodityCategories(Model model) {

    Map<String, List<String>> categoryMap = getCategoryMap();
    Map<String, String> imageUrlMap = getImageUrls();

    model.addAttribute("categorizedCommodities", categoryMap);
    model.addAttribute("imageUrls", imageUrlMap);

    return "market";
  }
private Map<String, String> getImageUrls() {
    Map<String, String> imageMap = new HashMap<>();
    
    // FRUITS - Wikimedia Commons & Free Sources
    imageMap.put("Banana", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/300px-Banana-Single.jpg");
    imageMap.put("Apple", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/300px-Red_Apple.jpg");
    imageMap.put("Mango(Raw-Ripe)", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Mangos_criollos_de_Venezuela.jpg/300px-Mangos_criollos_de_Venezuela.jpg");
    imageMap.put("Pomegranate", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Pomegranate_DSW.JPG/300px-Pomegranate_DSW.JPG");
    imageMap.put("Guava", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Psidium_guajava_fruit.jpg/300px-Psidium_guajava_fruit.jpg");
    imageMap.put("Pineapple", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Pineapple_and_cross_section.jpg/300px-Pineapple_and_cross_section.jpg");
    imageMap.put("Papaya", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Carica_papaya_-_K%C3%B6hler%E2%80%93s_Medizinal-Pflanzen-029.jpg/300px-Carica_papaya_-_K%C3%B6hler%E2%80%93s_Medizinal-Pflanzen-029.jpg");
    imageMap.put("Kiwi Fruit", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Kiwi_aka.jpg/300px-Kiwi_aka.jpg");
    imageMap.put("Coconut Seed", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Coconut_on_tree.JPG/300px-Coconut_on_tree.JPG");
    imageMap.put("Mousambi(Sweet Lime)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Citrus_limetta_sweet_lemon.jpg/300px-Citrus_limetta_sweet_lemon.jpg");
    imageMap.put("Tamarind Fruit", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Tamarind.jpg/300px-Tamarind.jpg");
    imageMap.put("Nutmeg", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Nutmeg.jpg/300px-Nutmeg.jpg");
    imageMap.put("Kinnow", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Oranges_-_whole-halved-segment.jpg/300px-Oranges_-_whole-halved-segment.jpg");
    imageMap.put("Papaya(Raw)", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Papaya_Cross_Section_BW_2.jpg/300px-Papaya_Cross_Section_BW_2.jpg");
    imageMap.put("Banana - Green", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Bananas_white_background.jpg/300px-Bananas_white_background.jpg");
    
    // VEGETABLES - Wikimedia Commons
    imageMap.put("Potato", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Patates.jpg/300px-Patates.jpg");
    imageMap.put("Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/300px-Bright_red_tomato_and_cross_section02.jpg");
    imageMap.put("Onion Green", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Spring_onions.jpg/300px-Spring_onions.jpg");
    imageMap.put("Green Chilli", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Peperoncini_piccanti.jpg/300px-Peperoncini_piccanti.jpg");
    imageMap.put("Brinjal", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Solanum_melongena_24_08_2012_%281%29.JPG/300px-Solanum_melongena_24_08_2012_%281%29.JPG");
    imageMap.put("Cauliflower", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Cauliflower.JPG/300px-Cauliflower.JPG");
    imageMap.put("Ladies Finger (Bhindi)", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Okra_aka.jpg/300px-Okra_aka.jpg");
    imageMap.put("Green Peas", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Snow_pea_pods.jpg/300px-Snow_pea_pods.jpg");
    imageMap.put("French Beans(Frasbean)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Green_beans.jpg/300px-Green_beans.jpg");
    imageMap.put("Bitter gourd", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Momordica_Charantia_Gourd.jpg/300px-Momordica_Charantia_Gourd.jpg");
    imageMap.put("Cucumbar(Kheera)", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/ARS_cucumber.jpg/300px-ARS_cucumber.jpg");
    imageMap.put("Pumpkin", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/FrenchMarketPumpkinsB.jpg/300px-FrenchMarketPumpkinsB.jpg");
    imageMap.put("Water Melon", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Taiwan_2009_Tainan_City_Organic_Farm_Watermelon_FRD_7962.jpg/300px-Taiwan_2009_Tainan_City_Organic_Farm_Watermelon_FRD_7962.jpg");
    imageMap.put("Asparagus", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Asparagus_officinalis_Bgm.jpg/300px-Asparagus_officinalis_Bgm.jpg");
    imageMap.put("Turnip", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Turnip_2622027.jpg/300px-Turnip_2622027.jpg");
    imageMap.put("Mustard", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Brassica_juncea_var._japonica.JPG/300px-Brassica_juncea_var._japonica.JPG");
    imageMap.put("Maize", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Corn_on_the_cob.jpg/300px-Corn_on_the_cob.jpg");
    imageMap.put("Ashgourd", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Winter_melon_1.jpg/300px-Winter_melon_1.jpg");
    imageMap.put("Sponge gourd", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Luffa_aegyptiaca_0003.JPG/300px-Luffa_aegyptiaca_0003.JPG");
    imageMap.put("Yam(Ratalu)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Yam.jpg/300px-Yam.jpg");
    imageMap.put("Cowpea(Veg)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Starr_070730-7862_Vigna_unguiculata.jpg/300px-Starr_070730-7862_Vigna_unguiculata.jpg");
    imageMap.put("Indian Beans(Seam)", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Lablab_purpureus_-_pods.jpg/300px-Lablab_purpureus_-_pods.jpg");
    imageMap.put("Methi(Leaves)", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Fenugreek_Plants.jpg/300px-Fenugreek_Plants.jpg");
    imageMap.put("Pointed gourd(Parval)", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Trichosanthes_dioica.jpg/300px-Trichosanthes_dioica.jpg");
    imageMap.put("Sweet Pumpkin", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Cucurbita_moschata_Butternut_2012_G2.jpg/300px-Cucurbita_moschata_Butternut_2012_G2.jpg");
    imageMap.put("Snakeguard", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Snake_Gourd.jpg/300px-Snake_Gourd.jpg");
    imageMap.put("Ridgeguard(Tori)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Luffa_acutangula_2.jpg/300px-Luffa_acutangula_2.jpg");
    imageMap.put("Round gourd", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Indian_Baby_Pumpkin.jpg/300px-Indian_Baby_Pumpkin.jpg");
    imageMap.put("Little gourd(Kundru)", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Ivy_gourd.jpg/300px-Ivy_gourd.jpg");
    imageMap.put("Karbuja(Musk Melon)", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/Cantaloupes.jpg/300px-Cantaloupes.jpg");
    imageMap.put("Knool Khol", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Kohlrabi_2.jpg/300px-Kohlrabi_2.jpg");
    imageMap.put("Peas cod", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Peas_in_pods_-_Studio.jpg/300px-Peas_in_pods_-_Studio.jpg");
    imageMap.put("Galgal(Lemon)", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Lemon-edit1.jpg/300px-Lemon-edit1.jpg");
    imageMap.put("Field Pea", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Green_Field_Peas.jpg/300px-Green_Field_Peas.jpg");
    imageMap.put("Green Avare(W)", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Hyacinth_bean_from_Lalbagh_flower_show_Aug_2013_8539.JPG/300px-Hyacinth_bean_from_Lalbagh_flower_show_Aug_2013_8539.JPG");
    imageMap.put("Tube Flower", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Banana_blossom.jpg/300px-Banana_blossom.jpg");
    imageMap.put("Squash(Chappal Kadoo)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/Summer_Squash_Basket.jpg/300px-Summer_Squash_Basket.jpg");
    imageMap.put("Cowpea(Lobia/Karamani)", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Black-eyed_pea.jpg/300px-Black-eyed_pea.jpg");
    imageMap.put("Rajgir", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Amaranthus.JPG/300px-Amaranthus.JPG");
    imageMap.put("Surat Beans(Papadi)", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Lablab_purpureus_beans.jpg/300px-Lablab_purpureus_beans.jpg");
    imageMap.put("Millets", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/00/Unprocessed_millet.jpg/300px-Unprocessed_millet.jpg");
    imageMap.put("Rat Tail Radish(Mogari)", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Radishes_with_different_peel_colours.jpg/300px-Radishes_with_different_peel_colours.jpg");
    imageMap.put("Marget", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Green_beans.jpg/300px-Green_beans.jpg");
    imageMap.put("White Muesli", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Daikon.Japan.jpg/300px-Daikon.Japan.jpg");
    imageMap.put("Marigold(Calcutta)", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Tagetes_erecta_orange.jpg/300px-Tagetes_erecta_orange.jpg");
    
    // PULSES - Wikimedia Commons
    imageMap.put("Kabuli Chana(Chickpeas-White)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Chickpea_plant_shoot.jpg/300px-Chickpea_plant_shoot.jpg");
    imageMap.put("Masur Dal", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Red_Lentils_in_bowl.jpg/300px-Red_Lentils_in_bowl.jpg");
    imageMap.put("Green Gram Dal(Moong Dal)", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Mung_bean_-_Vigna_radiata.jpg/300px-Mung_bean_-_Vigna_radiata.jpg");
    imageMap.put("Bengal Gram Dal(Chana Dal)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Chana_dal.JPG/300px-Chana_dal.JPG");
    imageMap.put("Black Gram Dal(Urd Dal)", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Urad_dal.jpg/300px-Urad_dal.jpg");
    imageMap.put("Arhar(Tur/Red Gram)(Whole)", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Pigeon_pea.jpg/300px-Pigeon_pea.jpg");
    imageMap.put("Bengal Gram(Gram)(Whole)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Chickpeas_in_bowl.jpg/300px-Chickpeas_in_bowl.jpg");
    imageMap.put("Green Gram(Moong)(Whole)", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Mung-Bohne_200405.jpg/300px-Mung-Bohne_200405.jpg");
    imageMap.put("Black Gram(Urd Beans)(Whole)", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Vigna_mungo.jpg/300px-Vigna_mungo.jpg");
    imageMap.put("Lentil(Masur)(Whole)", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Lens_culinaris_001.JPG/300px-Lens_culinaris_001.JPG");
    imageMap.put("Pegeon Pea(Arhar Fali)", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Pigeon_pea_flowers.jpg/300px-Pigeon_pea_flowers.jpg");
    imageMap.put("Peas Wet", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Garden_peas_in_pod.jpg/300px-Garden_peas_in_pod.jpg");
    imageMap.put("White Peas", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Peas_in_pods.jpg/300px-Peas_in_pods.jpg");
    imageMap.put("Moath Dal", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Vigna_aconitifolia_Moth_bean.jpg/300px-Vigna_aconitifolia_Moth_bean.jpg");
    imageMap.put("Other Pulses", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Haricot_blanc.jpg/300px-Haricot_blanc.jpg");
    
    // OILSEEDS / SEEDS / SPICES - Wikimedia Commons
    imageMap.put("Sesamum(Sesame,Gingelly,Til)", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/Sesame_seeds.jpg/300px-Sesame_seeds.jpg");
    imageMap.put("Cummin Seed(Jeera)", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Cumin_seeds.JPG/300px-Cumin_seeds.JPG");
    imageMap.put("Cardamoms", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Cardomom.jpg/300px-Cardomom.jpg");
    imageMap.put("Mustard Oil", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Bottle_of_mustard_oil.jpg/300px-Bottle_of_mustard_oil.jpg");
    imageMap.put("Coriander(Leaves)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/A_scene_of_Coriander_leaves.JPG/300px-A_scene_of_Coriander_leaves.JPG");
    imageMap.put("Ajwan", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Ajwain_aka.jpg/300px-Ajwain_aka.jpg");
    imageMap.put("Methi Seeds", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Fenugreek_seeds.jpg/300px-Fenugreek_seeds.jpg");
    imageMap.put("Linseed", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Flax_seeds.jpg/300px-Flax_seeds.jpg");
    imageMap.put("Coconut Oil", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/NCI_coconut_oil.jpg/300px-NCI_coconut_oil.jpg");
    imageMap.put("Guar Seed(Cluster Beans Seed)", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Cyamopsis_tetragonoloba_seed.JPG/300px-Cyamopsis_tetragonoloba_seed.JPG");
    imageMap.put("Nigella seeds", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Nigella_sativa_seeds.jpg/300px-Nigella_sativa_seeds.jpg");
    imageMap.put("Poppy seeds", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Poppy_seeds.jpg/300px-Poppy_seeds.jpg");
    imageMap.put("Suva(Dill Seed)", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f5/Dill_seeds.jpg/300px-Dill_seeds.jpg");
    imageMap.put("Neem Seed", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Azadirachta_indica_seeds.jpg/300px-Azadirachta_indica_seeds.jpg");
    
    // GRAINS / CEREALS / MILLETS - Wikimedia Commons
    imageMap.put("Rice", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Rice_p1160004.jpg/300px-Rice_p1160004.jpg");
    imageMap.put("Wheat", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Wheat_close-up.JPG/300px-Wheat_close-up.JPG");
    imageMap.put("Bajra(Pearl Millet/Cumbu)", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Pearl_millet.jpg/300px-Pearl_millet.jpg");
    imageMap.put("Jowar(Sorghum)", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Sorghum_bicolor_02.jpg/300px-Sorghum_bicolor_02.jpg");
    imageMap.put("Paddy(Basmati)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Basmatireis.jpg/300px-Basmatireis.jpg");
    imageMap.put("Paddy(Common)", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Unpolished_rice_of_different_types.jpg/300px-Unpolished_rice_of_different_types.jpg");
    imageMap.put("Barley(Jau)", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Barley_grain.jpg/300px-Barley_grain.jpg");
    imageMap.put("Kodo Millet(Varagu)", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Kodo_Millet_Grains.jpg/300px-Kodo_Millet_Grains.jpg");
    
    // HERBS / MEDICINAL PLANTS - Wikimedia Commons
    imageMap.put("Mint(Pudina)", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Mint-leaves-2007.jpg/300px-Mint-leaves-2007.jpg");
    imageMap.put("Amla(Nelli Kai)", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Phyllanthus_emblica_fruit.jpg/300px-Phyllanthus_emblica_fruit.jpg");
    imageMap.put("Giloy", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Tinospora_cordifolia.jpg/300px-Tinospora_cordifolia.jpg");
    imageMap.put("Asgand", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Withania_somnifera_002.JPG/300px-Withania_somnifera_002.JPG");
    imageMap.put("Absinthe", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Artemisia_absinthium_002.JPG/300px-Artemisia_absinthium_002.JPG");
    
    // FLOWERS - Wikimedia Commons
    imageMap.put("Rose(Local)", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Rosa_sp.160.jpg/300px-Rosa_sp.160.jpg");
    imageMap.put("Jasmine", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Jasminum_officinale_flowers.jpg/300px-Jasminum_officinale_flowers.jpg");
    imageMap.put("Chrysanthemum", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Chrysanthemum_November_2007_Osaka_Japan.jpg/300px-Chrysanthemum_November_2007_Osaka_Japan.jpg");
    imageMap.put("Chrysanthemum(Loose)", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Chrysanthemum_cultivar_0438.jpg/300px-Chrysanthemum_cultivar_0438.jpg");
    imageMap.put("Marigold(loose)", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Tagetes_erecta_orange.jpg/300px-Tagetes_erecta_orange.jpg");
    imageMap.put("Tube Rose(Loose)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Polianthes_tuberosa_2.jpg/300px-Polianthes_tuberosa_2.jpg");
    imageMap.put("Tube Rose(Double)", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Polianthes_tuberosa_6.jpg/300px-Polianthes_tuberosa_6.jpg");
    imageMap.put("Tube Rose(Single)", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Polianthes_tuberosa_2.jpg/300px-Polianthes_tuberosa_2.jpg");
    imageMap.put("Carnation", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Dianthus_caryophyllus_-_red_cultivar_%28aka%29.jpg/300px-Dianthus_caryophyllus_-_red_cultivar_%28aka%29.jpg");
    
    // DAIRY / MEAT / ANIMAL PRODUCTS - Wikimedia Commons
    imageMap.put("Fish", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Fishmarket_03.jpg/300px-Fishmarket_03.jpg");
    imageMap.put("Goat", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Hausziege_04.jpg/300px-Hausziege_04.jpg");
    imageMap.put("Cow", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Cow_female_black_white.jpg/300px-Cow_female_black_white.jpg");
    imageMap.put("She Buffalo", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Water_Buffalo_bathing.jpg/300px-Water_Buffalo_bathing.jpg");
    imageMap.put("Ghee", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Ghee.jpg/300px-Ghee.jpg");
    imageMap.put("Egg", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Chicken_eggs_in_basket_2020_G2.jpg/300px-Chicken_eggs_in_basket_2020_G2.jpg");
    
    // OTHERS / MISCELLANEOUS - Wikimedia Commons
    imageMap.put("Dry Chillies", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Dried_Chilies.jpg/300px-Dried_Chilies.jpg");
    imageMap.put("Groundnut", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Peanut_9417.jpg/300px-Peanut_9417.jpg");
    imageMap.put("Groundnut pods(raw)", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Peanuts_in_shells.jpg/300px-Peanuts_in_shells.jpg");
    imageMap.put("Tapioca", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Tapioca_roots.JPG/300px-Tapioca_roots.JPG");
    imageMap.put("Sugar", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/White_sugar_cubes.JPG/300px-White_sugar_cubes.JPG");
    imageMap.put("Cotton", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/CottonPlant.JPG/300px-CottonPlant.JPG");
    imageMap.put("Rubber", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Latex_being_collected_from_a_tapped_rubber_tree.jpg/300px-Latex_being_collected_from_a_tapped_rubber_tree.jpg");
    imageMap.put("Cocoa", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Cacao-pod-k4636-14.jpg/300px-Cacao-pod-k4636-14.jpg");
    imageMap.put("Coffee", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Roasted_coffee_beans.jpg/300px-Roasted_coffee_beans.jpg");
    imageMap.put("Gur(Jaggery)", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Jaggery_kerala.jpg/300px-Jaggery_kerala.jpg");
    imageMap.put("Dry Fodder", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Hay_bales_in_Washington.jpg/300px-Hay_bales_in_Washington.jpg");
    
    return imageMap;
  }

  private Map<String, List<String>> getCategoryMap() {

    Map<String, List<String>> map = new LinkedHashMap<>();

    // Fruits
    map.put(
        "Fruits",
        Arrays.asList(
            "Nutmeg",
            "Tamarind Fruit",
            "Banana",
            "Kinnow",
            "Papaya(Raw)",
            "Apple",
            "Mango(Raw-Ripe)",
            "Mousambi(Sweet Lime)",
            "Pomegranate",
            "Banana - Green",
            "Coconut Seed",
            "Guava",
            "Pineapple",
            "Kiwi Fruit",
            "Papaya"));

    // Vegetables
    map.put(
        "Vegetables",
        Arrays.asList(
            "Rajgir",
            "Surat Beans(Papadi)",
            "Millets",
            "Methi(Leaves)",
            "Rat Tail Radish(Mogari)",
            "Turnip",
            "Snakeguard",
            "Potato",
            "Asparagus",
            "Mustard",
            "Marget",
            "Maize",
            "White Muesli",
            "Little gourd(Kundru)",
            "Karbuja(Musk Melon)",
            "Knool Khol",
            "Peas cod",
            "Brinjal",
            "Galgal(Lemon)",
            "Green Chilli",
            "Field Pea",
            "Green Avare(W)",
            "Ashgourd",
            "Tube Flower",
            "Sponge gourd",
            "Squash(Chappal Kadoo)",
            "Yam(Ratalu)",
            "Marigold(Calcutta)",
            "Water Melon",
            "Pumpkin",
            "Ridgeguard(Tori)",
            "Round gourd",
            "Cowpea(Veg)",
            "Ladies Finger (Bhindi)",
            "Green Peas",
            "French Beans(Frasbean)",
            "Indian Beans(Seam)",
            "Tomato",
            "Onion Green",
            "Bitter gourd",
            "Cauliflower",
            "Cucumbar(Kheera)",
            "Cowpea(Lobia/Karamani)",
            "Pointed gourd(Parval)",
            "Sweet Pumpkin"));


    // Pulses / Legumes
    map.put(
        "Pulses",
        Arrays.asList(
            "Kabuli Chana(Chickpeas-White)",
            "Masur Dal",
            "Moath Dal",
            "Black Gram(Urd Beans)(Whole)",
            "Pegeon Pea(Arhar Fali)",
            "Green Gram Dal(Moong Dal)",
            "Bengal Gram(Gram)(Whole)",
            "Peas Wet",
            "White Peas",
            "Arhar(Tur/Red Gram)(Whole)",
            "Other Pulses",
            "Bengal Gram Dal(Chana Dal)",
            "Black Gram Dal(Urd Dal)",
            "Lentil(Masur)(Whole)",
            "Green Gram(Moong)(Whole)"));

    // Oilseeds / Seeds / Spices
    map.put(
        "Oilseeds / Seeds / Spices",
        Arrays.asList(
            "Sesamum(Sesame,Gingelly,Til)",
            "Cummin Seed(Jeera)",
            "Guar Seed(Cluster Beans Seed)",
            "Cardamoms",
            "Nigella seeds",
            "Poppy seeds",
            "Mustard Oil",
            "Coriander(Leaves)",
            "Ajwan",
            "Methi Seeds",
            "Suva(Dill Seed)",
            "Linseed",
            "Neem Seed",
            "Coconut Oil"));

    // Grains / Cereals / Millets
    map.put(
        "Grains / Cereals / Millets",
        Arrays.asList(
            "Maize",
            "Paddy(Basmati)",
            "Jowar(Sorghum)",
            "Rice",
            "Bajra(Pearl Millet/Cumbu)",
            "Paddy(Common)",
            "Kodo Millet(Varagu)",
            "Barley(Jau)",
            "Wheat"));

    // Herbs / Medicinal Plants
    map.put(
        "Herbs / Medicinal Plants",
        Arrays.asList("Giloy", "Asgand", "Mint(Pudina)", "Amla(Nelli Kai)", "Absinthe"));

    // Flowers / Ornamentals
    map.put(
        "Flowers / Ornamentals",
        Arrays.asList(
            "Rose(Local)",
            "Jasmine",
            "Chrysanthemum(Loose)",
            "Chrysanthemum",
            "Marigold(loose)",
            "Tube Rose(Loose)",
            "Tube Rose(Double)",
            "Tube Rose(Single)",
            "Carnation"));

    // Dairy / Meat / Animal Products
    map.put(
        "Dairy / Meat / Animal Products",
        Arrays.asList("Fish", "Goat", "Cow", "She Buffalo", "Ghee", "Egg"));

    // Others / Miscellaneous
    map.put(
        "Others / Miscellaneous",
        Arrays.asList(
            "Dry Chillies",
            "Groundnut",
            "Groundnut pods(raw)",
            "Tapioca",
            "Sugar",
            "Dry Fodder",
            "Green Fodder",
            "Cotton",
            "Firewood",
            "Wood",
            "Rubber",
            "Cocoa",
            "Coffee",
            "Gur(Jaggery)"));

    return map;
  }
}