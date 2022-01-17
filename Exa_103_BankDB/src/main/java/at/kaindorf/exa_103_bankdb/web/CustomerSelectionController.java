package at.kaindorf.exa_103_bankdb.web;

import at.kaindorf.exa_103_bankdb.database.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/selection")
public class CustomerSelectionController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String getView() {
        return "customerSelectionView";
    }

    @PostMapping("/getCustomers")
    public String getCustomers(@RequestParam("filterType") String filterType) {
        System.out.println(filterType);
        return "redirect:/selection";
    }
}
