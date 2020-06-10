package com.firestarters.steps.ui;

import com.firestarters.models.Product;
import com.firestarters.page.ProductDetailsPage;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class ProductDetailsSteps {

    ProductDetailsPage productDetailsPage;
    @Step
    public Product getDetailedProductInstanceFromUi(int quantity) {
        Product product = new Product();
        product.setQty(quantity);
        product.setColor(productDetailsPage.getRandomColorValue());
        product.setSize(productDetailsPage.getRandomSizeValue());
        product.setName(productDetailsPage.getProductName());
        double price=productDetailsPage.getProductPrice();
        product.setPrice(price);
        product.setSubtotal(quantity*price);
        return product;
    }

    @Step
    public void addDetailedProductToCart(int quantity) {
        //facem un cart product la care ii setam prop(culoare random, size random dintre cele available pentru culoarea respeciva...)
        Product product = getDetailedProductInstanceFromUi(quantity);
        //setam culoarea,size-ul si cantitatea produsului si pe ui,pe care le luam de la CartProduct-ul construit
        productDetailsPage.selectColor(product.getColor());
        productDetailsPage.selectSize(product.getSize());
        productDetailsPage.selectQty(Integer.toString(quantity));
        clickAddToCartButton();
        //adaugam CartProduct-ul construit si pe sesiune
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, product);
    }

    @Step
    public void clickAddToCartButton() {
        productDetailsPage.clickAddToCartButton();
    }

    public Product findProductInList(Product searchedProduct, List<Product> products) {
        for (Product product : products) {
            if (product.getName().contentEquals(searchedProduct.getName())) {
                return product;
            }
        }
        return null;
    }


}
