package com.firestarters.page;

import com.firestarters.page.AbstractPage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends AbstractPage {
    @FindBy(css = "input[id='login:guest']")
    private WebElementFacade asGuestRadioButton;
    @FindBy(css = "#onepage-guest-register-button")
    private WebElementFacade continueButton;

    public void clickOnWebElem(WebElement element){
        element.click();
    }
    public WebElement getAsGuestRadioBtn(){
        return asGuestRadioButton;
    }
    public WebElement getContinueBtn(){
        return continueButton;
    }

}