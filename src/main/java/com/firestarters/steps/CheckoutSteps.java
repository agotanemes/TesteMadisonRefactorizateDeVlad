package com.firestarters.steps;

import com.firestarters.models.BillingInf;
import com.firestarters.models.Cart;
import com.firestarters.models.ShippingInform;
import com.firestarters.page.CheckoutPage;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class CheckoutSteps {
    CheckoutPage checkoutPage;
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
    public void fillRequestedFieldsForBilling(String firstN, String middleN, String lastN, String email, String adress, String city, String zip, String tel, String country, String state){
        BillingInf billingInf=checkoutPage.fillRequestedFieldsForBilling(firstN,middleN,lastN,email,adress,city,zip,tel,country,state);
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.BILLING_INF, billingInf);

    }
    @Step
    public void fillRequestedFieldsForShipping(String firstN, String lastN, String strAddr, String city, String zip, String tel, String country, String state){
        ShippingInform shippingInform=checkoutPage.fillRequestedFieldsForShipping(firstN,lastN,strAddr,city,zip,tel,country,state);
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.SHIPPING_INF, shippingInform);

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
