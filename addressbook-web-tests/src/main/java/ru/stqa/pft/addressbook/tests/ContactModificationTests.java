
package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContacForm(new ContactData("smit", "grey", "89296173544", "mamba@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }
}