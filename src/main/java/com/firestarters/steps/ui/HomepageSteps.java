package com.firestarters.steps.ui;

import com.firestarters.page.HomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class HomepageSteps {

    @Steps
    HomePage homePage;
    @Step
    public void clickOnSubcategoryOfACategory(String subcategory, String category) {
        homePage.clickOnSubcategoryOfACategory(subcategory, category);
    }


}
