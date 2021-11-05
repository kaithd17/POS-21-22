package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.pojos.Burger;
import at.kaindorf.springburger.pojos.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

//@Controller man benötigt eine Datei, welche zurückgeschickt wird
//@RestController schickt json oder xml Daten zurück
@Controller
@Slf4j //Logged direkt auf die Spring-Konsole
@RequestMapping("design")
@SessionAttributes("designBurger")
public class SpringBurgerController {

    private List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("120B", "120g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("160B", "160g Ground Beef", Ingredient.Type.PATTY),
            new Ingredient("140B", "140g Turkey", Ingredient.Type.PATTY),
            new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIE),
            new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE),
            new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),
            new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            new Ingredient("GOUD", "Gouda", Ingredient.Type.CHEESE)
    );

    //Die Methode wird vor den get und postMapping Methoden aufgerufen
    @ModelAttribute
    public void addAttributes(Model model){
        //Model ist der Transporter zwischen Controller und Thymeleaf
        Map<String, List<Ingredient>> ingredients = new HashMap<>();
        for(Ingredient.Type ingredient : Ingredient.Type.values()){
            ingredients.put(ingredient.name().toLowerCase(Locale.ROOT), filterByType(ingredient));
        }

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("designBurger", new Burger());
    }

    private List<Ingredient> filterByType(Ingredient.Type type){
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processBurger(@Valid @ModelAttribute("designBurger") Burger burger, Errors errors){
        log.info("Processing burger: " + burger.toString());
        if (errors.hasErrors()){
            log.info(errors.getObjectName() + " " + errors.getAllErrors());
            return "designForm";
        }
        return "redirect:/orders/current";
    }

    @GetMapping
    public String showDesign(){
        return "designForm";
    }
}
