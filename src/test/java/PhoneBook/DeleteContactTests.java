package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteContactTests extends TestBase{

    @BeforeMethod
    public void precondition() {
        if(app.getUserHelper().isSignOutButtonPresent()){
            logger.info("User is already logged in.");
        } else {
            app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
            logger.info("User was successfully logged in.");
        }
        app.getContactHelper().addContactPositiveData(ContactData.CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTest(){
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        app.getContactHelper().deleteOneContact();
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(ContactData.CONTACT_LOCATOR), contactsNumberBefore-1));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore-1);
    }

    @Test
    public void deleteAllContactsTest(){
        try {
            while(app.getContactHelper().hasContacts()){
                app.getContactHelper().deleteFirstContact();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все контакты были удалены!");
        }
        Assert.assertEquals(app.getContactHelper().getContactsCount(), 0, "Не все контакты были удалены!");
    }
}
