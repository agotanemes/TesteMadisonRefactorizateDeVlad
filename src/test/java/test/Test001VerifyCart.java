package test;

import com.firestarters.steps.CartPageSteps;
import com.firestarters.steps.HomepageSteps;
import com.firestarters.steps.ProductDetailsSteps;
import com.firestarters.steps.ProductPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

@RunWith(SerenityRunner.class)
public class Test001VerifyCart extends BaseTest {
    @Steps
    private HomepageSteps homepageSteps;
    @Steps
    private ProductPageSteps productPageSteps;
    @Steps
    private ProductDetailsSteps productDetailsSteps;
    @Steps
    private CartPageSteps cartPageSteps;

    @Test
    public void test001VerifyCart() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String firstProductName = "ELIZABETH KNIT TOP";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(firstProductName);
        productDetailsSteps.addDetailedProductToCart(2);
        cartPageSteps.verifyCartDetails();

        String secondProductName = "LAFAYETTE CONVERTIBLE DRESS";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(secondProductName);
        productDetailsSteps.addDetailedProductToCart(2);
        cartPageSteps.verifyCartDetails();

        cartPageSteps.modifyProductQuantityFromCart(secondProductName, 4);
        cartPageSteps.verifyCartDetails();

        cartPageSteps.removeProductFromCart(firstProductName);
        cartPageSteps.verifyCartDetails();

    }
}
