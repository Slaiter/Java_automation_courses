package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().fillContacForm(new ContactData("bob", "petrov", "89296173544", "qiwi77@mail.ru", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }
}