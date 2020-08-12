package contacts;

import java.util.ArrayList;

public class ListOfContacts {

    ArrayList<Contact> records = new ArrayList<>();

    public ListOfContacts() {
    }

    public void add(Contact record) {
        records.add(record);
        System.out.println("The record added.");
    }

    public Contact get(int index) {
        return records.get(index);
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
}
