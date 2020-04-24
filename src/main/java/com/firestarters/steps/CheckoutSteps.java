package com.firestarters.steps;

import com.firestarters.page.CheckoutPage;
import net.thucydides.core.annotations.Step;

public class CheckoutSteps {
    CheckoutPage checkoutPage;
    @Step
    public void selectCheckoutMethod(){
        checkoutPage.clickOnWebElem(checkoutPage.getAsGuestRadioBtn());
        //checkoutPage.selectCheckoutMethod();
    }
    @Step
    public void selectContinue(){
        checkoutPage.clickOnWebElem(checkoutPage.getContinueBtn());
    }
}
