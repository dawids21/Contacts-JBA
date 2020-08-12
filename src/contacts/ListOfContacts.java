package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;

public class ListOfContacts implements Iterable<Contact>, Serializable {

    private static final long serialVersionUID = 6239461410088048045L;
    ArrayList<Contact> records = new ArrayList<>();

    public ListOfContacts() {
    }

    public void add(Contact record) {
        records.add(record);
    }

    public Contact get(int index) {
        Contact contact;
        if (index >= 0 && index <= records.size()) {
            contact = records.get(index);
        } else {
            contact = null;
        }
        return contact;
    }

    public void remove(int index) {
        records.remove(index);
    }

    public void remove(Contact contact) {
        records.remove(contact);
    }

    public void print() {
        int index = 1;
        for (var record : records) {
            System.out.println(index + ". " + record.toString());
            index++;
        }
    }

    public int size() {
        return records.size();
    }

    @Override
    public Iterator<Contact> iterator() {
        return records.iterator();
    }

    @Override
    public Spliterator<Contact> spliterator() {
        return records.spliterator();
    }
}
