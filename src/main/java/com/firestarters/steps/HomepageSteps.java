package com.firestarters.steps;

import com.firestarters.page.HomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class HomepageSteps {

    @Steps
    HomePage homePage;

    @Steps
    ProductDetailsSteps productDetailsSteps;

    @Step
    public void selectProductFromNewProductsOnHomepage() {
        homePage.selectRandomProductFromNewProductsOnHomepage();
    }

    @Step
    public void selectDetails() {
        productDetailsSteps.configureProductAndAddToCart();
    }

    @Step
    public void clickOnSaleSection() {
        homePage.clickOnSaleHeaderOption();
    }

    @Step
    public void clickOnMenSection() {
        homePage.clickOnMenHeaderOption();
    }

    @Step
    public void clickOnWomenSection() {
        homePage.womenHeaderOption();
    }

    @Step
    public void testSubscriptionInput(String emailAddress, String expectedResult) {
        homePage.fillInNewsletterInput(emailAddress);
        homePage.clickNewsletterSubscribe();
        String actualResult = homePage.getSubscriptionSuccessMessage();
        Assert.assertTrue(actualResult.equals(expectedResult));

    }

    //Agota 10.03.2020
    @Step
    public void clickOnSubcategoryOfACategory(String subcategory, String category) {
        homePage.clickOnSubcategoryOfACategory(subcategory, category);
    }
    //

}
