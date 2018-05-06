package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            WebElement editField = wd.findElement(locator);
            String existingText = editField.getAttribute("value");
            if (!text.equals(existingText)) {
                editField.clear();
                editField.sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public boolean isAlertPresent(By aNew) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElemetPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected WebElement findElement(By locator) {
        return wd.findElement(locator);
    }

    public void closeAlert() {
        wd.switchTo().alert().accept();
    }

    public void select(By by, String text) {
        new Select(findElement(by)).selectByVisibleText(text);
    }
}