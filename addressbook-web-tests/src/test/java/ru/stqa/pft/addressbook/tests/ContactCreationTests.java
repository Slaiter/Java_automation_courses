package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withFirstname("bob").withLastname("petrov").withHomePhone("111")
                .withMobilePhone("222").withWorkPhone("333").withEmail("qiwi77@mail.ru").withGroup("test1")
                .withPhoto(photo).withAddress("testaddress").withEmail("email")
                .withEmail2("email2").withEmail3("email3");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}