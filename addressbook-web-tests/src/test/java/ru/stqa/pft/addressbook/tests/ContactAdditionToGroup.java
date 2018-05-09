package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (groups.size() == 0) {
            GroupData group = new GroupData().withName("new");
            app.goTo().groupPage();
            app.group().create(group);
        }
        if (app.db().contacts().size() == 0) {
            if (app.contact().all().size() == 0) {
                app.contact().create(new ContactData()
                        .withFirstname("bob").withLastname("petrov").withHomePhone("111").withMobilePhone("222").withWorkPhone("333")
                        .withEmail("qiwi77@mail.ru").withAddress("testaddress").withEmail("email")
                        .withEmail2("email2").withEmail3("email3").inGroup(groups.iterator().next()));
            }
        }
    }

    @Test
    public void testAddContactToGroup() {

        int contactId = 0;
        boolean completed = false;
        Groups beforeAdditionGroups = null;
        Groups beforeAddedGroups = null;
        Groups existedGroups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (ContactData editedContact : contacts) {
            beforeAdditionGroups = editedContact.getGroups();
            GroupData newGroup = app.contact().getGroupToAddition(existedGroups, editedContact);
            if (newGroup != null) {
                app.contact().addition(editedContact, newGroup);
                contactId = editedContact.getId();
                beforeAddedGroups = beforeAdditionGroups.withAdded(newGroup);
                completed = true;
                break;
            }
        }
        if (!completed) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("alex").withHeader("palex").withFooter("tralex"));
            Groups extendedGroups = app.db().groups();
            GroupData lastAddedGroup = extendedGroups.stream()
                    .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get();
            ContactData contact = contacts.iterator().next();
            contactId = contact.getId();
            app.contact().addition(contact, lastAddedGroup);
            if (beforeAdditionGroups != null) {
                beforeAddedGroups = beforeAdditionGroups.withAdded(lastAddedGroup);
            }
            Groups groupAfter = app.db().contactById(contactId).iterator().next().getGroups();
            assertThat(groupAfter, equalTo(beforeAddedGroups));
        }
    }
}