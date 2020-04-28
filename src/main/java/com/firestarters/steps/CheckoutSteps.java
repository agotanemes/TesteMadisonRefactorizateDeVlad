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
    public void fillRequestedFieldsForBilling(BillingInf b){
        checkoutPage.fillRequestedFieldsForBilling(b);
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.BILLING_INF, b);

    }
    @Step
    public void fillRequestedFieldsForShipping(ShippingInform s){
        checkoutPage.fillRequestedFieldsForShipping(s);
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.SHIPPING_INF, s);

    }
    @Step
    public void selectShippingMet(){
        checkoutPage.selectShippingMet();
    }

    @Step
    public void verifyOrderReviewDetails() {
        Cart expectedCart = cartPageSteps.getCartFromSession();
        Cart actualCart = checkoutPage.getOrderReviewCart();
        System.out.println("Expected cart is: " + expectedCart.toString());
        System.out.println("Actual cart is: " + actualCart.toString());
        Assert.assertTrue("Cart details are not as expected!", expectedCart.equals(actualCart));
    }
}
