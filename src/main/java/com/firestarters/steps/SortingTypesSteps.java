package com.firestarters.steps;

import com.firestarters.page.SortingTypesPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;

public class SortingTypesSteps {
    SortingTypesPage sortingTypesPage;
    @Step
    public void selectSortingType(String sortingType){
        sortingTypesPage.selectSortingType(sortingType);
    }

    @Step
    public void verifyProductListAfterNameSort(){
        ArrayList<String> actual=sortingTypesPage.getProductsNames();
        ArrayList<String> expected=sortingTypesPage.getProdListSortedByName();
        for(int i=0;i<actual.size();i++){
            Assert.assertTrue(actual.get(i).equals(expected.get(i)));
        }
    }
    @Step
    public void verifyProductListAfterPriceSort(){
        ArrayList<Double> actual=sortingTypesPage.getProductsPrices();
        ArrayList<Double>expected=sortingTypesPage.getProdlistSortedByPrice();
        for(int i=0;i<actual.size();i++){
            Assert.assertTrue(actual.get(i).equals(expected.get(i)));
        }
    }

}
