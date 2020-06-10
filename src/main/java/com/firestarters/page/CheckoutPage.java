package com.firestarters.page;

import static com.firestarters.tools.utils.Utils.convertStringToDouble;
import static com.firestarters.tools.utils.Utils.eliminateSpaces;
import static com.firestarters.tools.utils.Utils.extractNumberFromStrinAsString;
import static com.firestarters.tools.utils.Utils.splitByEnter;
import static com.firestarters.tools.utils.Utils.splitStringByComma;
import static com.firestarters.tools.utils.Utils.splitStringBySpace;
import static com.firestarters.tools.utils.Utils.stringReplace;

import com.firestarters.models.BillingInf;
import com.firestarters.models.Cart;
import com.firestarters.models.Product;
import com.firestarters.models.ShippingInform;
import com.firestarters.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends GeneralPage {
    //@FindBy(css = "input[id='login:guest']")
    //private WebElementFacade asGuestRadioButton;
    //@FindBy(css = "#onepage-guest-register-button")
   // private WebElementFacade continueButton;
    //Billing inf
    @FindBy(css = "select[id='billing:country_id']")
    private WebElementFacade countryDropdown;
    @FindBy(css = "select[id='billing:region_id']")
    private WebElementFacade stateDropdown;
    //@FindBy(css = "input[title*='different']")
    //private WebElement shippingToThisAddressRadioBtn;
    //@FindBy(css = "#billing-buttons-container button[title = 'Continue']")
    //private WebElementFacade billingTabContinueButton;
    @FindBy(css = "#shipping\\:firstname")
    private WebElementFacade shippingFirstNameLabel;

    //Shipping inf
    @FindBy(css = "select[name*='shipping'][title='Country']")
    private WebElementFacade shippingConuntryDropdown;
    @FindBy(css = "#shipping\\:region_id")
    private WebElementFacade shippingStateDropdown;
    //@FindBy(css = "#shipping-buttons-container button[title=\"Continue\"]")
    //private WebElementFacade shippingTabContinueButton;
    @FindBy(css = ".sp-methods dt:first-child")
    private WebElementFacade flatRateLabel;

    //@FindBy(css = ".sp-methods label[for*='free']")
    //private WebElementFacade shippingMethodRadioButton;
    //@FindBy(css = "#shipping-method-buttons-container button")
    //private WebElementFacade shippingMethodContinueButton;
    @FindBy(css = "#payment-buttons-container button")
    private WebElementFacade paymentContinueButton;
    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderBtn;
    @FindBy(css = "#checkout-review-table>tfoot td:nth-child(2)")
    private List<WebElement> totalPrices;

    //order review
    @FindBy(css = "#checkout-review-table>tbody>tr")
    private List<WebElement> orderReviewProducts;
    @FindBy(css = "tr[class='first last']")
    private WebElement orderReviewHeader;
    @FindBy(css = "#billing-progress-opcheckout>dd[class='complete']>address")
    private WebElement billingCompletedInf;
    @FindBy(css = "#shipping-progress-opcheckout>dd[class='complete']>address")
    private WebElement shippingCompletedInf;

    public WebElement getInputByTitle(String title) {
        return getDriver().findElement(By.cssSelector("li.active input[title='" + title + "']"));
    }

    public void fillRequestedFieldsForBilling(BillingInf b) {
        getInputByTitle("First Name").sendKeys(b.getFirstN());
        getInputByTitle("Middle Name/Initial").sendKeys(b.getMiddleN());
        getInputByTitle("Last Name").sendKeys(b.getLastN());
        getInputByTitle("Email Address").sendKeys(b.getEmailAdr());
        getInputByTitle("Street Address").sendKeys(b.getAddress());
        getInputByTitle("City").sendKeys(b.getCity());
        getInputByTitle("Zip/Postal Code").sendKeys(b.getZip());
        getInputByTitle("Telephone").sendKeys(b.getTelephone());
        countryDropdown.selectByVisibleText(b.getCountry());
        stateDropdown.selectByVisibleText(b.getState());
        clickOnWebElemWithText(Constants.SHIP_TO_DIFFERENT_ADDRESS);
        clickOnWebElemWithText(Constants.TEXT_BUTTON_CONTINUE);
    }

    public void fillRequestedFieldsForShipping(ShippingInform s) {
        waitForWebElemToAppear(shippingFirstNameLabel);
        getInputByTitle("First Name").sendKeys(s.getFirstName());
        getInputByTitle("Last Name").sendKeys(s.getLastName());
        getInputByTitle("Street Address").sendKeys(s.getStreetAddr());
        getInputByTitle("City").sendKeys(s.getCity());
        getInputByTitle("Zip/Postal Code").sendKeys(s.getZip());
        getInputByTitle("Telephone").sendKeys(s.getTelephone());
        shippingConuntryDropdown.selectByVisibleText(s.getCountry());
        shippingStateDropdown.selectByVisibleText(s.getState());
        scrollToElementWithText(Constants.TEXT_BUTTON_CONTINUE);
        clickOnWebElemWithText(Constants.TEXT_BUTTON_CONTINUE);
        waitForWebElemToAppear(flatRateLabel);

    }

    public void selectShippingMet() {
        clickOnWebElemWithText(Constants.SHIPING_METHOD_FREE);
        clickOnWebElemWithText(Constants.TEXT_BUTTON_CONTINUE);
        waitForWebElemToAppear(paymentContinueButton);
        clickOnWebElemWithText(Constants.TEXT_BUTTON_CONTINUE);
        waitForWebElemToAppear(placeOrderBtn);

    }

    public Cart getOrderReviewCart() {
        List<Product> products = new ArrayList<>();
        waitForWebElemToAppear(orderReviewHeader);
        List<WebElement> orderReviewprod = orderReviewProducts;
        for (WebElement prod : orderReviewprod) {
            Product product = new Product();
            String name = prod.findElement(By.cssSelector(".product-name")).getText();
            String color = prod.findElement(By.cssSelector("dd:nth-child(2)")).getText();
            String size = prod.findElement(By.cssSelector("dd:nth-child(4)")).getText();
            String price = prod.findElement(By.cssSelector("td[data-rwd-label='Price']")).getText();
            Double correctPrice = convertStringToDouble(stringReplace(price));
            double priceAsdouble = correctPrice.doubleValue();
            String qty = prod.findElement(By.cssSelector("td[data-rwd-label='Qty']")).getText();
            String subtotal = prod.findElement(By.cssSelector("td[data-rwd-label='Subtotal']")).getText();
            Double correctSub = convertStringToDouble(stringReplace(subtotal));
            double subtotalAsDouble = correctSub.doubleValue();
            product.setName(name);
            product.setColor(color);
            product.setSize(size);
            product.setPrice(priceAsdouble);
            int qtyInt = Integer.parseInt(qty);
            product.setQty(qtyInt);
            product.setSubtotal(subtotalAsDouble);
            products.add(product);
        }
        String subtotal = totalPrices.get(0).getText();
        String tax = totalPrices.get(1).getText();
        String grandTotal = totalPrices.get(2).getText();
        double dSubtotal = convertStringToDouble(stringReplace(subtotal));
        double dTax = convertStringToDouble(stringReplace(tax));
        double dGrandTotal = convertStringToDouble(stringReplace(grandTotal));
        Cart cart = new Cart();
        cart.setCartProducts(products);
        cart.setGrandTotal(dGrandTotal);
        cart.setTax(dTax);
        cart.setSubtotal(dSubtotal);
        return cart;
    }

    //Billing completed information from right side
    public String getBillingCompletedInf() {
        String s = billingCompletedInf.getText();
        return s;
    }

    public BillingInf getBillingCompletedInfAsObj() {
        BillingInf billingInf = new BillingInf();
        String billingInfStr = getBillingCompletedInf();
        String[] billingInfComp = splitByEnter(billingInfStr);

        String firstNAndMidNAndLastN = billingInfComp[0];
        String[] text1 = splitStringBySpace(firstNAndMidNAndLastN);
        String fName = text1[0];
        String mName = text1[1];
        String lName = text1[2];

        String adress = billingInfComp[1];

        String cityStateAndZip = billingInfComp[2];
        String[] text2 = splitStringByComma(cityStateAndZip);
        String city = text2[0];
        String state = text2[1];
        String zip = text2[2].replace(" ", "");

        String country = billingInfComp[3];

        String telephone = billingInfComp[4];
        String tel = extractNumberFromStrinAsString(telephone);

        billingInf.setFirstN(fName);
        billingInf.setMiddleN(mName);
        billingInf.setLastN(lName);
        billingInf.setState(eliminateSpaces(state));
        billingInf.setCountry(country);
        billingInf.setCity(city);
        billingInf.setAddress(adress);
        billingInf.setZip(zip);
        billingInf.setTelephone(tel);
        return billingInf;
    }

    public String getShippingCompletedInf() {
        return shippingCompletedInf.getText();
    }

    public ShippingInform getShippingCompletedInfAsObj() {
        ShippingInform shippingInf = new ShippingInform();
        String shippingInfStr = getShippingCompletedInf();
        String[] billingInfComp = splitByEnter(shippingInfStr);

        String firstNAndMidNAndLastN = billingInfComp[0];
        String[] text1 = splitStringBySpace(firstNAndMidNAndLastN);
        String fName = text1[0];
        String lName = text1[1];

        String adress = billingInfComp[1];

        String cityStateAndZip = billingInfComp[2];
        String[] text2 = splitStringByComma(cityStateAndZip);
        String city = text2[0];
        String state = text2[1];
        String zip = text2[2].replace(" ", "");

        String country = billingInfComp[3];

        String telephone = billingInfComp[4];
        String tel = extractNumberFromStrinAsString(telephone);

        shippingInf.setFirstName(fName);
        shippingInf.setLastName(lName);
        shippingInf.setState(eliminateSpaces(state));
        shippingInf.setCountry(country);
        shippingInf.setCity(city);
        shippingInf.setStreetAddr(adress);
        shippingInf.setZip(zip);
        shippingInf.setTelephone(tel);
        return shippingInf;

    }

    public void clickPlaceOrder() {
        clickOnWebElemWithText(Constants.TEXT_BUTTON_PLACE_ORDER);
        withTimeoutOf(Duration.ofSeconds(10));
    }

}