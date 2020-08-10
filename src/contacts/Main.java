package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static AppStates state;
    private static final PersonFactory personFactory = new PersonFactory();
    private static final OrganizationFactory organizationFactory = new OrganizationFactory();

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfContacts = new ListOfContacts();
        final var personEditor = new PersonEditor();
        final var organizationEditor = new OrganizationEditor();

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
                            break;
                        case "search":
                            break;
                        case "count":
                            break;
                        case "exit":
                            break;
                        default:
                            System.out.println("Incorrect action");
                            break;
                    }
                    break;
                case EDIT:
                    if (listOfContacts.size() == 0) {
                        System.out.print("No records to edit!");
                    } else {
                        listOfContacts.listRecords();
                        int indexOfRecord;
                        System.out.print("Select a record: ");
                        try {
                            indexOfRecord = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            indexOfRecord = 0;
                        }
                        if (indexOfRecord != 0) {
                            var contact = listOfContacts.getRecord(indexOfRecord - 1);
                            if (contact instanceof Person) {
                                personEditor.edit(input, listOfContacts, indexOfRecord - 1);
                            } else if (contact instanceof Organization) {
                                organizationEditor.edit(input, listOfContacts, indexOfRecord - 1);
                            }
                        }
                    }
                    System.out.println();
                    setState(AppStates.MENU);
                    break;
                case REMOVE:
                    if (listOfContacts.size() == 0) {
                        System.out.print("No records to remove!");
                    } else {
                        listOfContacts.listRecords();
                        int index;
                        try {
                            System.out.print("Select a record: ");
                            index = Integer.parseInt(input.nextLine());
                        } catch (InputMismatchException e) {
                            System.out.print("You have to input correct index");
                            index = 0;
                        }
                        if (index > 0 && index <= listOfContacts.size()) {
                            listOfContacts.removeRecord(index - 1);
                            System.out.print("The record removed!");
                        } else {
                            System.out.print("There is no such record");
                        }
                    }
                    System.out.println();
                    setState(AppStates.MENU);
                    break;
                case COUNT:
                    System.out.print("The Phone Book has " + listOfContacts.size() + " records.");
                    System.out.println();
                    setState(AppStates.MENU);
                    break;
                case INFO:
                    listOfContacts.listRecords();
                    int index;
                    System.out.print("Enter index to show info: ");
                    try {
                        index = Integer.parseInt(input.nextLine());
                    } catch (InputMismatchException e) {
                        System.out.print("You have to input correct index");
                        index = 0;
                    }
                    if (index > 0 && index <= listOfContacts.size()) {
                        System.out.println(listOfContacts.getRecord(index - 1)
                                                         .getInfo());
                    } else {
                        System.out.print("There is no such record");
                    }
                    System.out.println();
                    setState(AppStates.MENU);
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

    private static String getActions(AppStates state) { //TODO add new states
        String str = "";

        switch (state) {
            case MENU:
                str = "add, list, search, count, exit";
                break;
            case ADD:
                break;
            case EDIT:
                break;
            case REMOVE:
                break;
            case COUNT:
                break;
            case INFO:
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
        MENU, ADD, EDIT, REMOVE, COUNT, INFO;
    }
}
