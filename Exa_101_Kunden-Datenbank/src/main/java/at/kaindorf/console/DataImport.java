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

    public static void customerMenuList(Scanner scanner){
        int input = 0;
        while(true){
            System.out.println("\n[1] Find Country By Name");
            System.out.println("[2] Find all countries");
            System.out.println("[3] Count all countries");
            System.out.println("[4] Count all addresses");
            System.out.println("[5] Get all years of customers");
            System.out.println("[6] Count all customers");
            System.out.println("[7] Find customers from country");
            System.out.println("[8] Exit");
            System.out.print("What do you want to do? ");
            try{
                input = Integer.parseInt(scanner.nextLine());
                switch (input){
                    case 1:
                        System.out.print("Name of Country: ");
                        String countryName = scanner.nextLine();
                        TypedQuery<Country> typedQuery = em.createNamedQuery("Country.findByName", Country.class);
                        typedQuery.setParameter("name", countryName);
                        try{
                            Country country = typedQuery.getSingleResult();
                            System.out.println("\nCountry:");
                            System.out.println(country);
                        }catch (NoResultException ex){
                            System.out.println("Could not find any Country!");
                        }
                        break;

                    case 2:
                        TypedQuery<Country> typedQuery2 = em.createNamedQuery("Country.findAll", Country.class);
                        List<Country> countries = typedQuery2.getResultList();
                        System.out.println("\nCountries:");
                        countries.forEach(System.out::println);
                        break;

                    case 3:
                        Query countCountries = em.createNamedQuery("Country.countAll");
                        Long amountOfCountries = (Long) countCountries.getSingleResult();
                        System.out.println("\nAmount of countries: " + amountOfCountries);
                        break;

                    case 4:
                        Query countAddresses = em.createNamedQuery("Address.countAll");
                        Long amountOfAddresses = (Long) countAddresses.getSingleResult();
                        System.out.println("\nAmount of addresses: " + amountOfAddresses);
                        break;

                    case 5:
                        TypedQuery<Number> customerTypedQuery = em.createNamedQuery("Customer.findYears", Number.class);
                        List<Number> years = customerTypedQuery.getResultList();
                        System.out.println("\nYears:");
                        years.forEach(System.out::println);
                        break;

                    case 6:
                        Query countCustomers = em.createNamedQuery("Customer.countAll");
                        Long amountOfCustomers = (Long) countCustomers.getSingleResult();
                        System.out.println("\nAmount of Customers: " + amountOfCustomers);
                        break;

                    case 7:
                        System.out.print("Enter name of Country: ");
                        String countryName2 = scanner.nextLine();
                        TypedQuery<Customer> customerTypedQuery1 = em.createNamedQuery("Customer.findFromCountry", Customer.class);
                        customerTypedQuery1.setParameter("name", countryName2);
                        try{
                            List<Customer> customers = customerTypedQuery1.getResultList();
                            System.out.println("\nCustomers:");
                            customers.forEach(System.out::println);
                        }catch (NoResultException ex){
                            System.out.println("Could not find any Customers!");
                        }
                        break;

                    case 8:
                        return;
                    default:
                        System.out.println("Invalid Input");
                }
            }catch (NumberFormatException ex){
                System.out.println("Invalid Input");
            }
        }
    }

    public static void importMenu(Scanner scanner){
        boolean valid = false;
        int input;
        //Menu
        System.out.println("\nWelcome to the customer database: ");
        while(true){
            try{
                System.out.println("[1] Import JSON");
                System.out.println("[2] Import XML");
                System.out.print("Please import data: ");
                input = Integer.parseInt(scanner.nextLine());
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
                        System.out.println("invalid input\n");
                }
            }catch (NumberFormatException exception){
                System.out.println("invalid input\n");
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
                Long amountOfCustomers = (Long) countCustomers.getSingleResult();
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
        customerMenuList(scanner);
        System.out.println("Thanks for using my software :)");

        //Close database connection
        close();
    }
}
