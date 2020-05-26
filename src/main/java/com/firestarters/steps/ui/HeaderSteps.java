package com.firestarters.steps.ui;

import com.firestarters.page.HeaderPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class HeaderSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private HeaderPage headerPage;

    @Step
    public void openCart() {
        headerPage.clickOnWebElemWithText("Cart");
        headerPage.clickOnWebElemWithText("View Shopping Cart");
    }
}
