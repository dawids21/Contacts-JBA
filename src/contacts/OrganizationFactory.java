package contacts;

public class OrganizationFactory implements ContactFactory {

    @Override
    public Contact createContact(String field1, String field2, String phoneNumber) {
        return new Organization(field1, field2, phoneNumber);
    }
}
