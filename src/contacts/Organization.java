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
}
