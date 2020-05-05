package test;

import com.firestarters.models.BillingInf;
import com.firestarters.models.ShippingInform;
import com.firestarters.steps.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

import static com.firestarters.factory.BillingInfFactory.getBillingInfInstance;
import static com.firestarters.factory.ShippingInformFactory.getShippingInformInstance;

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
    public void checkoutTest() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        String firstProductName = "ELIZABETH KNIT TOP";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(firstProductName);
        productDetailsSteps.addDetailedProductToCart(2);

        String secondProductName = "LAFAYETTE CONVERTIBLE DRESS";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(secondProductName);
        productDetailsSteps.addDetailedProductToCart(2);

        checkoutSteps.clickProceedToCheckoutBtn();
        checkoutSteps.selectCheckoutMethod();
        checkoutSteps.selectContinue();
        BillingInf billingInf=getBillingInfInstance();
        checkoutSteps.fillRequestedFieldsForBilling(billingInf);
        ShippingInform shippingInform=getShippingInformInstance();
        checkoutSteps.fillRequestedFieldsForShipping(shippingInform);
        checkoutSteps.selectShippingMet();
        checkoutSteps.verifyBillingDetails();
        checkoutSteps.verifyShippingDetails();
        checkoutSteps.verifyOrderReviewCartProdDetails();
        checkoutSteps.clickPlaceOrder();

    }
}
