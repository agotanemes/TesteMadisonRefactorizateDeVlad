package test;

import com.firestarters.models.BillingInf;
import com.firestarters.steps.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

import static com.firestarters.factory.BillingInfFactory.getBillingInfInstance;

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
        checkoutSteps.fillRequestedFieldsForBilling("Nemes","Melinda","Agota","agotanemes96@gmail.com","Str Petrisat nr 212","Blaj","515400","0755096274","Romania","Alba");
        checkoutSteps.fillRequestedFieldsForShipping("Nemes","Agota","Str Petrisat Nr 212","Blaj","515400","075509627","Romania","Alba");
        checkoutSteps.selectShippingMet();
        BillingInf billingInf=getBillingInfInstance();
        System.out.println(billingInf);
        //TO DO
        //checkoutSteps.verifyOrderReviewDetails();
    }
}
