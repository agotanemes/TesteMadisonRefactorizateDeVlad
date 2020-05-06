package com.firestarters.page;

import net.serenitybdd.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AbstractPage extends PageObject {
    //    public void waitForWebElem(WebElement element) {
    public void waitForWebElemToAppear(WebElement element) {
        withTimeoutOf(Duration.ofSeconds(60)).waitFor(element);
    }

    //TO DO am sters metoda aceasta, nu-si prea are rostul
    //    public void clickOnWebElem(WebElement element){
    //        element.click();
    //    }

    public void clickOnWebElemWithText(String text) {
        getWebElementWithText(text).click();
    }

    public WebElement getWebElementWithText(String text) {
        List<WebElement> elements = getDriver().findElements(By.xpath("//*[text()='" + text + "']"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return null;
    }
}