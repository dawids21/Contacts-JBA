package contacts;

public class Organization extends Contact {

    private static final String[] FIELD_NAMES = {"Name", "Address", "Number"};

    private static final long serialVersionUID = -6841528553144988537L;
    private String name;
    private String address;

    public Organization(String name, String address, String phoneNumber) {
        super(phoneNumber);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasName() {
        return !getName().equals("");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasAddress() {
        return !getAddress().equals("");
    }

    @Override
    public String getInfo() {
        return String.format("Name: %s\nAddress: %s\nNumber: %s\n",
                             (hasName() ? getName() : Contact.NO_DATA_MSG),
                             (hasAddress() ? getAddress() : Contact.NO_DATA_MSG),
                             (hasNumber() ? getPhoneNumber() : Contact.NO_DATA_MSG));
    }

    @Override
    public String[] getFieldsNames() {
        return FIELD_NAMES;
    }

    @Override
    public boolean setField(String fieldName, String fieldValue) {
        boolean valid = true;
        switch (fieldName.toLowerCase()) {
            case "name":
                setName(fieldValue);
                break;
            case "address":
                setAddress(fieldValue);
                break;
            case "number":
                if (!setPhoneNumber(fieldValue)) {
                    valid = false;
                }
                break;
            default:
                valid = false;
        }
        return valid;
    }

    @Override
    public String getField(String fieldName) {
        String fieldValue = "";

        switch (fieldName.toLowerCase()) {
            case "name":
                fieldValue = hasName() ? getName() : Contact.NO_DATA_MSG;
                break;
            case "address":
                fieldValue = hasAddress() ? getAddress() : Contact.NO_DATA_MSG;
                break;
            case "number":
                fieldValue = hasNumber() ? getPhoneNumber() : Contact.NO_DATA_MSG;
                break;
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
