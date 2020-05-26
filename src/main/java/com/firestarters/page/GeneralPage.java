package com.firestarters.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class GeneralPage extends AbstractPage {

    public void waitForWebElemToAppear(WebElement element) {
        withTimeoutOf(Duration.ofSeconds(60)).waitFor(element);
    }

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
        return getDriver().findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
    }
    public void scrollToElementWithText(String elementText) {
        scrollToElement(getWebElementWithText(elementText));
    }
    public void typeInInputWithTitle(String inputTitle, String value) {
        getDriver().findElement(By.cssSelector("input[title='" + inputTitle + "']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),
                value);

    }
}
