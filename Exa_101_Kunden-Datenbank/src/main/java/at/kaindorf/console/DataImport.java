package at.kaindorf.console;

import at.kaindorf.pojos.*;
import at.kaindorf.xml.XMLAccess;
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
import java.util.*;

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
            customerList.add(XMLAccess.convertToCustomer(xmlCustomer));
        });
        return customerList;
    }

    public static void customerMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the customer database: ");
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Kunden_Datenbank");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        //Get data and persist objects
        List<Customer> customerList = importJSON();
        customerList.stream().forEach(customer -> {
            em.persist(customer);
        });

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println(customerList);
    }
}
