package contacts;

public class OrganizationEditor extends ContactEditor {

    public OrganizationEditor() {

    }

    @Override
    protected void printOptions() {
        System.out.print("Select a field (name, address, number): ");
    }

    @Override
    protected void editContact(ListOfContacts list, int index, String field, String value) {
        var organization = (Organization) list.getRecord(index);
        switch (field.toLowerCase()) {
            case "name":
                organization.setName(value);
                break;
            case "address":
                organization.setAddress(value);
                break;
            case "number":
                organization.setPhoneNumber(value);
                break;
            default:
                System.out.println("There is no such field in record");
                return;
        }
        System.out.println("The record updated!");
    }
}
