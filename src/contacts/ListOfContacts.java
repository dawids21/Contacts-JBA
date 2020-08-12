package contacts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;

public class ListOfContacts implements Iterable<Contact> {

    ArrayList<Contact> records = new ArrayList<>();

    public ListOfContacts() {
    }

    public void add(Contact record) {
        records.add(record);
        System.out.println("The record added.");
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
