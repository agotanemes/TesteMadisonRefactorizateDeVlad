package com.firestarters.steps;

import com.firestarters.models.BillingInf;
import com.firestarters.models.Cart;
import com.firestarters.models.ShippingInform;
import com.firestarters.page.CheckoutPage;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import static com.firestarters.tools.utils.SerenitySessionUtils.getFromSession;

public class CheckoutSteps {
    CheckoutPage checkoutPage;
    @Steps
    CartPageSteps cartPageSteps;
    @Step
    public void selectCheckoutMethod(){
        checkoutPage.clickOnWebElem(checkoutPage.getAsGuestRadioBtn());
        //checkoutPage.selectCheckoutMethod();
    }
    @Step
    public void selectContinue(){
        checkoutPage.clickOnWebElem(checkoutPage.getContinueBtn());
    }
    @Step
    public void fillRequestedFieldsForBilling(BillingInf billingInf){
        checkoutPage.fillRequestedFieldsForBilling(billingInf);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BILLING_INF, billingInf);

    }
    @Step
    public void fillRequestedFieldsForShipping(ShippingInform shippingInform){
        checkoutPage.fillRequestedFieldsForShipping(shippingInform);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.SHIPPING_INF, shippingInform);

    }
    @Step
    public void selectShippingMet(){
        checkoutPage.selectShippingMet();
    }

    @Step
    public void verifyOrderReviewDetails() {
        Cart expectedCart = getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST);
        Cart actualCart = checkoutPage.getOrderReviewCart();
        System.out.println("Expected cart is: " + expectedCart.toString());
        System.out.println("Actual cart is: " + actualCart.toString());
        Assert.assertTrue("Cart details are not as expected!", expectedCart.equals(actualCart));
    }
    @Step
    public BillingInf getBillingInfFromSession() {
        BillingInf billingInf = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BILLING_INF);
        System.out.println("Cart from session: "+billingInf);
        return billingInf;
    }
    @Step
    public void verifyBillingDetails(){
        BillingInf expectedBilling = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BILLING_INF);
        System.out.println("Expected Billing: "+ expectedBilling);
        BillingInf actualBilling = checkoutPage.getBillingCompletedInfAsObj();
        System.out.println("Actual billing: "+ actualBilling);

        Assert.assertTrue("Incorreect billing ",expectedBilling.equals(actualBilling));
    }
    @Step
    public void verifyShippingDetails(){
        ShippingInform expectedShipping= SerenitySessionUtils.getFromSession(SerenityKeyConstants.BILLING_INF);
        System.out.println("Expected Shipping: "+ expectedShipping);
        ShippingInform actualShipping=checkoutPage.getShippingCompletedInfAsObj();
        System.out.println("Actual shipping: "+actualShipping);
        Assert.assertTrue("Incorreect billing ",expectedShipping.equals(actualShipping));
    }
}
