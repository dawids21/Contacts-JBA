package contacts;

public class SearchEngine {

    private final ListOfContacts input;

    private ListOfContacts results = null;

    public SearchEngine(ListOfContacts input) {
        this.input = input;
    }

    public void search(String query) {
        results = new ListOfContacts();
        for (var contact : input) {
            if (contact.toString()
                       .toLowerCase()
                       .contains(query.toLowerCase())) {
                results.add(contact);
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
