package PhoneBook;

import PhoneBook.data.UserData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getUserHelper().isSignOutButtonPresent()){
            logger.info("User is already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign out.");
        }
    }

    @Test (priority = 1)
    public void createAccountPositiveTest() {
        // click on Login link //a[.='LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        // enter email By.name("email")
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.RANDOM_EMAIL);
        // enter password By.name("password")
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        // click the Registration button By.name("registration")
        app.driver.findElement(By.name("registration")).click();
        // Assert button //button[.='Sign Out']
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void createAccountNegativeTest() {
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.VALID_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
        // hard assert - первый failed assert прервет выполнение теста
        // Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void createAccountNegativeSoftAssertTest() {
        SoftAssert softAssert = new SoftAssert();
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.VALID_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        /*
         * В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
         * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
         * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */
        softAssert.assertTrue(app.getContactHelper().isAlertPresent());
        softAssert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
        /*
         * Назначение: assertAll() используется для проверки всех утверждений, сделанных с помощью SoftAssert, в конце теста.
         * Если одно или несколько утверждений не прошли, assertAll() вызовет исключение и тест будет помечен как неудавшийся
         */
    }
}
