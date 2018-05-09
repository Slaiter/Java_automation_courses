package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionContactFromGroup extends TestBase {

    ContactData contactData = new ContactData();
    GroupData groupData = new GroupData();


    @BeforeMethod
    public void ensurePreconditions() {
        Groups group = app.db().groups();

        Groups allGroups = app.db().groups();
        if (allGroups.size() == 0) {
            GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
            app.goTo().groupPage();
            ;
            app.group().create(newGroup);
            allGroups = app.db().groups();
        }
        Contacts contacts = app.db().contacts();
        if (contacts.size() != 0) {
            try {
                contactData = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            } catch (Exception ex) {
                contactData = null;
            }
            if (contactData == null) {
                ContactData contact = contacts.iterator().next();
                GroupData randomGroup = group.iterator().next();
                app.contact().addition(contact, randomGroup);
                contacts = app.db().contacts();
                contactData = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            }
        } else {
            ContactData newContact = new ContactData().withFirstname(RandomStringUtils.randomAlphabetic(10))
                    .withLastname(RandomStringUtils.randomAlphabetic(10)).inGroup(allGroups.stream().iterator().next());
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("alex").withLastname("palex").withAddress("tralex").inGroup(allGroups.stream().iterator().next()));
            contactData = app.db().contacts().stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
        }
        Groups groupsWithContact = contactData.getGroups();
        groupData = groupsWithContact.stream().iterator().next();

    }

    @Test
    public void testDeletionContactFromGroup() {
        ContactData before = app.db().contacts().stream().filter((c) -> (c.getId() == contactData.getId())).iterator().next();
        app.contact().deleteFromGroup(contactData, groupData);
        ContactData after = app.db().contacts().stream().filter((c) -> (c.getId() == contactData.getId())).iterator().next();
        System.out.println("before: " + before);
        System.out.println("after: " + after);
        assertThat(before, CoreMatchers.equalTo(after.without(groupData)));
    }
}