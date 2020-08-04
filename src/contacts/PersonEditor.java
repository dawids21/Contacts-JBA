package contacts;

public class PersonEditor extends ContactEditor {

    public PersonEditor() {

    }

    @Override
    protected void printOptions() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
    }

    @Override
    protected void editContact(ListOfContacts list, int index, String field, String value) {
        var person = (Person) list.getRecord(index);
        switch (field.toLowerCase()) {
            case "name":
                person.setName(value);
                break;
            case "surname":
                person.setSurname(value);
                break;
            case "birth":
                person.setBirthDate(value);
                break;
            case "gender":
                person.setGender(value);
                break;
            case "number":
                person.setPhoneNumber(value);
                break;
            default:
                System.out.println("There is no such field in record");
                return;
        }
        System.out.println("The record updated!");
    }
}
