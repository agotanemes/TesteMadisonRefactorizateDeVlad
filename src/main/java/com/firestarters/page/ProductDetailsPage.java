package com.firestarters.page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.firestarters.tools.utils.Utils.convertStringToDouble;
import static com.firestarters.tools.utils.Utils.stringReplace;

public class ProductDetailsPage extends AbstractPage {

    @FindBy(css = "ul#configurable_swatch_color li:not(.not-available) a")
    private List<WebElementFacade> colorList;

    @FindBy(css = "ul#configurable_swatch_size li:not(.not-available) a")
    private List<WebElementFacade> sizeList;

    @FindBy(css = "div>.btn-cart")
    private WebElementFacade addToCartButton;
    //Agota 10.03.2020
    @FindBy(css = "#qty")
    private WebElementFacade qtyInput;
    //11.03.2020
    @FindBy(css = ".product-shop .product-name span")
    private WebElementFacade productName;
    @FindBy(css = ".price-info  span[id*='product-price']>span")
    private WebElementFacade productPrice;

    //

    //Ciuverca Ionut
    public void selectAColor() {
        Random color = new Random();
        WebElement randomColor = colorList.get(color.nextInt(colorList.size()));
        randomColor.click();
    }

    //Ciuverca Ionut
    public void selectSize() {
        Random size = new Random();
        WebElement randomSize = sizeList.get(size.nextInt(sizeList.size()));
        randomSize.click();
    }

    //Ciuverca Ionut
    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    //Agota 10.03.2020

    public List<WebElementFacade> getColorList() {
        return colorList;
    }

    public List<WebElementFacade> getSizeList() {
        return sizeList;
    }

    public WebElementFacade getQtyInput() {
        return qtyInput;
    }

    //
    public void clickOnWebElem(WebElement elem) {
        elem.click();
    }

    public String getSpecifiedWebAtr(WebElement elem, String atr) {
        return elem.getAttribute(atr);
    }

    public void selectColor(String color) {
        for (WebElementFacade col : colorList) {
            String clr = col.getAttribute("title");
            if (clr.toLowerCase().equals(color.toLowerCase())) {
                clickOnWebElem(col);
            }
        }
    }

    public WebElement getRandomElementFromList(List<WebElementFacade> elements) {
        Random rand = new Random();
        int index = rand.nextInt(elements.size());
        return elements.get(index);
    }

    public String getRandomColorValue() {
        WebElement color = getRandomElementFromList(colorList);
        color.click();
        return color.getAttribute("title");
    }

    public String getRandomSizeValue() {
        WebElement size = getRandomElementFromList(sizeList);
        size.click();
        return size.getText();
    }

    public void selectSize(String size) {
        for (WebElementFacade sizeElem : sizeList) {
            if (sizeElem.getText().toLowerCase().equals(size.toLowerCase())) {
                clickOnWebElem(sizeElem);
            }
        }
    }

    public void selectQty(String qty) {
        qtyInput.clear();
        qtyInput.sendKeys(qty);
    }

    public String getProductName() {
        return productName.getText();
    }

    public double getProductPrice() {
        String price = productPrice.getText();
        Double correctPrice = convertStringToDouble(stringReplace(price));
        double returnedPrice = correctPrice.doubleValue();
        return returnedPrice;
    }

    public double getStringAsdouble(String s) {
        Double correctPrice = convertStringToDouble(stringReplace(s));
        double returnedPrice = correctPrice.doubleValue();
        return returnedPrice;
    }

}
