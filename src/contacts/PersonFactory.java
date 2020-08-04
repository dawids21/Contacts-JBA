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
        var birthDateString = input.nextLine();
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(birthDateString);
        } catch (DateTimeParseException e) {
            birthDate = null;
        }

        System.out.print("Enter the surname: ");
        var genderString = input.nextLine();
        Person.Genders gender;
        switch (genderString) {
            case "M":
                gender = Person.Genders.MALE;
                break;
            case "F":
                gender = Person.Genders.FEMALE;
                break;
            default:
                System.out.println("Bad gender");
                gender = Person.Genders.INVALID;
                break;
        }

        System.out.print("Enter the phone number: ");
        var phoneNumber = input.nextLine();
        if (!checkNumber(phoneNumber)) {
            phoneNumber = "";
            System.out.println("Wrong number format!");
        }
        return new Person(name, surname, birthDate, gender, phoneNumber);
    }
}
