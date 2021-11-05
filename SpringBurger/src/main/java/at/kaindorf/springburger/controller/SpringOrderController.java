package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.pojos.Burger;
import at.kaindorf.springburger.pojos.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class SpringOrderController {

    @GetMapping("/current")
    public String requestOrder(Model model, @SessionAttribute Burger designBurger) {
        log.info("Your burger " + designBurger);
        model.addAttribute("designBurger", designBurger);
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String performOrder(@Valid @ModelAttribute("order") Order order, Errors errors) {
        log.info("Order from " + order);
        if (errors.hasErrors()){
            log.info(errors.getObjectName() + " " + errors.getAllErrors());
            return "orderForm";
        }
        return "redirect:/design";
    }
}
