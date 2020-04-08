package com.firestarters.page;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class AbstractPage  extends PageObject {
public void waitForWebElem(WebElement element){
    withTimeoutOf(Duration.ofSeconds(60)).waitFor(element);
}
public void clickOnWebElem(WebElement element){
    element.click();
}
}
