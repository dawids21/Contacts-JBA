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

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            this.birthDate = null;
        }
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.toLowerCase()
                  .equals("m")) {
            this.gender = Genders.MALE;
        } else if (gender.toLowerCase()
                         .equals("f")) {
            this.gender = Genders.FEMALE;
        } else {
            this.gender = Genders.INVALID;
        }
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
    public String[] getFieldsNames() { //TODO implement getFieldsNames
        return new String[0];
    }

    @Override
    public void setField(String fieldName, String fieldValue) { //TODO implement setField

    }

    @Override
    public String getField(String fieldName) { //TODO implement getField
        return null;
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
