package contacts;

public class Person {
    private String name;
    private String surname;
    private String phoneNumber;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        phoneNumber = "";
    }

    public Person(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
        }
    }

    private boolean checkNumber(String phoneNumber) {
        return phoneNumber.matches("\\+?(\\w+[ -]\\(\\w{2,}\\)([ -]\\w{2,})*|(\\w+|\\(\\w+\\))([ -]\\w{2,})*)");
    }

    public boolean hasNumber() {
        return !phoneNumber.equals("");
    }

}
