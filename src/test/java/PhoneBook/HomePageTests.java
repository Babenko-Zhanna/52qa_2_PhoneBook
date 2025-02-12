package PhoneBook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomePageHelper().isHomeComponentPresent(), "Home component is not found");
        // System.out.println("Is home component found?: " + app.getHomePageHelper().isHomeComponentPresent());
    }
}

