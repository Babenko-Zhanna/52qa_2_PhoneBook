package PhoneBook.fw;

import PhoneBook.core.BaseHelper;
import PhoneBook.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class UserHelper extends BaseHelper {

    Logger logger = LoggerFactory.getLogger(UserHelper.class);


    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickTheLoginButton() {
        click(By.name("login"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void typePassword(String password) {
        logger.info("Typing password: " + password);
        type(By.name("password"), password);
    }

    public void typeEmail(String email) {
        logger.info("Typing email: " + email);
        type(By.name("email"), email);
    }

    public void checkLogin() {
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickTheLoginButton();
    }

    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }

    public void clickOnSignOutButton() {
         click(By.xpath("//button[.='Sign Out']"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }
}
