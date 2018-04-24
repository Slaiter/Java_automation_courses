package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressAndEmail extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.contact().all().size() == 0) {
                app.contact().create(new ContactData()
                        .withFirstname("bob").withLastname("petrov").withHomePhone("111")
                        .withMobilePhone("222").withWorkPhone("333").withEmail("qiwi77@mail.ru")
                        .withGroup("test1").withAddress("testaddress").withEmail("email")
                        .withEmail2("email2").withEmail3("email3"));
            }
        }
    }

    @Test
    public void testAddressAndEmail() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        VerifyContactListInUi();
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}