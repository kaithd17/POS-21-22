package at.kaindorf.pattern.immutable;

public class Address {
    private String streetname;
    private int zipCode;

    public Address(String streetname, int zipCode) {
        this.streetname = streetname;
        this.zipCode = zipCode;
    }

    public String getStreetname() {
        return streetname;
    }

    public int getZipCode() {
        return zipCode;
    }
}
