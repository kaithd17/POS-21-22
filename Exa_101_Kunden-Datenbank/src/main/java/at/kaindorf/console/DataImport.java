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
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void open(){
        emf = Persistence.createEntityManagerFactory("PU_Kunden_Datenbank");
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
        emf.close();
    }

    public static List<Customer> importJSON() {
        List<Customer> jsonCustomers = new ArrayList<>();
        try {
            jsonCustomers = new ObjectMapper().readValue(new File(JSON_PATH.toString()), new TypeReference<List<Customer>>() { });
            em.getTransaction().begin();
            //Get data and persist objects
            jsonCustomers.stream().forEach(customer -> {
                em.persist(customer);
            });
            em.getTransaction().commit();
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

        em.getTransaction().begin();
        //Get data and persist objects
        customerList.stream().forEach(customer -> {
            em.persist(customer);
        });
        em.getTransaction().commit();
        return customerList;
    }

    public static void customerMenuList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the customer database: ");
        System.out.println("[1] Import JSON");
        System.out.println("[2] Import XML");
        System.out.println("[1] Find Country By Name");
        System.out.println("[2] Find all countries");
        System.out.println("[3] Count all countries");
        System.out.println("[4] Count all addresses");
        System.out.println("[5] Get all years of customers");
        System.out.println("[6] Count all customers");
        System.out.println("[7] Find customers from country");
        System.out.println("[8] Exit");

    }

    public static void main(String[] args) {




    }
}
