package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElemetPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElemetPresent(By.name("New"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        if (isElemetPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }
}