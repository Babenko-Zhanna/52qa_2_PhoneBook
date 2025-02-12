package PhoneBook.fw;

import PhoneBook.core.BaseHelper;
import PhoneBook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static PhoneBook.data.ContactData.CONTACT_LOCATOR;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void addContactPositiveData(String name) {
        // click on ADD link
        click(By.xpath("//a[.='ADD']"));
        // enter name
        type(By.xpath("//input[@placeholder='Name']"), name);
        // enter lastName
        type(By.xpath("//input[@placeholder='Last Name']"), "Jackson");
        // enter phone
        type(By.xpath("//input[@placeholder='Phone']"), "1234567890");
        // enter email
        type(By.xpath("//input[@placeholder='email']"), "test987@gmail.com");
        // enter address
        type(By.xpath("//input[@placeholder='Address']"), "Germany");
        // enter description
        type(By.xpath("//input[@placeholder='description']"), "My Friend");
        // click on Save button
        click(By.xpath("//b[.='Save']"));
    }

    public void addContactPositiveData(Contact contact) {
        click(By.xpath("//a[.='ADD']"));
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
        click(By.xpath("//b[.='Save']"));
    }

    public int getContactsCount() {
        if (isElementPresent(By.className(CONTACT_LOCATOR))) {
            return driver.findElements(By.className(CONTACT_LOCATOR)).size();
        }
        return 0;
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.className(CONTACT_LOCATOR));
        for (WebElement element : contacts){
            if(element.getText().contains(textToFind)){
                return true;
            }
        }
        return false;
    }

    public void deleteOneContact() {
        click(By.className(CONTACT_LOCATOR));
        click(By.xpath("//button[.='Remove']"));
    }

    public boolean hasContacts() {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }

    public void deleteFirstContact() {
        int contactsNumberBefore = getContactsCount();
        deleteOneContact();
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsNumberBefore-1));
    }
}
