package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PersonFactory extends ContactFactory {

    public PersonFactory() {
    }

    @Override
    public Contact createContact(Scanner input) {
        System.out.print("Enter the name: ");
        var name = input.nextLine();

        System.out.print("Enter the surname: ");
        var surname = input.nextLine();

        System.out.print("Enter the birth date: ");
        var birthDate = input.nextLine();
        if (!checkBirthDate(birthDate)) {
            System.out.println("Bad birth date!");
        }

        System.out.print("Enter the gender: ");
        var gender = input.nextLine();
        if (!checkGender(gender)) {
            System.out.println("Bad gender!");
        }

        System.out.print("Enter the phone number: ");
        var phoneNumber = input.nextLine();
        if (!checkNumber(phoneNumber)) {
            System.out.println("Wrong number format!");
        }

        return new Person(name, surname, birthDate, gender, phoneNumber);
    }

    private boolean checkGender(String gender) {
        boolean valid = true;
        if (gender.length() != 1) {
            valid = false;
        } else if (gender.toLowerCase()
                         .charAt(0) != 'm' && gender.toLowerCase()
                                                    .charAt(0) != 'f') {
            valid = false;
        }
        return valid;
    }

    private boolean checkBirthDate(String birthDate) {
        boolean valid = true;
        try {
            LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }
}
