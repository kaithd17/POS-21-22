package at.kaindorf.pattern.immutable;

public final class ImmutablePerson { //make class final. Klassen die final sind, können nicht abgeleitet werden.
                                     //make instance vars private final.
    //Man kann das Objekt von diesen Klassen nicht verändern. Zum Beispiel String oder LocalDate.

    private final String name;
    private final int age;
    private final Address address;

    public ImmutablePerson(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = new Address(address.getStreetname(), address.getZipCode());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return new Address(address.getStreetname(), address.getZipCode());
    }
}
