package contacts;

public interface ContactFactory {
    Contact createContact(String field1, String field2, String phoneNumber);
}
