package contacts;

public class PersonFactory implements ContactFactory {

    @Override
    public Contact createContact(String field1, String field2, String phoneNumber) {
        return new Person(field1, field2, phoneNumber);
    }
}
