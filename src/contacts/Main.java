package contacts;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static AppStates state;

    public static void main(String[] args) {
        setState(AppStates.MENU);
        final var input = new Scanner(System.in);
        final var listOfPeople = new ListOfPeople();

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
                    String name;
                    String surname;
                    String phoneNumber;
                    System.out.print("Enter the name: ");
                    name = input.nextLine();
                    System.out.print("Enter the surname: ");
                    surname = input.nextLine();
                    System.out.print("Enter the number: ");
                    phoneNumber = input.nextLine();
                    listOfPeople.addRecord(name, surname, phoneNumber);
                    setState(AppStates.MENU);
                    break;
                case EDIT:
                    if (listOfPeople.size() == 0) {
                        System.out.println("No records to edit!");
                    } else {
                        listOfPeople.listRecords();
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
                        listOfPeople.editRecord(indexOfRecord - 1, field, value);
                    }
                    setState(AppStates.MENU);
                    break;
                case REMOVE:
                    if (listOfPeople.size() == 0) {
                        System.out.println("No records to remove!");
                    } else {
                        listOfPeople.listRecords();
                        int index;
                        try {
                            System.out.print("Select a record: ");
                            index = Integer.parseInt(input.nextLine());
                        } catch (InputMismatchException e) {
                            System.out.println("You have to input correct index");
                            index = 0;
                        }
                        listOfPeople.removeRecord(index - 1);
                    }
                    setState(AppStates.MENU);
                    break;
                case COUNT:
                    System.out.println("The Phone Book has " + listOfPeople.size() + " records.");
                    setState(AppStates.MENU);
                    break;
                case LIST:
                    listOfPeople.listRecords();
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
        MENU,
        ADD,
        EDIT,
        REMOVE,
        COUNT,
        LIST
    }
}
