package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
/*        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User is already logged in.");
        } else {
            logger.info("User was successfully logged in.");
        }*/
        app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
    }

    @Test
    public void addContactPositiveTest() {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        // addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.CONTACT_NAME)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Berlin")
                .setDescription("My friend"));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }


    @Test(dataProvider = "addContactDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderPositiveTest(String name, String lastName, String phone, String email, String address, String description) {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }

    @Test(dataProvider = "iteratorDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderIteratorPositiveTest(String name, String lastName, String phone, String email, String address, String description) {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }

    @Test(dataProvider = "objectDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderObjectPositiveTest(Contact contact) {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }

    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactDataProviderFromCSVPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactDataPositiveTest() {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        // addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.CONTACT_NAME)
                .setLastName(ContactData.CONTACT_LASTNAME)
                .setPhone(ContactData.CONTACT_PHONE)
                .setEmail(ContactData.CONTACT_EMAIL)
                .setAddress(ContactData.CONTACT_ADDRESS)
                .setDescription(ContactData.CONTACT_DESCRIPTION));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }

    @Test
    public void addContactWODescriptionPositiveTest() {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsNumberBefore);
        // addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.CONTACT_NAME)
                .setLastName(ContactData.CONTACT_LASTNAME)
                .setPhone(ContactData.CONTACT_PHONE)
                .setEmail(ContactData.CONTACT_EMAIL)
                .setAddress(ContactData.CONTACT_ADDRESS));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsNumberAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.CONTACT_NAME));
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore + 1);
    }

    @AfterMethod
    public void postCondition() {
        int contactsNumberBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО удаления: " + contactsNumberBefore);

        if (contactsNumberBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять.");
            return;
        }

        app.getContactHelper().click(By.className(ContactData.CONTACT_LOCATOR));
        app.getContactHelper().click(By.xpath("//button[.='Remove']"));
        // Ждём, пока не выполнено условие:
        // условие: уменьшилось ли количество контактов по сравнению с исходным значением contactsBefore
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsNumberBefore);
        // Явное ожидание, пока количество контактов не уменьшится (contactsBefore - 1)
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(ContactData.CONTACT_LOCATOR), contactsNumberBefore - 1));
        int contactsNumberAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления: " + contactsNumberAfter);
        Assert.assertEquals(contactsNumberAfter, contactsNumberBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }
}

