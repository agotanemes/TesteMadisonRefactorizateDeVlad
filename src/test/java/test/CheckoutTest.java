package test;

import com.firestarters.steps.CartPageSteps;
import com.firestarters.steps.CheckoutSteps;
import com.firestarters.steps.HomepageSteps;
import com.firestarters.steps.ProductDetailsSteps;
import com.firestarters.steps.ProductPageSteps;
import com.firestarters.tools.utils.Constants;

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

        //TO DO nu e nevoie musai de pas separat, l-am inclus in selectCheckoutMethodAndContinue
        //checkoutSteps.selectContinue();
        //TO DO instance in step chemam doar, ca salvam pe sesiune si ne luam de acolo in pasii de verificare
        //BillingInf billingInf = getBillingInfInstance();
        //checkoutSteps.fillRequestedFieldsForBilling(billingInf);
        checkoutSteps.fillRequestedFieldsForBilling();
        //TO DO la fel ca la billing
        //ShippingInform shippingInform = getShippingInformInstance();
        //checkoutSteps.fillRequestedFieldsForShipping(shippingInform);
        checkoutSteps.fillRequestedFieldsForShipping();
        checkoutSteps.selectShippingMet();
        checkoutSteps.verifyBillingDetails();
        checkoutSteps.verifyShippingDetails();
        checkoutSteps.verifyOrderReviewCartProdDetails();
        checkoutSteps.clickPlaceOrder();

    }
}
