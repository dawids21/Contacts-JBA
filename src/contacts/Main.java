package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static AppStates state;
    private static final PersonFactory personFactory = new PersonFactory();
    private static final OrganizationFactory organizationFactory = new OrganizationFactory();
    private static final PersonEditor personEditor = new PersonEditor();
    private static final OrganizationEditor organizationEditor = new OrganizationEditor();

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfContacts = new ListOfContacts();

        while (true) {
            System.out.print("[" + getState().name()
                                             .toLowerCase() + "] ");
            switch (getState()) {
                case MENU:
                    System.out.print("Enter action (" + getActions(getState()) + "): ");
                    var action = input.nextLine();
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
                            System.out.print("The Phone Book has " + listOfContacts.size() + " records.");
                            System.out.println();
                            break;
                        case "exit":
                            break;
                        default:
                            System.out.println("Incorrect action");
                            break;
                    }
                    break;
            }
        }
    }

    private static void addAction(Scanner input, ListOfContacts list) {
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
            list.addRecord(record);
        }
    }

    private static void editAction(Scanner input, ListOfContacts list) {
        if (list.size() == 0) {
            System.out.print("No records to edit!");
        } else {
            list.listRecords();
            int indexOfRecord;
            System.out.print("Select a record: ");
            try {
                indexOfRecord = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                indexOfRecord = 0;
            }
            if (indexOfRecord != 0) {
                var contact = list.getRecord(indexOfRecord - 1);
                if (contact instanceof Person) {
                    personEditor.edit(input, list, indexOfRecord - 1);
                } else if (contact instanceof Organization) {
                    organizationEditor.edit(input, list, indexOfRecord - 1);
                }
            }
        }
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

    private static String getActions(AppStates state) { //TODO add new states
        String str = "";

        switch (state) {
            case MENU:
                str = "add, list, search, count, exit";
                break;
            case SEARCH:
                break;
            case RECORD:
                break;
            case LIST:
                break;
        }

        return str;
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
            case "info":
                setState(AppStates.INFO);
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
        MENU, SEARCH, RECORD, LIST
    }
}
