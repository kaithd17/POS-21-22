package at.kaindorf.exa_103_bankdb.web;

import at.kaindorf.exa_103_bankdb.database.AccountRepository;
import at.kaindorf.exa_103_bankdb.database.CustomerRepository;
import at.kaindorf.exa_103_bankdb.database.GiroAccountRepository;
import at.kaindorf.exa_103_bankdb.database.SavingAccountRepository;
import at.kaindorf.exa_103_bankdb.pojos.Account;
import at.kaindorf.exa_103_bankdb.pojos.Customer;
import at.kaindorf.exa_103_bankdb.pojos.GiroAccount;
import at.kaindorf.exa_103_bankdb.pojos.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/overview")
public class CustomerDetailsController {

    @Autowired
    GiroAccountRepository giroAccountRepository;

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/details")
    private String customerOverview (@SessionAttribute("customerId") String customerId, Model model) {
        Long id = Long.parseLong(customerId);
        Customer customer = customerRepository.findCustomerByCustomerId(id);
        setAttributes(id, model, customer);
        return "customerDetailsView";
    }

    @PostMapping("/changeSavingAccount")
    private String changeSavingAccount(@RequestParam("buttonText") String buttonText, @SessionAttribute("customerId") String customerId, @RequestParam("savingAmount") Double amount, Model model) {
        Long id = Long.parseLong(customerId);
        Long aId = 0L;
        Double balance = 0.0;
        Customer customer = customerRepository.findById(id).get();

        if (buttonText.charAt(0) != '-') {
            aId = Long.parseLong(buttonText);
            final Long finalId = aId;
            System.out.println(finalId);
            balance = customer.getAccounts().stream().filter(account -> account.getAccountId().equals(finalId)).findFirst().get().getBalance();
            balance += amount;
            System.out.println(balance);
        } else {
            aId = Long.parseLong(buttonText);
            aId *= (-1);
            final Long finalId = aId;
            balance = customer.getAccounts().stream().filter(account -> account.getAccountId().equals(finalId)).findFirst().get().getBalance();
            balance -= amount;
        }
        final Long finalId = aId;
        customer.getAccounts().stream().filter(account -> account.getAccountId().equals(finalId)).findFirst().get().setBalance(balance);
        customerRepository.save(customer);
        setAttributes(id, model, customer);

        return "customerDetailsView";
    }

    public void setAttributes(Long id, Model model, Customer customer) {
        List<GiroAccount> giroAccounts = giroAccountRepository.getGiroAccounts(id);
        List<SavingAccount> savingAccounts = savingAccountRepository.getSavingsAccounts(id);
        Double totalBalance = accountRepository.getBalance(id);

        model.addAttribute("customer", customer);
        model.addAttribute("giroAccounts", giroAccounts);
        model.addAttribute("savingsAccounts", savingAccounts);
        model.addAttribute("totalBalance", totalBalance);
        model.addAttribute("customerId", customer.getCustomerId());
    }
}
