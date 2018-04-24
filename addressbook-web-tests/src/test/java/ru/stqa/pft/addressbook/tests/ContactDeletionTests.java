package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (groups.size() == 0) {
            GroupData group = new GroupData().withName("new");
            app.goTo().groupPage();
            app.group().create(group);
            groups = app.db().groups();
        }
        if (app.db().contacts().size() == 0) {
            if (app.contact().all().size() == 0) {
                app.contact().create(new ContactData().withFirstname("smit").withLastname("grey").withMobilePhone("89296173544")
                        .withEmail("mamba@mail.ru").withPhoto(new File("src/test/resources/stru.png")).inGroup(groups.iterator().next()));
            }
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
        VerifyContactListInUi();
    }
}