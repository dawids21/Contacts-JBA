package contacts;

import java.util.Scanner;

public abstract class AddContact {

    protected final Scanner input;

    protected AddContact(Scanner input) {
        this.input = input;
    }

    public Contact add() {
        var field1 = getField1Info();
        var field2 = getField2Info();
        var number = getNumberInfo();
        var record = create(field1, field2, number);
        checkNumber(record, number);
        return record;
    }

    private String getField1Info() {
        System.out.print("Enter the name: ");
        return input.nextLine();
    }

    public abstract String getField2Info();

    private String getNumberInfo() {
        System.out.print("Enter the number: ");
        return input.nextLine();
    }

    protected abstract Contact create(String field1, String field2, String phoneNumber);

    private void checkNumber(Contact record, String inputPhoneNumber) {
        if (!inputPhoneNumber.equals("") && record.getPhoneNumber()
                                                  .equals("")) {
            System.out.println("Wrong number format!");
        }
    }
}
