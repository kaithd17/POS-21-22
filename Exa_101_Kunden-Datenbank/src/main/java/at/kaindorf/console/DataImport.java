package at.kaindorf.console;

import at.kaindorf.pojos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataImport {
    private static final Path JSON_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "customers.json");
    private static final Path XML_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "customers.xml");

    public static List<Customer> importJSON() {
        List<Customer> jsonCustomers = new ArrayList<>();
        try {
            jsonCustomers = new ObjectMapper().readValue(new File(JSON_PATH.toString()), new TypeReference<List<Customer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonCustomers;
    }

    public static List<Customer> importXML() {
        XMLCustomerList xmlCustomerList = JAXB.unmarshal(XML_PATH.toString(), XMLCustomerList.class);
        List<XMLCustomer> xmlCustomers = xmlCustomerList.getXmlCustomerList();

        List<Customer> customerList = new ArrayList<>();
        //Convert XMLCustomers to normal Customers
        xmlCustomers.stream().forEach(xmlCustomer -> {
            customerList.add(convertToCustomer(xmlCustomer));
        });
        return customerList;
    }

    public static Customer convertToCustomer(XMLCustomer xmlCustomer) {
        Set<Country> countrySet = new HashSet<>();
        Set<Address> addressSet = new HashSet<>();

        //Create real country object and add object to countrySet
        Country country = new Country(xmlCustomer.getCountry(), xmlCustomer.getCountry_code());
        countrySet.add(country);

        //Create real address object and add object to addressSet
        Address address = new Address(
                xmlCustomer.getStreetname(),
                xmlCustomer.getStreetnumber(),
                xmlCustomer.getPostal_code(),
                xmlCustomer.getCity(),
                countrySet.stream().filter(country1 -> country1.equals(country)).findFirst().get());
        addressSet.add(address);

        //Add address to countryList
        country.getAddresses().add(addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

        //Create Customer object
        Customer customer = new Customer(
                xmlCustomer.getFirstname(),
                xmlCustomer.getLastname(),
                xmlCustomer.getGender().charAt(0),
                xmlCustomer.isActive(),
                xmlCustomer.getEmail(),
                xmlCustomer.getSince(),
                addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

        //Add customer object to the corresponding address object
        addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get().getCustomers().add(customer);

        return customer;
    }

    public static void main(String[] args) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Kunden_Datenbank");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();*/
        List<Customer> customerList = importXML();
        System.out.println(customerList);
    }
}
