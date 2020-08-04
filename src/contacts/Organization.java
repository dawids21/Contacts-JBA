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
    public void getInfo() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + (hasNumber() ? getPhoneNumber() : "[no data]"));
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeEdited());
    }

    @Override
    public String toString() {
        return name;
    }
}
