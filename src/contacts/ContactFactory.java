package contacts;

import java.util.Scanner;

public abstract class ContactFactory {

    public abstract Contact createContact(Scanner input);

    protected boolean checkNumber(String phoneNumber) {
        return phoneNumber.matches("\\+?(\\w+[ -]\\(\\w{2,}\\)([ -]\\w{2,})*|(\\w+|\\(\\w+\\))([ -]\\w{2,})*)");
    }
}
