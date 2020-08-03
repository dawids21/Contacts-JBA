package contacts;

public abstract class Contact {

    private String phoneNumber;

    protected Contact() {
        setPhoneNumber("");
    }

    protected Contact(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
