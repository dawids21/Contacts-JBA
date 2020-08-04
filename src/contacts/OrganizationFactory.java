package contacts;

import java.util.Scanner;

public class OrganizationFactory extends ContactFactory {

    public OrganizationFactory() {
    }

    @Override
    public Contact createContact(Scanner input) {
        System.out.print("Enter the organization name: ");
        var name = input.nextLine();

        System.out.print("Enter the address: ");
        var address = input.nextLine();

        System.out.print("Enter the phone number: ");
        var phoneNumber = input.nextLine();
        if (!checkNumber(phoneNumber)) {
            phoneNumber = "";
            System.out.println("Wrong number format!");
        }

        return new Organization(name, address, phoneNumber);
    }
}
