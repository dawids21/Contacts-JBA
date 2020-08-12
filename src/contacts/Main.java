package contacts;

import java.util.Scanner;

public class Main {

    private static final String TYPES_OF_CONTACTS = "person, organization";

    private static final PersonFactory personFactory = new PersonFactory();
    private static final OrganizationFactory organizationFactory =
             new OrganizationFactory();

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
                            listOfContacts.print();
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
                            listOfContacts.remove(getSelectedContact());
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
            list.add(record);
        }
    }

    private static void editAction(Scanner input, Contact selectedContact) {
        System.out.print("Select a field (");
        var fieldsNames = selectedContact.getFieldsNames();

        for (int i = 0; i < fieldsNames.length; i++) {
            if (i != fieldsNames.length - 1) {
                System.out.print(fieldsNames[i].toLowerCase() + ", ");
            } else {
                System.out.print(fieldsNames[i].toLowerCase());
            }
        }
        System.out.print("): ");
        var field = input.nextLine()
                         .toLowerCase();
        System.out.print("Enter " + field + ": ");
        var fieldValue = input.nextLine();
        if (selectedContact.setField(field, fieldValue)) {
            System.out.println("Saved");
        } else {
            System.out.println("Wrong field name!");
        }
        System.out.println(selectedContact.getInfo());
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
                str = "edit, delete, menu";
                break;
            case LIST:
                str = "[number], back";
                break;
        }

        return str;
    }

    private static boolean selectContact(ListOfContacts list, int index) {
        boolean success = true;
        if (index >= 0 && index <= list.size()) {
            selectedContact = list.get(index);
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
