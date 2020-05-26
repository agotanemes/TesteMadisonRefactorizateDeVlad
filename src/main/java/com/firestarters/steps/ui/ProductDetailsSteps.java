package com.firestarters.steps.ui;

import com.firestarters.models.CartProduct;
import com.firestarters.page.ProductDetailsPage;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

public class ProductDetailsSteps {

    ProductDetailsPage productDetailsPage;
    @Step
    public CartProduct getDetailedProductInstanceFromUi(int quantity) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setQty(quantity);
        cartProduct.setColor(productDetailsPage.getRandomColorValue());
        cartProduct.setSize(productDetailsPage.getRandomSizeValue());
        cartProduct.setName(productDetailsPage.getProductName());
        cartProduct.setPrice(productDetailsPage.getProductPrice());
        cartProduct.setSubtotal();
        return cartProduct;
    }

    @Step
    public void addDetailedProductToCart(int quantity) {
        //facem un cart product la care ii setam prop(culoare random, size random dintre cele available pentru culoarea respeciva...)
        CartProduct cartProduct = getDetailedProductInstanceFromUi(quantity);
        //setam culoarea,size-ul si cantitatea produsului si pe ui,pe care le luam de la CartProduct-ul construit
        productDetailsPage.selectColor(cartProduct.getColor());
        productDetailsPage.selectSize(cartProduct.getSize());
        productDetailsPage.selectQty(Integer.toString(quantity));
        clickAddToCartButton();
        //adaugam CartProduct-ul construit si pe sesiune
        SerenitySessionUtils.saveObjectInSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, cartProduct);
    }

    @Step
    public void clickAddToCartButton() {
        productDetailsPage.clickAddToCartButton();
    }

    public CartProduct findProductInList(CartProduct searchedProduct, List<CartProduct> products) {
        for (CartProduct product : products) {
            if (product.getName().contentEquals(searchedProduct.getName())) {
                return product;
            }
        }
        return null;
    }


}
