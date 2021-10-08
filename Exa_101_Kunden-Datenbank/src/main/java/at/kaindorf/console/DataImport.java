package at.kaindorf.console;

import at.kaindorf.pojos.*;
import at.kaindorf.xml.XMLAccess;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
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

    public static void importJSON() {
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
    }

    public static void importXML() {
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
    }

    public static int customerMenuList(Scanner scanner){
        System.out.println("[1] Find Country By Name");
        System.out.println("[2] Find all countries");
        System.out.println("[3] Count all countries");
        System.out.println("[4] Count all addresses");
        System.out.println("[5] Get all years of customers");
        System.out.println("[6] Count all customers");
        System.out.println("[7] Find customers from country");
        System.out.println("[8] Exit");
        System.out.println("What do you want to do? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void importMenu(Scanner scanner){
        boolean valid = false;
        //Menu
        System.out.println("Welcome to the customer database: ");
        while(true){
            try{
                System.out.println("[1] Import JSON");
                System.out.println("[2] Import XML");
                System.out.print("Please import data: ");
                int input = Integer.parseInt(scanner.nextLine());
                switch (input){
                    case 1:
                        importJSON();
                        valid = true;
                        break;

                    case 2:
                        importXML();
                        valid = true;
                        break;

                    default:
                        System.out.println("invalid input");
                }
            }catch (NumberFormatException exception){
                System.out.println("invalid input");
            }

            if (valid){
                //Get amount of countries
                Query countCountries = em.createNamedQuery("Country.countAll");
                Long amountOfCountries = (Long) countCountries.getSingleResult();
                System.out.println("Countries imported: " + amountOfCountries);

                //Get amount of addresses
                Query countAddresses = em.createNamedQuery("Address.countAll");
                Long amountOfAddresses = (Long) countAddresses.getSingleResult();
                System.out.println("Addresses imported: " + amountOfAddresses);

                //Get amount of customers
                Query countCustomers = em.createNamedQuery("Customer.countAll");
                Long amountOfCustomers = (Long) countAddresses.getSingleResult();
                System.out.println("Customers imported: " + amountOfCustomers);
                return;
            }

        }
    }

    public static void main(String[] args) {
        //open database connection
        open();
        //Scanner variable
        Scanner scanner = new Scanner(System.in);

        //Methods
        importMenu(scanner);

        //Close database connection
        close();
    }
}
