package contacts;

public class SearchEngine {

    private final ListOfContacts input;

    private ListOfContacts results = null;

    public SearchEngine(ListOfContacts input) {
        this.input = input;
    }

    public void search(String query) {
        results = new ListOfContacts();
        var lowerCaseQuery = query.toLowerCase();
        for (var contact : input) {
            for (var fieldName : contact.getFieldsNames()) {
                if (contact.getField(fieldName)
                           .toLowerCase()
                           .contains(lowerCaseQuery)) {
                    results.add(contact);
                    break;
                }
            }
        }
    }

    public void print() {
        System.out.println("Found " + results.size() + " results:");
        results.print();
    }

    public ListOfContacts getResults() {
        return results;
    }
}
