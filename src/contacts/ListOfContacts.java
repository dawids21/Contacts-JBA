package contacts;

import java.util.ArrayList;

public class ListOfContacts {

    ArrayList<Contact> records = new ArrayList<>();

    public ListOfContacts() {
    }

    public void addRecord(Contact record) {
        records.add(record);
        System.out.println("The record added.");
    }

    public Contact getRecord(int index) {
        return records.get(index);
    }

    public void removeRecord(int index) {
        if (index >= records.size()) {
            System.out.println("There is no such record");
        } else if (index >= 0) {
            records.remove(index);
            System.out.println("The record removed!");
        }
    }

    public void listRecords() {
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
