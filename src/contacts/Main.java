package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static AppStates state;

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfContacts = new ListOfContacts();
        final ContactFactory personFactory = new PersonFactory();
        final ContactFactory organizationFactory = new OrganizationFactory();

        while (true) {
            switch (getState()) {
                case MENU:
                    System.out.print("Enter action (add, remove, edit, count, list, exit): ");
                    var action = input.nextLine();
                    if (action.equals("exit")) {
                        System.exit(0);
                    } else {
                        setAppStateFromMenuAction(action);
                    }
                    break;
                case ADD:
                    System.out.print("Enter the type (person, organization): ");
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
                        listOfContacts.addRecord(record);
                    }
                    setState(AppStates.MENU);
                    break;
                case EDIT:
                    if (listOfContacts.size() == 0) {
                        System.out.println("No records to edit!");
                    } else {
                        listOfContacts.listRecords();
                        int indexOfRecord;
                        String field = "";
                        String value = "";
                        try {
                            System.out.print("Select a record: ");
                            indexOfRecord = Integer.parseInt(input.nextLine());
                            System.out.print("Select a field (name, surname, number): ");
                            field = input.nextLine();
                            System.out.print("Enter the " + field + ": ");
                            value = input.nextLine();
                        } catch (NumberFormatException e) {
                            indexOfRecord = 0;
                        }
                        listOfContacts.editRecord(indexOfRecord - 1, field, value);
                    }
                    setState(AppStates.MENU);
                    break;
                case REMOVE:
                    if (listOfContacts.size() == 0) {
                        System.out.println("No records to remove!");
                    } else {
                        listOfContacts.listRecords();
                        int index;
                        try {
                            System.out.print("Select a record: ");
                            index = Integer.parseInt(input.nextLine());
                        } catch (InputMismatchException e) {
                            System.out.println("You have to input correct index");
                            index = 0;
                        }
                        listOfContacts.removeRecord(index - 1);
                    }
                    setState(AppStates.MENU);
                    break;
                case COUNT:
                    System.out.println("The Phone Book has " + listOfContacts.size() + " records.");
                    setState(AppStates.MENU);
                    break;
                case LIST:
                    listOfContacts.listRecords();
                    setState(AppStates.MENU);
                    break;
            }
        }
    }


    private static void setAppStateFromMenuAction(String action) {
        switch (action) {
            case "add":
                setState(AppStates.ADD);
                break;
            case "remove":
                setState(AppStates.REMOVE);
                break;
            case "edit":
                setState(AppStates.EDIT);
                break;
            case "count":
                setState(AppStates.COUNT);
                break;
            case "list":
                setState(AppStates.LIST);
                break;
            default:
                System.out.println("Incorrect action");
                break;
        }
    }

    public static void setState(AppStates newState) {
        state = newState;
    }

    public static AppStates getState() {
        return state;
    }

    enum AppStates {
        MENU, ADD, EDIT, REMOVE, COUNT, LIST
    }
}
