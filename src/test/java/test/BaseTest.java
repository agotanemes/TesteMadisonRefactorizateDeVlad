package test;

import com.firestarters.steps.ui.CartPageSteps;
import com.firestarters.tools.constants.Constants;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;
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
        //TODO nu inteleg de ce ai avea nevoie de urmatoarele 4 linii
        System.out.println("Before test!");
        webdriver.get(Constants.URL);
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8080");
    }

    /*
     * @Before public void listen() { System.setProperty("http.proxyHost", "localhost"); System.setProperty("http.proxyPort", "8080");
     * System.setProperty("https.proxyHost", "localhost"); System.setProperty("https.proxyPort", "8080"); }
     */

    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        //TODO ai putea sterge before-ul de aici, cel putin pana apare nevoia de un before comun mai multor teste. Monentan nu ai, stergere produse sau altceva
        //        cartHttpSteps.emptyCart();
        //System.out.println("After test!");
        //webdriver.quit();
    }

}
