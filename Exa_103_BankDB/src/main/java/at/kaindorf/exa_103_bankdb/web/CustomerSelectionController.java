package at.kaindorf.exa_103_bankdb.web;

import at.kaindorf.exa_103_bankdb.database.CustomerRepository;
import at.kaindorf.exa_103_bankdb.pojos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/selection")
@SessionAttributes({"searchPattern", "customerId"})
public class CustomerSelectionController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String getView() {
        return "customerSelectionView";
    }

    @PostMapping("/getCustomers")
    public String getCustomers(@RequestParam("filterType") @SessionAttribute("searchPattern") String filterType, Model model) {
        List<Customer> customerList = customerRepository.findCustomerByLastname(filterType);
        System.out.println(customerList);
        model.addAttribute("searchPattern", filterType);
        model.addAttribute("customerList", customerList);
        return "customerSelectionView";
    }

    @PostMapping("/showCustomerOverview")
    public String showCustomerOverview(@RequestParam("customerId") @SessionAttribute("customerId") String customerId, @SessionAttribute("searchPattern") String filterType, Model model) {
        System.out.println(customerId);
        model.addAttribute("customerId", customerId);
        List<Customer> customerList = customerRepository.findCustomerByLastname(filterType);
        model.addAttribute("searchPattern", filterType);
        model.addAttribute("customerList", customerList);
        return "redirect:/overview/details";
    }
}
