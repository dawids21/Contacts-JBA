package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static AppStates state;

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfContacts = new ListOfContacts();
        final var personFactory = new PersonFactory();
        final var organizationFactory = new OrganizationFactory();
        final var personEditor = new PersonEditor();
        final var organizationEditor = new OrganizationEditor();

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
                        System.out.print("Select a record: ");
                        try {
                            indexOfRecord = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            indexOfRecord = 0;
                        }
                        if (indexOfRecord != 0) {
                            var contact = listOfContacts.getRecord(indexOfRecord);
                            if (contact instanceof Person) {
                                personEditor.edit(input, listOfContacts, indexOfRecord);
                            } else if (contact instanceof Organization) {
                                organizationEditor.edit(input, listOfContacts, indexOfRecord);
                            }
                        }
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
                case INFO:
                    listOfContacts.listRecords();
                    int index;
                    System.out.print("Enter index to show info: ");
                    try {
                        index = Integer.parseInt(input.nextLine());
                    } catch (InputMismatchException e) {
                        System.out.println("You have to input correct index");
                        index = 0;
                    }
                    listOfContacts.getRecord(index)
                                  .getInfo();
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
        MENU, ADD, EDIT, REMOVE, COUNT, INFO
    }
}
