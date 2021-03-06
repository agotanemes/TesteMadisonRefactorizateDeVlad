package com.firestarters.steps.ui;

import com.firestarters.models.Cart;
import com.firestarters.models.Product;
import com.firestarters.page.CartPage;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartPageSteps {

    CartPage cartPage;

    //Ciuverca Ionut
    @Step
    public void proceedToCheckout(int expectedSize) {
        assertEquals(expectedSize, cartPage.getNumberOfElementsFromCartProductsList());
        cartPage.proceedToCheckout();
    }


    @Step
    public void proceedToCheckoutForVerify() {
        cartPage.proceedToCheckout();
    }

    @Step
    public void proceedToCheckout() {
        assertEquals(2, cartPage.getNumberOfElementsFromCartProductsList());
        cartPage.proceedToCheckout();
    }

    //Agota 12.03.2020


    @Step
    public void verifyIfProductTableIsDisplayed() {
        assertTrue(cartPage.getProductTable().isDisplayed());
    }

    //Agota 13.03.2020
    @Step
    public List<Product> getProducts() {
        return cartPage.getProducts();
    }

    //
    //13.03.2020

    //
    @Step
    public double getTax() {
        return cartPage.getTax();
    }

    @Step
    public double getSubtotal() {
        return cartPage.getSubtotal();
    }

    @Step
    public void verifyTotals(Cart actual, Cart expected) {
        Double actualSubtotal = actual.getSubtotal();
        Double expectedSubtotal = expected.getSubtotal();
        Double actualTax = actual.getTax();
        Double expectedTax = expected.getTax();
        Double actualGrandTotal = actual.getGrandTotal();
        Double expectedGrandTotal = expected.getGrandTotal();
        Assert.assertTrue(actualSubtotal.equals(expectedSubtotal));
        Assert.assertTrue(actualTax.equals(expectedTax));
        Assert.assertTrue(actualGrandTotal.equals(expectedGrandTotal));
    }

    @Step
    public void verifyNrOfProductsFromHeaderCart() {

        String nrprod = cartPage.getNrOfProductsFromCart();
        Integer size = SerenitySessionUtils.getObjectListSize(SerenityKeyConstants.CART_PRODUCTS_LIST);
        System.out.println("size-ul listei de produse de pe sediune: "+size);
        Integer nrProducts = Integer.parseInt(nrprod);
        Assert.assertFalse(size.equals(nrProducts));
    }

    @Step
    public WebElement getProceedToCheckoutBtn() {
        return cartPage.getProceedToCheckoutButton();
    }

    @Step
    public Cart getTotalPricesForOrderReview(List<Product> products) {
        return cartPage.getTotalPricesForOrderReview(products);
    }

    @Step
    public void clickOnWebElem(WebElement element) {
        cartPage.clickOnWebElem(element);
    }

    @Step
    public WebElement getAccount() {
        return cartPage.getAccount();
    }

    @Step
    public String getMyCartText() {
        return cartPage.getMyCartText();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Step
    public void verifyMyAccountMyCartNrOfItems() {
        Cart expectedCart = new Cart(SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST));
        int sum = cartPage.sumOfQtys(expectedCart.getCartProducts());
        String nrOfItemsExp = cartPage.convertIntToString(sum);
        String accountTextAct = getMyCartText();
        assertTrue(accountTextAct.contains(nrOfItemsExp));
    }

    //delete specified product from cart
    @Step
    public void deleteProductFromCart(String name) {
        cartPage.deleteProductFromCart(name);
    }

    //stergerea unui anumit produs din lista de produse introduse in cart
    @Step
    public void removeProductFromAddedProdList(String name, List<Product> products) {
        cartPage.removeProductFromAddedProdList(name, products);
    }

    @Step
    public void modifyProductQuantityFromCart(String name, int newValue) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //modificam cantitatea produsului pe front
        cartPage.modifyProductQtyFromCart(name, newValue);
        //cautam pe sesiune produsul pe care vrem sa il modificam
        Product modifiedProduct = getCartProductFromSessionByName(name);
        modifiedProduct.setQty(newValue);
        modifiedProduct.setSubtotal(newValue*modifiedProduct.getPrice());
        SerenitySessionUtils.replaceObjectInSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, modifiedProduct, "name",
                name);
    }

    public Product getCartProductFromSessionByName(String name) {
        List<Product> products = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST);
        for (Product product : products) {
            if (product.getName().toLowerCase().contentEquals(name.toLowerCase())) {
                return product;
            }
        }
        return null;
    }

    @Step
    public void removeProductFromCart(String name) {
        cartPage.deleteProductFromCart(name);
        Product removedProduct = getCartProductFromSessionByName(name);
        SerenitySessionUtils.removeObjectFromSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, removedProduct);
    }

    @Step
    public void verifyCartDetails(double taxRate) {
        Cart expectedCart = new Cart((List<Product>) SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST), taxRate);
        Cart actualCart = new Cart();
        actualCart.setCartProducts(cartPage.getProducts());
        actualCart.setGrandTotal(cartPage.getTotalPriceAsDouble());
        actualCart.setTax(cartPage.getTax());
        actualCart.setSubtotal(cartPage.getSubtotal());
        System.out.println("Expected cart is: " + expectedCart.toString());
        System.out.println("Actual cart is: " + actualCart.toString());

         Assert.assertTrue("Cart details are not as expected!", expectedCart.equals(actualCart));
    }

    //minicart
    @Step
    public WebElement getMiniCart(){
        return cartPage.getMiniCart();
    }
    @Step
    public List<Product> getMiniCartRecentlyAddedProd(){
        return cartPage.getMiniCartRecentlyAddedProd();
    }

    @Step
    public void checkIfFoundRecentlyAddedProdInCartList(){
        Cart expectedCart = new Cart(SerenitySessionUtils.getFromSession(SerenityKeyConstants.CART_PRODUCTS_LIST));
        List<Product> productsfromSession=expectedCart.getCartProducts();
        List<Product> recentlyAddedProducts=getMiniCartRecentlyAddedProd();
        int productsSize=productsfromSession.size();
        int recentlyAddedProdListSize=recentlyAddedProducts.size();
        int i=0;
        int j=productsSize-1;
        System.out.println("Begin");
        System.out.println(recentlyAddedProdListSize);
        while(recentlyAddedProdListSize>0){
            //System.out.println("mini: "+searchedProducts.get(i).getQty()+" product "+products.get(j).getQty());
            Assert.assertEquals(productsfromSession.get(j).getQty(),recentlyAddedProducts.get(i).getQty());
            Assert.assertTrue(productsfromSession.get(j).getName().equals(recentlyAddedProducts.get(i).getName()));
            Double price1= productsfromSession.get(j).getPrice();
            Double price2=recentlyAddedProducts.get(i).getPrice();
            //Assert.assertTrue(products.get(i).getProductPrice().equals(cartProducts.get(i).getProductPrice()));
            Assert.assertTrue(price1.equals(price2));
            /*Double subtotal1=productsfromSession.get(j).getSubtotal();
            Double subtotal2=recentlyAddedProducts.get(i).getSubtotal();
            Assert.assertTrue(subtotal1.equals(subtotal2));*/
            recentlyAddedProdListSize=recentlyAddedProdListSize-1;
            j=j-1;
            i=i+1;


        }
    }
    @Step
    public void modifyProductQtyFromMiniCart(String name,String qty) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        cartPage.modifyProductQtyFromMiniCart(name,qty);
        int quantity=Integer.parseInt(qty);
        //cartPage.modifyProductQtyFromCart(name, quantity);
        //cautam pe sesiune produsul pe care vrem sa il modificam
        Product modifiedProduct = getCartProductFromSessionByName(name);
        modifiedProduct.setQty(quantity);
        modifiedProduct.setSubtotal(quantity*modifiedProduct.getPrice());
        SerenitySessionUtils.replaceObjectInSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, modifiedProduct, "name",
                name);
    }
    @Step
    public void removeMiniCartProduct(String name){
        cartPage.removeMiniCartProduct(name);
        Product removedProduct = getCartProductFromSessionByName(name);
        SerenitySessionUtils.removeObjectFromSerenitySessionList(SerenityKeyConstants.CART_PRODUCTS_LIST, removedProduct);

    }


}
