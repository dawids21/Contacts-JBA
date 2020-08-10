package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Contact {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Genders gender;

    public Person(String name, String surname, String birthDate, String gender, String phoneNumber) {
        super(phoneNumber);
        setName(name);
        setSurname(surname);
        setBirthDate(birthDate);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean setBirthDate(String birthDate) {
        boolean success = true;
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            this.birthDate = null;
            success = false;
        }
        return success;
    }

    public Genders getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        boolean success = true;
        if (gender.toLowerCase()
                  .equals("m")) {
            this.gender = Genders.MALE;
        } else if (gender.toLowerCase()
                         .equals("f")) {
            this.gender = Genders.FEMALE;
        } else {
            this.gender = Genders.INVALID;
            success = false;
        }
        return success;
    }

    enum Genders {
        MALE, FEMALE, INVALID;

        @Override
        public String toString() {
            String str;
            if (this == MALE) {
                str = "Male";
            } else if (this == FEMALE) {
                str = "Female";
            } else {
                str = "[no data]";
            }
            return str;
        }
    }

    @Override
    public String getInfo() {
        return "Name: " + getName() + "\nSurname: " + getSurname() + "\nBirth date: " +
               (getBirthDate() != null ? getBirthDate() : "[no data]") + "\nGender: " + getGender() + "\nNumber: " +
               (hasNumber() ? getPhoneNumber() : "[no data]") + "\nTime created: " + getTimeCreated() +
               "\nTime last edit: " + getTimeEdited();
    }

    @Override
    public String[] getFieldsNames() {
        var names = new String[5];
        names[0] = "Name";
        names[1] = "Surname";
        names[2] = "Birth";
        names[3] = "Gender";
        names[4] = "Number";
        return names;
    }

    @Override
    public boolean setField(String fieldName, String fieldValue) {
        boolean success = true;
        switch (fieldName.toLowerCase()) {
            case "name":
                setName(fieldValue);
                break;
            case "surname":
                setSurname(fieldValue);
                break;
            case "birth":
                success = setBirthDate(fieldValue);
                break;
            case "gender":
                success = setGender(fieldValue);
                break;
            case "number":
                success = setPhoneNumber(fieldValue);
                break;
        }
        return success;
    }

    @Override
    public String getField(String fieldName) {
        String fieldValue = "";

        switch (fieldName.toLowerCase()) {
            case "name":
                fieldValue = getName();
                break;
            case "surname":
                fieldValue = getSurname();
                break;
            case "birth":
                fieldValue = getBirthDate().toString();
                break;
            case "gender":
                fieldValue = getGender().toString();
                break;
            case "number":
                fieldValue = getPhoneNumber();
                break;
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
