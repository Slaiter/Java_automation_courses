package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("bob").withLastname("petrov").withHomePhone("111")
                    .withMobilePhone("222").withWorkPhone("333").withEmail("qiwi77@mail.ru")
                    .withGroup("test1").withAddress("testaddress").withEmail("email")
                    .withEmail2("email2").withEmail3("email3"));
        }
    }

    @Test
    public void testContactDeletion() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}