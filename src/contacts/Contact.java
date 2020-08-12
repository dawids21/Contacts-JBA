package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Contact implements Serializable {

    public static final String NO_DATA_MSG = "[no data]";

    private static final long serialVersionUID = -7806631094278739018L;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeEdited;
    private String phoneNumber;

    protected Contact(String phoneNumber) {
        timeCreated = LocalDateTime.now()
                                   .withSecond(0)
                                   .withNano(0);
        setTimeEdited(LocalDateTime.now());
        setPhoneNumber(phoneNumber);
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(LocalDateTime timeEdited) {
        this.timeEdited = timeEdited.withSecond(0)
                                    .withNano(0);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        boolean success = true;
        if (checkNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            success = false;
        }
        return success;
    }

    private boolean checkNumber(String phoneNumber) {
        return phoneNumber.matches(
                 "\\+?(\\w+[ -]\\(\\w{2,}\\)([ -]\\w{2,})*|(\\w+|\\(\\w+\\))([ -]\\w{2,})*)");
    }

    public boolean hasNumber() {
        return !phoneNumber.equals("");
    }

    public abstract String getInfo();

    public abstract String[] getFieldsNames();

    public abstract boolean setField(String fieldName, String fieldValue);

    public abstract String getField(String fieldName);
}
