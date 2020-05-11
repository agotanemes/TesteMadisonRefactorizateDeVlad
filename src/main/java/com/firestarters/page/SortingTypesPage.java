package com.firestarters.page;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.firestarters.tools.utils.Utils;

import static com.firestarters.tools.utils.Utils.convertStringToDouble;
import static com.firestarters.tools.utils.Utils.stringReplace;

public class SortingTypesPage extends GeneralPage {
    @FindBy(css="select[title='Sort By']")
    private WebElement sortingTypes;
    @FindBy(css=".products-grid>li")
    private List<WebElement> productList;
    public List<WebElement> getProductList(){
        return productList;
    }
    public void selectSortingType(String sortingType){
        clickOn(sortingTypes);
        Select sortingSelect=new Select(sortingTypes);
        sortingSelect.selectByVisibleText(sortingType);
    }
    public ArrayList<String> getProductsNames(){
        ArrayList<String> productsNames=new ArrayList<>();
        for(WebElement prod:productList){
            String productName=prod.findElement(By.cssSelector(".product-info .product-name")).getText();
            productsNames.add(productName);
        }
        return productsNames;
    }
    public ArrayList<String> getProdListSortedByName(){
        ArrayList<String> productsName=getProductsNames();
        Collections.sort(productsName);
        return productsName;
    }
    public ArrayList<Double> getProductsPrices(){
        ArrayList<Double> productsPrices=new ArrayList<>();
        for(WebElement prod:productList){
            String productPrice=prod.findElement(By.cssSelector(".product-info .price-box")).getText();
            double price= convertStringToDouble(stringReplace(productPrice));
            Double prodPrice=new Double(price);
            productsPrices.add(prodPrice);
        }
        return productsPrices;
    }
    public ArrayList<Double> getProdlistSortedByPrice(){
        ArrayList<Double> productsPrices=getProductsPrices();
        Collections.sort(productsPrices);
        return productsPrices;
    }
}
