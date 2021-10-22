package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.pojos.Burger;
import at.kaindorf.springburger.pojos.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j //Debug
@RequestMapping("/design")
public class SpringBurgerController {

    private List<Ingredient> ingredientList = Arrays.asList(
        new Ingredient("120B", "120g Ground Beef", Ingredient.Type.PATTY),
        new Ingredient("160B", "160g Ground Beef", Ingredient.Type.PATTY),
        new Ingredient("140T", "140g Turkey", Ingredient.Type.PATTY),
        new Ingredient("TOMA", "Tomatoe", Ingredient.Type.VEGGIE),
        new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE),
        new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),
        new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
        new Ingredient("GAUD", "Gauda", Ingredient.Type.CHEESE)
    );

    @ModelAttribute //Transporterklasse
    public void addAttribute(Model model) {
        Map<String, List<Ingredient>> ingredients = new HashMap<>();
        for (Ingredient.Type ingredient : Ingredient.Type.values()) {
            ingredients.put(ingredient.name().toLowerCase(), filterByType(ingredient));
        }
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("designBurger", new Burger());
    }

    private List<Ingredient> filterByType(Ingredient.Type type) {
        return ingredientList.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    @GetMapping
    public String showDesign() {
        return "designForm";
    }

    @PostMapping
    public String processBurger(@ModelAttribute("designBurger") Burger burger) {
        log.info("Processing burger: " + burger.toString());
        return "designForm";
    }
}
