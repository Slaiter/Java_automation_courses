package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserBD;
import ru.stqa.pft.mantis.model.UserData;

public class SessionHelper extends HelperBase {

    private UserData userBD = null;

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public UserData selectUser() {
        UserBD userBDs = app.db().users();
        for (UserData userBDB : userBDs) {
            if (userBDB.getName() == "administrator") {
                userBDs.iterator().next();
            } else {
                return userBD = userBDB;
            }
        }
        return userBD;
    }

    public void resetPassword(int id) {
        click(By.cssSelector("a[class='manage-menu-link']"));
        click(By.xpath("//ul[@class='menu']//a[.='Управление пользователями']"));
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
        click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
    }
}