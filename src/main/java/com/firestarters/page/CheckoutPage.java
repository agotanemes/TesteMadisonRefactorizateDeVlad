package com.firestarters.page;

import com.firestarters.models.BillingInf;
import com.firestarters.models.Cart;
import com.firestarters.models.CartProduct;
import com.firestarters.models.ShippingInform;
import com.firestarters.page.AbstractPage;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.firestarters.tools.utils.Utils.*;

public class CheckoutPage extends AbstractPage {
    @FindBy(css = "input[id='login:guest']")
    private WebElementFacade asGuestRadioButton;
    @FindBy(css = "#onepage-guest-register-button")
    private WebElementFacade continueButton;
    //Billing inf
    @FindBy(css="select[id='billing:country_id']")
    private WebElementFacade countryDropdown;
    @FindBy(css="select[id='billing:region_id']")
    private WebElementFacade stateDropdown;
    @FindBy(css="input[title*='different']")
    private WebElement shippingToThisAddressRadioBtn;
    @FindBy(css = "#billing-buttons-container button[title = 'Continue']")
    private WebElementFacade billingTabContinueButton;
    @FindBy(css = "#shipping\\:firstname")
    private  WebElementFacade shippingFirstNameLabel;


    //Shipping inf
    @FindBy(css="select[name*='shipping'][title='Country']")
    private WebElementFacade shippingConuntryDropdown;
    @FindBy(css = "#shipping\\:region_id")
    private WebElementFacade shippingStateDropdown;
    @FindBy(css = "#shipping-buttons-container button[title=\"Continue\"]")
    private  WebElementFacade shippingTabContinueButton;
    @FindBy(css = ".sp-methods dt:first-child")
    private WebElementFacade flatRateLabel;

    @FindBy(css = ".sp-methods label[for*='free']")
    private WebElementFacade shippingMethodRadioButton;
    @FindBy(css = "#shipping-method-buttons-container button")
    private WebElementFacade shippingMethodContinueButton;
    @FindBy(css = "#payment-buttons-container button")
    private WebElementFacade paymentContinueButton;
    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderBtn;
    @FindBy(css="#checkout-review-table>tfoot td:nth-child(2)")
    private List<WebElement> totalPrices;

    //order review
    @FindBy(css="#checkout-review-table>tbody>tr")
    private List<WebElement> orderReviewProducts;
    @FindBy(css="tr[class='first last']")
    private WebElement orderReviewHeader;

    public void clickOnWebElem(WebElement element){
        element.click();
    }
    public WebElement getAsGuestRadioBtn(){
        return asGuestRadioButton;
    }
    public WebElement getContinueBtn(){
        return continueButton;
    }

    public WebElement getInputByTitle(String title){
        return getDriver().findElement(By.cssSelector("li.active input[title='" + title + "']"));
    }

