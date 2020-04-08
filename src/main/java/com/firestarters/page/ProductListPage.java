package com.firestarters.page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

//import net.serenitybdd.core.annotations.findby.FindBy;
//import net.serenitybdd.core.pages.WebElementFacade;

public class ProductListPage extends AbstractPage {

    @FindBy(css = ".products-grid .product-image[title*= 'Core']")
    private WebElementFacade specificProduct;

    @FindBy(css = ".products-grid .item.last")
    private List<WebElementFacade> productsList;

    @FindBy(css = ".success-msg")
    private WebElementFacade successMessage;

    @FindBy(css = ".block-compare")
    private WebElementFacade compareProductsMenu;

    //Agota 10.03.2020

    //

    //Ciuverca Ionut
    public void selectAProduct() {
        specificProduct.click();
    }

    //Ciuverca Ionut
    public WebElementFacade getRandomProduct() {
        Random random = new Random();
        WebElementFacade randomProduct = productsList.get(random.nextInt(productsList.size()));
        return randomProduct;
    }

    //Ciuverca Ionut
    public void clickOnAddToCompare() {
        WebElementFacade randomProduct = getRandomProduct();
        String productTitle = randomProduct.findElement(By.cssSelector(".product-name")).getText().toLowerCase();
        randomProduct.findElement(By.cssSelector(".link-compare")).click();
        String message = successMessage.getText().toLowerCase();
        assertTrue(message.contains(productTitle));
    }

    //Ciuverca Ionut
    public void addToCompare() {
        WebElementFacade randomProduct = getRandomProduct();
        randomProduct.findElement(By.cssSelector(".link-compare")).click();
        assertTrue(compareProductsMenu.isDisplayed());

    }

    //Agota 10.03.2020

    public void clickProduct(WebElementFacade product) {
        product.click();
    }

    public void openProduct(String name) {
        for (WebElementFacade product : productsList) {
            if (product.findElement(By.cssSelector(".product-name a")).getText().toLowerCase().contentEquals(name.toLowerCase())) {
                clickProduct(product);
                break;
            }
        }

    }

    //
}
