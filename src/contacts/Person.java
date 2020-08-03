package contacts;

import java.time.LocalDate;

public class Person extends Contact {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Genders gender;

    public Person(String name, String surname, String phoneNumber) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
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

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Genders getGender() {
        return gender;
    }

    public void setGender(Genders gender) {
        this.gender = gender;
    }

    enum Genders {
        MALE, FEMALE
    }

    enum Data implements ContactData {
        NAME, SURNAME, BIRTH_DATE, GENDER
    }
}
