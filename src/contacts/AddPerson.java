package contacts;

import java.util.Scanner;

public class AddPerson extends AddContact {

    public AddPerson(Scanner input) {
        super(input);
    }

    @Override
    public String getField2Info() {
        System.out.print("Enter the surname: ");
        return input.nextLine();
    }

    @Override
    protected Contact create(String field1, String field2, String phoneNumber) {
        return new Person(field1, field2, phoneNumber);
    }
}
