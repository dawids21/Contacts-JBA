package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Person extends Contact {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private Genders gender;

    public Person(String name, String surname, LocalDate birthDate, Genders gender, String phoneNumber) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
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

    public void setGender(Genders gender) {
        this.gender = gender;
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
    public String toString() {
        return getName() + " " + getSurname();
    }
}
