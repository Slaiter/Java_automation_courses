package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final int id;
    private final String firstname;
    private final String secondname;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(String firstname, String secondname, String mobile, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.secondname = secondname;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(int id, String firstname, String secondname, String mobile, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return secondname != null ? secondname.equals(that.secondname) : that.secondname == null;
    }

    @Override
    public int hashCode() {
        return secondname != null ? secondname.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                '}';
    }
}