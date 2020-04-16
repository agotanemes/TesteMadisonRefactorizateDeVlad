package com.firestarters.steps;

import com.firestarters.models.CartProduct;
import com.firestarters.page.ProductDetailsPage;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

public class ProductDetailsSteps {

    ProductDetailsPage productDetailsPage;

    //    @Step
    //    public void selectARandomColor(){
    //        productDetailsPage.selectAColor();
    //    }
    //
    //    @Step
    //    public void selectARandomSize(){
    //        productDetailsPage.selectSize();
    //    }
    //
    //    @Step
    //    public void addToCart(){
    //       productDetailsPage.addToCart();
    //   }

    //Ciuverca Ionut ( here I grouped all the above)
    @Step
    public void configureProductAndAddToCart() {
        productDetailsPage.selectAColor();
        productDetailsPage.selectSize();
        productDetailsPage.clickAddToCartButton();
    }

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

    //Agota 10.03.2020
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
    public void printProducts() {
        List<CartProduct> products = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST);
        for (CartProduct prod : products) {
            System.out.println(prod.toString());
        }
    }

    @Step
    public void clickAddToCartButton() {
        productDetailsPage.clickAddToCartButton();
    }

    //Agota 13.03.2020
    @Step
    public void verifyTwoCartListsAreEqual(List<CartProduct> products, List<CartProduct> cartProducts) {
        int productsSize = products.size();
        int cartproductsSize = cartProducts.size();
        Assert.assertEquals(productsSize, cartproductsSize);
        for (int i = 0; i < products.size(); i++) {
            Assert.assertTrue(products.get(i).getColor().equals(cartProducts.get(i).getColor()));
            Assert.assertTrue(products.get(i).getSize().equals(cartProducts.get(i).getSize()));
            Assert.assertTrue(products.get(i).getName().equals(cartProducts.get(i).getName()));
            Double price1 = products.get(i).getPrice();
            Double price2 = cartProducts.get(i).getPrice();
            //Assert.assertTrue(products.get(i).getProductPrice().equals(cartProducts.get(i).getProductPrice()));
            Assert.assertTrue(price1.equals(price2));
            Double subtotal1 = products.get(i).getSubtotal();
            Double subtotal2 = cartProducts.get(i).getSubtotal();
            Assert.assertTrue(subtotal1.equals(subtotal2));

        }
    }

    //
    // Mihai (corectare metoda verifyTwoCartListsAreEqual)
    @Step
    public void verifyToProductListAreEquals(List<CartProduct> expectedProducts, List<CartProduct> actualProducts) {

        for (CartProduct expectedProduct : expectedProducts) {
            CartProduct actualProduct = findProductInList(expectedProduct, actualProducts);
            Assert.assertTrue("Prices are not correct", expectedProduct.getPrice() == actualProduct.getPrice());
            Assert.assertTrue("Sizes are not correct", expectedProduct.getSize() == actualProduct.getSize());
            Assert.assertTrue("Names are not correct", expectedProduct.getName() == actualProduct.getName());
            Assert.assertTrue("Colors are not correct", expectedProduct.getColor() == actualProduct.getColor());
            Assert.assertTrue("Qty are not correct", expectedProduct.getQty() == actualProduct.getQty());
        }
    }

    public CartProduct findProductInList(CartProduct searchedProduct, List<CartProduct> products) {
        for (CartProduct product : products) {
            if (product.getName().contentEquals(searchedProduct.getName())) {
                return product;
            }
        }
        return null;
    }

    //

}
