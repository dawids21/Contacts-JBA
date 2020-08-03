package contacts;

import java.util.Scanner;

public class AddOrganization extends AddContact {

    public AddOrganization(Scanner input) {
        super(input);
    }

    @Override
    public String getField2Info() {
        System.out.print("Enter the address: ");
        return input.nextLine();
    }

    @Override
    protected Contact create(String field1, String field2, String phoneNumber) {
        return new Organization(field1, field2, phoneNumber);
    }
}
