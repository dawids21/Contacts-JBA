package contacts;

public class Organization extends Contact {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getInfo() {
        return "Organization name: " + getName() + "\nAddress: " + getAddress() + "\nNumber: " +
               (hasNumber() ? getPhoneNumber() : "[no data]") + "\nTime created: " + getTimeCreated() +
               "\nTime last edit: " + getTimeEdited();
    }

    @Override
    public String[] getFieldsNames() {
        var names = new String[3];
        names[0] = "Name";
        names[1] = "Address";
        names[2] = "Number";
        return names;
    }

    @Override
    public void setField(String fieldName, String fieldValue) {
        switch (fieldName.toLowerCase()) {
            case "name":
                setName(fieldValue);
                break;
            case "address":
                setAddress(fieldValue);
                break;
            case "number":
                setPhoneNumber(fieldValue);
                break;
        }
    }

    @Override
    public String getField(String fieldName) {
        String fieldValue = "";

        switch (fieldName.toLowerCase()) {
            case "name":
                fieldValue = getName();
                break;
            case "address":
                fieldValue = getAddress();
                break;
            case "number":
                fieldValue = getPhoneNumber();
                break;
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        return name;
    }
}
