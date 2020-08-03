package contacts;

import java.util.Scanner;

public interface ContactFactory {

    Contact createContact(Scanner input);

    static boolean checkNumber(String phoneNumber) {
        return phoneNumber.matches("\\+?(\\w+[ -]\\(\\w{2,}\\)([ -]\\w{2,})*|(\\w+|\\(\\w+\\))([ -]\\w{2,})*)");
    }
}
