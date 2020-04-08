package com.firestarters.page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

@DefaultUrl("http://qa2.dev.evozon.com")
public class HomePage extends AbstractPage {

    @FindBy(css = ".widget-products ul li.item > a")
    private List<WebElementFacade> newProductsSectionList;

    @FindBy(css = ".nav-5.parent > a")
    private WebElementFacade saleHeaderOption;

    @FindBy(css = "li[class=\"level0 nav-2 parent\"]")
    private WebElementFacade menHeaderOption;

    @FindBy(css = ".level0.nav-1.first.parent>a")
    private WebElementFacade womenHeaderOption;

    @FindBy(css = "#newsletter")
    private WebElementFacade newsletterInputField;

    @FindBy(css = ".success-msg>ul>li>span")
    private WebElementFacade subscriptionSuccessMessage;

    @FindBy(css = "button[title=\"Subscribe\"]")
    private WebElementFacade newsletterSubscribeButton;

    @FindBy(css = ".validation-advice")
    private WebElementFacade validationAdvice;
    //Agota-10.03.2020
    @FindBy(css = ".nav-primary>li")
    private List<WebElement> categoryList;

    //

    public void clickOnMenHeaderOption() {
        menHeaderOption.click();
    }

    public void clickOnSaleHeaderOption() {
        saleHeaderOption.click();
    }

    public void womenHeaderOption() {
        womenHeaderOption.click();
    }

    public void fillInNewsletterInput(String emailAddress) {
        newsletterInputField.clear();
        newsletterInputField.sendKeys(emailAddress);
    }

    public void clickNewsletterSubscribe() {
        newsletterSubscribeButton.click();
    }

    public String getSubscriptionSuccessMessage() {
        if (validationAdvice.isCurrentlyVisible()) {
            return validationAdvice.getText();
        }

        String message1 = subscriptionSuccessMessage.getText();

        return message1;

    }

    public boolean isSubscriptionSuccessMessageVisible() {
        return subscriptionSuccessMessage.isVisible();
    }

    public void selectRandomProductFromNewProductsOnHomepage() {
        Random productsSectionSize = new Random();
        WebElementFacade randomNewProducts = newProductsSectionList.get(productsSectionSize.nextInt(newProductsSectionList.size()));
        randomNewProducts.click();
    }

    //Agota 10.03.2020

    public List<WebElement> getCategoryList() {
        return categoryList;
    }

    //return list of subcategories of a category
    public List<WebElement> getSubcategoryListOfACategory(WebElement category) {

        List<WebElement> subcategories = category.findElements(By.cssSelector(" ul>li"));
        return subcategories;
    }

    public WebElement getWebElementFromListByText(List<WebElement> list, String itemName) {
        for (WebElement item : list) {
            if (item.getText().toLowerCase().equals(itemName.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public void clickSubcategory(WebElement category) {
        category.click();

    }

    public void hoverOnCategory(WebElement category) {
        Actions action = new Actions(getDriver());
        action.moveToElement(category).build().perform();
    }

    //
    //11.03.2020
    public WebElement getCategory(String searchedCat) {
        //list of categories
        List<WebElement> categories = getCategoryList();
        for (WebElement category : categories) {
            if (category.getText().toLowerCase().equals(searchedCat.toLowerCase())) {
                return category;
            }
        }
        return null;
    }

    public void clickOnSubcategoryOfACategory(String subcategory, String category) {
        WebElement cat = getCategory(category);
        hoverOnCategory(cat);
        getWebElementFromListByText(getSubcategoryListOfACategory(cat), subcategory).click();
    }

}
