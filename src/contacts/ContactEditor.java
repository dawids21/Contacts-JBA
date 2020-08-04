package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class ContactEditor {

    protected ContactEditor() {

    }

    public void edit(Scanner input, ListOfContacts list, int index) {
        String field = "";
        String value = "";
        printOptions();
        field = input.nextLine();
        System.out.print("Enter " + field + ": ");
        value = input.nextLine();
        editContact(list, index, field, value);
        list.getRecord(index).setTimeEdited(LocalDateTime.now());
    }

    protected abstract void printOptions();

    protected abstract void editContact(ListOfContacts list, int index, String field, String value);
}
