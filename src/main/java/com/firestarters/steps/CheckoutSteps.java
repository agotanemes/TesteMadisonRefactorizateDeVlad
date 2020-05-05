package com.firestarters.steps;

import com.firestarters.models.BillingInf;
import com.firestarters.models.Cart;
import com.firestarters.models.CartProduct;
import com.firestarters.models.ShippingInform;
import com.firestarters.page.CheckoutPage;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;

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
    public void clickProceedToCheckoutBtn(){
        checkoutPage.clickOnWebElem(cartPageSteps.getProceedToCheckoutBtn());
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
    public void verifyOrderReviewCartProdDetails() {
        Cart expectedCart = new Cart(SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST));
        Cart actualCart = checkoutPage.getOrderReviewCart();
        List<CartProduct> expectedCartProdList=expectedCart.getCartProducts();
        List<CartProduct> actualCartProdList=actualCart.getCartProducts();
        for(int i=0;i<expectedCartProdList.size();i++){
            Assert.assertTrue(expectedCartProdList.get(i).getColor().equals(actualCartProdList.get(i).getColor()));
            Assert.assertTrue(expectedCartProdList.get(i).getSize().equals(actualCartProdList.get(i).getSize()));
            Integer expQty=expectedCartProdList.get(i).getQty();
            Integer actQty=actualCartProdList.get(i).getQty();
            Assert.assertTrue(expQty.equals(actQty));
            Assert.assertTrue(expectedCartProdList.get(i).getName().equals(actualCartProdList.get(i).getName()));
            Double expPrice=expectedCartProdList.get(i).getPrice();
            Double actPrice=actualCartProdList.get(i).getPrice();
            Assert.assertTrue(expPrice.equals(actPrice));
            Double expSubtotal=expectedCartProdList.get(i).getSubtotal();
            Double actSubtotal=actualCartProdList.get(i).getSubtotal();
            Assert.assertTrue(expSubtotal.equals(actSubtotal));
        }
        /*System.out.println("Expected cart is: " + expectedCart.toString());
        System.out.println("Actual cart is: " + actualCart.toString());

        Assert.assertTrue("Cart details are not as expected!", expectedCart.equals(actualCart));*/
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
        ShippingInform expectedShipping= SerenitySessionUtils.getFromSession(SerenityKeyConstants.SHIPPING_INF);
        System.out.println("Expected Shipping: "+ expectedShipping);
        ShippingInform actualShipping=checkoutPage.getShippingCompletedInfAsObj();
        System.out.println("Actual shipping: "+actualShipping);
        Assert.assertTrue("Incorreect billing ",expectedShipping.equals(actualShipping));
    }
    @Step
    public void clickPlaceOrder(){
        checkoutPage.clickPlaceOrder();
    }

}
