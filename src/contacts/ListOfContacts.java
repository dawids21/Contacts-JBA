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

    public void editRecord(int index, String field, String value) { //TODO
        if (index < 0 || index > records.size()) {
            System.out.println("There is no such record");
            return;
        }

        var record = records.get(index);
        if (record instanceof Person) {
            var person = (Person) record;

            switch (field) {
                case "name":
                    person.setName(value);
                    System.out.println("The record updated!");
                    break;
                case "surname":
                    person.setSurname(value);
                    System.out.println("The record updated!");
                    break;
                case "number":
                    person.setPhoneNumber(value);
                    if (!value.equals("") && person.getPhoneNumber()
                                                   .equals("")) {
                        System.out.println("Wrong number format!");
                    }
                    System.out.println("The record updated!");
                    break;
                default:
                    System.out.println("There is no such field in record");
                    break;
            }
        } else if (record instanceof Organization) {
            var organization = (Organization) record;

            switch (field) {
                case "name":
                    organization.setName(value);
                    System.out.println("The record updated!");
                    break;
                case "address":
                    organization.setAddress(value);
                    System.out.println("The record updated!");
                    break;
                case "number":
                    organization.setPhoneNumber(value);
                    if (!value.equals("") && organization.getPhoneNumber()
                                                         .equals("")) {
                        System.out.println("Wrong number format!");
                    }
                    System.out.println("The record updated!");
                    break;
                default:
                    System.out.println("There is no such field in record");
                    break;
            }
        }
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
            System.out.println(record.toString());
            index++;
        }
    }

    public int size() {
        return records.size();
    }
}
