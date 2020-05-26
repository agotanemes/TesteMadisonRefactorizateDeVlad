package test;

import com.firestarters.steps.ui.CartPageSteps;
import com.firestarters.steps.ui.CheckoutSteps;
import com.firestarters.steps.ui.HomepageSteps;
import com.firestarters.steps.ui.ProductDetailsSteps;
import com.firestarters.steps.ui.ProductPageSteps;
import com.firestarters.tools.constants.Constants;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CheckoutTest extends BaseTest {
    @Steps
    CartPageSteps cartPageSteps;
    @Steps
    CheckoutSteps checkoutSteps;
    @Steps
    HomepageSteps homepageSteps;
    @Steps
    ProductPageSteps productPageSteps;
    @Steps
    ProductDetailsSteps productDetailsSteps;

    @Test
    public void checkoutTest() {
        String firstProductName = "ELIZABETH KNIT TOP";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(firstProductName);
        productDetailsSteps.addDetailedProductToCart(2);

        String secondProductName = "LAFAYETTE CONVERTIBLE DRESS";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(secondProductName);
        productDetailsSteps.addDetailedProductToCart(2);

        checkoutSteps.clickProceedToCheckoutBtn();
        checkoutSteps.selectCheckoutMethodAndContinue(Constants.CHECKOUT_METHOD_AS_GUEST);
        checkoutSteps.fillRequestedFieldsForBilling();
        checkoutSteps.fillRequestedFieldsForShipping();
        checkoutSteps.selectShippingMet();
        checkoutSteps.verifyBillingDetails();
        checkoutSteps.verifyShippingDetails();
        checkoutSteps.verifyOrderReviewCartProdDetails();
        checkoutSteps.clickPlaceOrder();

    }
}
