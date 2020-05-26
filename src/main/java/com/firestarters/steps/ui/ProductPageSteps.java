package com.firestarters.steps.ui;

import com.firestarters.page.ProductListPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ProductPageSteps {
    @Steps
    ProductListPage productListPage;

    //Ciuverca Ionut
    @Step
    public void selectAProduct() {
        productListPage.selectAProduct();
    }

    //Ciuverca Ionut
    @Step
    public void selectAdToCompare() {
        productListPage.clickOnAddToCompare();
    }

    //Ciuverca Ionut
    @Step
    public void addToCompare() {
        productListPage.addToCompare();
    }

    //Agota 10.03.2020
    @Step
    public void openProduct(String name) {
        productListPage.openProduct(name);
    }

    //
}
