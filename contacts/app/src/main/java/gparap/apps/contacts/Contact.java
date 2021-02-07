package gparap.apps.contacts;

/**
 * Created by gparap on 2020-09-22.
 */
public class Contact {
    private String id,
                   name,
                   phoneNumberPrimary,
                   phoneNumberSecondary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumberPrimary() {
        return phoneNumberPrimary;
    }

    public void setPhoneNumberPrimary(String phoneNumberPrimary) {
        this.phoneNumberPrimary = phoneNumberPrimary;
    }

    public String getPhoneNumberSecondary() {
        return phoneNumberSecondary;
    }

    public void setPhoneNumberSecondary(String phoneNumberSecondary) {
        this.phoneNumberSecondary = phoneNumberSecondary;
    }

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        phoneNumberPrimary = "";
        phoneNumberSecondary = "";
    }
}
