package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.pojos.Burger;
import at.kaindorf.springburger.pojos.Ingredient;
import at.kaindorf.springburger.repo.BurgerRepository;
import at.kaindorf.springburger.repo.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<Ingredient> ingredients;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private BurgerRepository burgerRepository;


    //Die Methode wird vor den get und postMapping Methoden aufgerufen
    @ModelAttribute
    public void addAttributes(Model model){
        ingredients = ingredientRepository.findAll();
        //Model ist der Transporter zwischen Controller und Thymeleaf
        Map<String, List<Ingredient>> ingredientsMap = new HashMap<>();
        for(Ingredient.Type type : Ingredient.Type.values()){
            ingredientsMap.put(type.name().toLowerCase(Locale.ROOT), filterByType(type));
        }

        model.addAttribute("ingredients", ingredientsMap);
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
        burgerRepository.save(burger);
        return "redirect:/orders/current";
    }

    @GetMapping
    public String showDesign(){
        return "designForm";
    }
}
