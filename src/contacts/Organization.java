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
        return new String[0];
    }

    @Override
    public void setField(String fieldName, String fieldValue) {

    }

    @Override
    public String getField(String fieldName) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
