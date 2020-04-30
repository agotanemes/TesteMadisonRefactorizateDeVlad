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
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getProceedToCheckoutBtn());
        checkoutSteps.selectCheckoutMethod();
        checkoutSteps.selectContinue();
        BillingInf billingInf=getBillingInfInstance();
        checkoutSteps.fillRequestedFieldsForBilling(billingInf);
        ShippingInform shippingInform=getShippingInformInstance();
        checkoutSteps.fillRequestedFieldsForShipping(shippingInform);
        checkoutSteps.selectShippingMet();
        checkoutSteps.verifyBillingDetails();
        checkoutSteps.verifyShippingDetails();

        //TO DO-lists are not equal
        //checkoutSteps.verifyOrderReviewDetails();
    }
}
