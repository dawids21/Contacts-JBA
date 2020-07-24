package contacts;

import java.util.ArrayList;

public class ListOfPeople {
    ArrayList<Person> records = new ArrayList<>();

    public ListOfPeople() {}

    public void addRecord(String name, String surname, String phoneNumber) {
        var person = new Person(name, surname, phoneNumber);
        if (!phoneNumber.equals("") && person.getPhoneNumber().equals("")) {
            System.out.println("Wrong number format!");
        }
        records.add(person);
        System.out.println("The record added.");
    }

    public void editRecord(int index, String field, String value) {
        if (index < 0 || index > records.size()) {
            System.out.println("There is no such record");
            return;
        }

        var person = records.get(index);
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
                if (!value.equals("") && person.getPhoneNumber().equals("")) {
                    System.out.println("Wrong number format!");
                }
                System.out.println("The record updated!");
                break;
            default:
                System.out.println("There is no such field in record");
                break;
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
        for (var person : records) {
            System.out.println(index + ". " + person.getName() + " " + person.getSurname() + ", " + (person.hasNumber() ?
                                                                                                             person.getPhoneNumber() :
                                                                                                             "[no number]"));
            index++;
        }
    }

    public int size() {
        return records.size();
    }
}
