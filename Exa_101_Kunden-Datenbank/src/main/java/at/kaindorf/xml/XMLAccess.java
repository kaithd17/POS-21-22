package at.kaindorf.xml;

import at.kaindorf.pojos.Address;
import at.kaindorf.pojos.Country;
import at.kaindorf.pojos.Customer;
import at.kaindorf.pojos.XMLCustomer;

import java.util.HashSet;
import java.util.Set;

public class XMLAccess {
    private static Set<Country> countrySet = new HashSet<>();
    private static Set<Address> addressSet = new HashSet<>();

    public static Customer convertToCustomer(XMLCustomer xmlCustomer) {

        //Create real country object and add object to countrySet
        Country country = new Country(xmlCustomer.getCountry(), xmlCustomer.getCountry_code());
        countrySet.add(country);
        Country realCountry = countrySet.stream().filter(country1 -> country1.equals(country)).findFirst().get();

        //Create real address object and add object to addressSet
        Address address = new Address(
                xmlCustomer.getStreetname(),
                xmlCustomer.getStreetnumber(),
                xmlCustomer.getPostal_code(),
                xmlCustomer.getCity(),
                realCountry);
        addressSet.add(address);

        //Add address to countryList
        realCountry.getAddresses().add(addressSet.stream().filter(address1 -> address1.equals(address)).findFirst().get());

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
}