    public BillingInf fillRequestedFieldsForBilling(String firstN, String middleN, String lastN, String email, String adress, String city, String zip, String tel, String country, String state){
        getInputByTitle("First Name").sendKeys(firstN);
        getInputByTitle("Middle Name/Initial").sendKeys(middleN);
        getInputByTitle("Last Name").sendKeys(lastN);
        getInputByTitle("Email Address").sendKeys(email);
        getInputByTitle("Street Address").sendKeys(adress);
        getInputByTitle("City").sendKeys(city);
        getInputByTitle("Zip/Postal Code").sendKeys(zip);
        getInputByTitle("Telephone").sendKeys(tel);
        clickOnWebElem(countryDropdown);
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country);
        clickOnWebElem(stateDropdown);
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText(state);
        clickOnWebElem(shippingToThisAddressRadioBtn);
        clickOnWebElem(billingTabContinueButton);
        //waitFor(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#shipping\\:firstname")));
        //withTimeoutOf(Duration.ofSeconds(60)).waitFor(shippingFirstNameLabel);
        waitForWebElem(shippingFirstNameLabel);
        BillingInf billingInf=new BillingInf();
        billingInf.setFirstN(firstN);
        billingInf.setMiddleN(middleN);
        billingInf.setLastN(lastN);
        billingInf.setEmailAdr(email);
        billingInf.setAddress(adress);
        billingInf.setCity(city);
        billingInf.setZip(eliminateStaces(zip));
        billingInf.setTelephone(tel);
        billingInf.setCountry(country);
        billingInf.setState(eliminateStaces(state));
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.BILLING_INF, billingInf);
        return billingInf;
    }
    public ShippingInform fillRequestedFieldsForShipping(String firstN, String lastN, String strAddr, String city, String zip, String tel, String country, String state){
        //complete the fields
        getInputByTitle("First Name").sendKeys(firstN);
        getInputByTitle("Last Name").sendKeys(lastN);
        getInputByTitle("Street Address").sendKeys(strAddr);
        getInputByTitle("City").sendKeys(city);
        getInputByTitle("Zip/Postal Code").sendKeys(zip);
        getInputByTitle("Telephone").sendKeys(tel);
        clickOnWebElem(shippingConuntryDropdown);
        Select shippingCountrySelect = new Select(shippingConuntryDropdown);
        shippingCountrySelect.selectByVisibleText(country);
        clickOnWebElem(shippingStateDropdown);
        Select shippingStateSelect = new Select(shippingStateDropdown);
        shippingStateSelect.selectByVisibleText(state);
        //set the shiping inf in the shipininf obj.
        ShippingInform shippingInform=new ShippingInform();
        shippingInform.setFirstName(firstN);
        shippingInform.setLastName(lastN);
        shippingInform.setStreetAddr(strAddr);
        shippingInform.setCity(city);
        shippingInform.setZip(zip);
        shippingInform.setTelephone(tel);
        shippingInform.setCountry(country);
        shippingInform.setState(state);
        shippingTabContinueButton.click();
        //withTimeoutOf(Duration.ofSeconds(15)).waitFor(flatRateLabel);
        waitForWebElem(flatRateLabel);
        return shippingInform;
    }
    public void selectShippingMet(){
        clickOnWebElem(shippingMethodRadioButton);
        clickOnWebElem(shippingMethodContinueButton);
        //withTimeoutOf(Duration.ofSeconds(10)).waitFor(paymentContinueButton);
        waitForWebElem(paymentContinueButton);
        paymentContinueButton.click();
        //withTimeoutOf(Duration.ofSeconds(20)).waitFor(placeOrderBtn);
        waitForWebElem(placeOrderBtn);

    }
    public Cart getOrderReviewCart(){
        List<CartProduct> cartProducts=new ArrayList<>();
        waitForWebElem(orderReviewHeader);
        List<WebElement> orderReviewprod= orderReviewProducts;
        for(WebElement prod:orderReviewprod){
            CartProduct cartProduct=new CartProduct();
            String name=prod.findElement(By.cssSelector(".product-name")).getText();
            String color=prod.findElement(By.cssSelector("dd:nth-child(2)")).getText();
            String size=prod.findElement(By.cssSelector("dd:nth-child(4)")).getText();
            String price=prod.findElement(By.cssSelector("td[data-rwd-label='Price']")).getText();
            Double correctPrice = convertStringToDouble(stringReplace(price));
            double priceAsdouble = correctPrice.doubleValue();
            String qty=prod.findElement(By.cssSelector("td[data-rwd-label='Qty']")).getText();
            String subtotal=prod.findElement(By.cssSelector("td[data-rwd-label='Subtotal']")).getText();
            Double correctSub=convertStringToDouble(stringReplace(subtotal));
            double subtotalAsDouble=correctSub.doubleValue();
            cartProduct.setName(name);
            cartProduct.setColor(color);
            cartProduct.setSize(size);
            cartProduct.setPrice(priceAsdouble);
            int qtyInt= Integer.parseInt(qty);
            cartProduct.setQty(qtyInt);
            cartProduct.setSub(subtotalAsDouble);
            cartProducts.add(cartProduct);
        }
        String subtotal=totalPrices.get(0).getText();
        String tax=totalPrices.get(1).getText();
        String grandTotal=totalPrices.get(2).getText();
        double dSubtotal=convertStringToDouble(stringReplace(subtotal));
        double dTax=convertStringToDouble(stringReplace(tax));
        double dGrandTotal=convertStringToDouble(stringReplace(grandTotal));
        Cart cart=new Cart();
        cart.setCartProducts(cartProducts);
        cart.setGrandTotal(dGrandTotal);
        cart.setTax(dTax);
        cart.setSubtotal(dSubtotal);
        return cart;
    }


}