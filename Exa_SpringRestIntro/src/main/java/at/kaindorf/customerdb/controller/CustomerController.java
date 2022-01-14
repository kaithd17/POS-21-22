package at.kaindorf.customerdb.controller;

import at.kaindorf.customerdb.data.CustomerRepository;
import at.kaindorf.customerdb.pojos.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.of(customerRepository.findById(id));
//        Optional<Customer> custOpt = customerRepository.findById(id);
//        if (custOpt.isPresent()) {
//            return ResponseEntity.ok(custOpt.get());
//        }
//        return ResponseEntity.notFound().build();
        //return customerRepository.findById(373L).get();
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers(@RequestParam("page") int pageNo, @RequestParam("size") int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by("lastname").descending());
        return ResponseEntity.of(Optional.of(customerRepository.findAll(page)));
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            customer = customerRepository.save(customer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customer.getCustomerId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
