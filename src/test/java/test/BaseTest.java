package test;

import com.firestarters.steps.CartPageSteps;
import com.firestarters.tools.utils.Constants;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;
    @Steps
    private CartPageSteps cartPageSteps;

    @Before
    public void setup() {
        System.out.println("Before test!");
        webdriver.get(Constants.URL);
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        System.out.println("After test!");
        webdriver.quit();
    }

}
