package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Contact {

    private static final String[] FIELD_NAMES =
             {"Name", "Surname", "Birth", "Gender", "Number"};
    private static final long serialVersionUID = 1061845763226119918L;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Genders gender;

    public Person(String name, String surname, String birthDate, String gender,
                  String phoneNumber) {
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

    public boolean hasName() {
        return !getName().equals("");
    }

    public String getSurname() {
        return surname;
    }

    public boolean hasSurname() {
        return !getSurname().equals("");
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

    public boolean hasBirthDate() {
        return birthDate != null;
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

    public boolean hasGender() {
        return gender != null && getGender() != Genders.INVALID;
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
                str = "Invalid";
            }
            return str;
        }
    }

    @Override
    public String getInfo() {
        return String.format(
                 "Name: %s\nSurname: %s\nBirth date: %s\nGender: %s\nNumber: %s\nTime created: %s\nTime last edit: %s",
                 (hasName() ? getName() : Contact.NO_DATA_MSG),
                 (hasSurname() ? getSurname() : Contact.NO_DATA_MSG),
                 (hasBirthDate() ? getBirthDate() : Contact.NO_DATA_MSG),
                 (hasGender() ? getGender() : Contact.NO_DATA_MSG),
                 (hasNumber() ? getPhoneNumber() : Contact.NO_DATA_MSG),
                 getTimeCreated().toString(),
                 getTimeEdited().toString());
    }

    @Override
    public String[] getFieldsNames() {
        return FIELD_NAMES;
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
                fieldValue = hasName() ? getName() : Contact.NO_DATA_MSG;
                break;
            case "surname":
                fieldValue = hasSurname() ? getSurname() : Contact.NO_DATA_MSG;
                break;
            case "birth":
                fieldValue =
                         hasBirthDate() ? getBirthDate().toString() : Contact.NO_DATA_MSG;
                break;
            case "gender":
                fieldValue = hasGender() ? getGender().toString() : Contact.NO_DATA_MSG;
                break;
            case "number":
                fieldValue = hasNumber() ? getPhoneNumber() : Contact.NO_DATA_MSG;
                break;
        }

        return fieldValue;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
