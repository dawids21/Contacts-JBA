package contacts;

import java.util.Scanner;

public abstract class ContactEditor {

    protected ContactEditor() {

    }

    public void edit(Scanner input, ListOfContacts list, int index) {
        String field = "";
        String value = "";
        printOptions();
        field = input.nextLine();
        System.out.print("Enter the " + field + ": ");
        value = input.nextLine();
        editContact(list, index, field, value);
    }

    protected abstract void printOptions();

    protected abstract void editContact(ListOfContacts list, int index, String field, String value);
}
