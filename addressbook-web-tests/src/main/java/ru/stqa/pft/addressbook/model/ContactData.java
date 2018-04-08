
package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String secondname;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(String firstname, String secondname, String mobile, String email, String group) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}