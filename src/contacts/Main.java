package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String TYPES_OF_CONTACTS = "person, organization";

    private static final PersonFactory personFactory = new PersonFactory();
    private static final OrganizationFactory organizationFactory =
             new OrganizationFactory();
    private static final PersonEditor personEditor = new PersonEditor();
    private static final OrganizationEditor organizationEditor = new OrganizationEditor();

    private static AppStates state;
    private static Contact selectedContact = null;

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfContacts = new ListOfContacts();

        while (true) {
            System.out.print("[" + getState().name()
                                             .toLowerCase() + "] Enter action (" +
                             getActions(getState()) + "): ");
            var action = input.nextLine()
                              .toLowerCase();
            switch (getState()) {
                case MENU:
                    switch (action) {
                        case "add":
                            addAction(input, listOfContacts);
                            System.out.println();
                            break;
                        case "list":
                            listOfContacts.listRecords();
                            setState(AppStates.LIST);
                            System.out.println();
                            break;
                        case "search":
                            break;
                        case "count":
                            System.out.print(
                                     "The Phone Book has " + listOfContacts.size() +
                                     " records.");
                            System.out.println();
                            break;
                        case "exit":
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Incorrect action");
                            break;
                    }
                    break;
                case SEARCH:
                    break;
                case RECORD:
                    switch (action) {
                        case "edit":
                            editAction(input, getSelectedContact());
                            deselectContact();
                            setState(AppStates.MENU);
                            break;
                        case "delete":
                            listOfContacts.removeRecord(getSelectedContact());
                            deselectContact();
                            setState(AppStates.MENU);
                            break;
                        case "menu":
                            deselectContact();
                            setState(AppStates.MENU);
                            break;
                        default:
                            System.out.print("Incorrect action");
                            break;
                    }
                    System.out.println();
                    break;
                case LIST:
                    if ("back".equals(action)) {
                        setState(AppStates.MENU);
                    } else {
                        int index;
                        try {
                            index = Integer.parseInt(action);
                        } catch (NumberFormatException e) {
                            System.out.println("You have to input number");
                            index = 0;
                        }

                        if (index != 0 && selectContact(listOfContacts, index - 1)) {
                            System.out.println(getSelectedContact().getInfo());
                            setState(AppStates.RECORD);
                        }
                    }
                    System.out.println();
                    break;
            }
        }
    }

    private static void addAction(Scanner input, ListOfContacts list) {
        System.out.print("Enter the type (" + TYPES_OF_CONTACTS + "): ");
        var type = input.nextLine();
        Contact record;
        switch (type.toLowerCase()) {
            case "person":
                record = personFactory.createContact(input);
                break;
            case "organization":
                record = organizationFactory.createContact(input);
                break;
            default:
                record = null;
                break;
        }
        if (record != null) {
            list.addRecord(record);
        }
    }

    private static void editAction(Scanner input, Contact selectedContact) {
        System.out.print("Select a field (");
        var fieldsNames = selectedContact.getFieldsNames();

        for (int i = 0; i < fieldsNames.length; i++) {
            if (i != fieldsNames.length - 1) {
                System.out.print(fieldsNames[i] + ", ");
            } else {
                System.out.print(fieldsNames[i]);
            }
        }
        System.out.print("): ");
        var field = input.nextLine()
                         .toLowerCase();
        System.out.print("Enter " + field);
        var fieldValue = input.nextLine();
        if (selectedContact.setField(field, fieldValue)) {
            System.out.print("Saved");
        } else {
            System.out.print("Wrong field name!");
        }
        System.out.print(selectedContact.getInfo());
    }

    private static void deleteAction(Scanner input, ListOfContacts list) {
        if (list.size() == 0) {
            System.out.print("No records to remove!");
        } else {
            list.listRecords();
            int index;
            try {
                System.out.print("Select a record: ");
                index = Integer.parseInt(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.print("You have to input correct index");
                index = 0;
            }
            if (index > 0 && index <= list.size()) {
                list.removeRecord(index - 1);
                System.out.print("The record removed!");
            } else {
                System.out.print("There is no such record");
            }
        }
    }

    private static void infoAction(Scanner input, ListOfContacts list) {
        int index;
        System.out.print("Enter index to show info: ");
        try {
            index = Integer.parseInt(input.nextLine());
        } catch (InputMismatchException e) {
            System.out.print("You have to input correct index");
            index = 0;
        }
        if (index > 0 && index <= list.size()) {
            System.out.println(list.getRecord(index - 1)
                                   .getInfo());
        } else {
            System.out.print("There is no such record");
        }

    }

    private static String getActions(AppStates state) {
        String str = "";

        switch (state) {
            case MENU:
                str = "add, list, search, count, exit";
                break;
            case SEARCH:
                str = "[number], back, again";
                break;
            case RECORD:
                str = "[number], delete, menu";
                break;
            case LIST:
                str = "[number], back";
                break;
        }

        return str;
    }

    private static boolean selectContact(ListOfContacts list, int index) {
        boolean success = true;
        if (index > 0 && index <= list.size()) {
            selectedContact = list.getRecord(index);
        } else {
            selectedContact = null;
            success = false;
        }
        return success;
    }

    private static void deselectContact() {
        selectedContact = null;
    }

    private static Contact getSelectedContact() {
        return selectedContact;
    }

    public static void setState(AppStates newState) {
        state = newState;
    }

    public static AppStates getState() {
        return state;
    }

    enum AppStates {
        MENU, SEARCH, RECORD, LIST
    }
}
