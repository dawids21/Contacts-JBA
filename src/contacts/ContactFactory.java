package contacts;

public class ContactFactory {

    public ContactFactory() {
       
    }

    public Person createPerson(String name, String surname) {
        return new Person(name, surname);
    }

    public Person createPerson(String name, String surname, String phoneNumber) {
        return new Person(name, surname, phoneNumber);
    }

    public Organization createOrganization(String name, String address) {
        return new Organization(name, address);
    }

    public Organization createOrganization(String name, String address, String phoneNumber) {
        return new Organization(name, address, phoneNumber);
    }
}
