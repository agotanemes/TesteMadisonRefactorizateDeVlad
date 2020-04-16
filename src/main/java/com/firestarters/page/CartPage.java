package com.firestarters.page;

import com.firestarters.models.Cart;
import com.firestarters.models.CartProduct;
import com.firestarters.tools.utils.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.firestarters.tools.utils.Utils.*;

@DefaultUrl("http://qa2.dev.evozon.com/checkout/cart/")
public class CartPage extends AbstractPage {

    @FindBy(css = "#shopping-cart-table > tbody tr")
    private List<WebElementFacade> cartProductsList;

    //@FindBy(css = ".button[title*='Proceed']")
    @FindBy(css = ".checkout-types.top .btn-proceed-checkout")
    private WebElementFacade proceedToCheckoutButton;

    @FindBy(css = "#shopping-cart-table > tbody")
    private WebElementFacade listOfProductsInCart;
    @FindBy(css = ".product-cart-actions .link-wishlist")
    private WebElementFacade wishlistBtnInCart;
    @FindBy(css = ".success-msg")
    private WebElementFacade successMsgAddedInWishlist;

    @FindBy(css = "#shopping-cart-table")
    private WebElementFacade productTable;

    //Agota
    @FindBy(css = "#shopping-cart-table>tbody>tr")
    private List<WebElementFacade> productList;
    @FindBy(css = ".a-right>strong>span[class='price']")
    private WebElement totalPrice;
    //@FindBy(css=".a-right span[class='price']")
    //private List<WebElement> totalPriceList;
    @FindBy(css = ".count")
    private WebElement nrOfProductsFromCart;

    //@FindBy(css="skip-link ")
    @FindBy(css = ".skip-account>span:nth-child(2)")
    private WebElement account;
    @FindBy(css = ".top-link-cart ")
    private WebElement myCart;
    //micart from header
    @FindBy(css = ".header-minicart span:nth-child(2)")
    private WebElement miniCart;
    @FindBy(css = "#cart-sidebar>li .product-details")
    private List<WebElement> miniCartRecentlyAddedProd;



    //-------------------
    //Ciuverca Ionut
    public int getNumberOfElementsFromCartProductsList() {

        return cartProductsList.size();
    }

    //Ciuverca Ionut
    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public List<WebElementFacade> getCartProductsList() {
        return cartProductsList;
    }

    //Agota


    public WebElement getTotalPrice() {
        return totalPrice;
    }

    //return Product list from cart
    public List<WebElementFacade> getProductList() {
        return productList;
    }

    public WebElementFacade getProductTable() {
        return productTable;

    }

    public WebElementFacade getWishListBtnInCart() {
        return wishlistBtnInCart;
    }

    public WebElementFacade getSuccessMsgAddedInWishlist() {
        return successMsgAddedInWishlist;
    }

    //12.03.2020



    //13.03.2020
    public List<CartProduct> getProducts() {
        List<CartProduct> products = new ArrayList<>();
        List<WebElementFacade> productListFromWeb = getProductList();
        for (WebElementFacade product : productListFromWeb) {
            CartProduct pr = new CartProduct();
            String price = product.findElement(By.cssSelector(" td[class='product-cart-price']")).getText();
            //qty
            String qty = product.findElement(By.cssSelector("td[class='product-cart-actions']>input")).getAttribute("value");
            Double correctPrice = convertStringToDouble(stringReplace(price));
            //price
            double priceAsdouble = correctPrice.doubleValue();
            //name
            String productName = product.findElement(By.cssSelector(" .product-name>a")).getText();
            //color
            String productColor = product.findElement(By.cssSelector("td.product-cart-info dd:nth-child(2)")).getText();
            //size
            String productSize = product.findElement(By.cssSelector(" dd:nth-child(4)")).getText();
            ;
            //subtotal
            String subtotal = product.findElement(By.cssSelector("td[class='product-cart-total'] span[class='price']")).getText();
            Double correctSubtotal = convertStringToDouble(stringReplace(subtotal));
            double subtotalAsdouble = correctSubtotal.doubleValue();
            pr.setColor(productColor);
            pr.setSize(productSize);
            pr.setQty(Integer.parseInt(qty));
            pr.setName(productName);
            pr.setPrice(priceAsdouble);
            pr.setSubtotal();
            products.add(pr);
        }
        return products;
    }

    public double getTheSumOfSubtotals(List<CartProduct> producs) {
        double sum = 0;
        for (CartProduct p : producs) {
            sum = sum + p.getSubtotal();
        }
        return sum;
    }

    public double totalPriceAsDouble() {
        return extractNumberFromString(getTotalPrice().getText());
    }

    public double getTax() {
        WebElement tax = getDriver().findElement(By.xpath("//tr/td[contains(text(),'Tax')]/following-sibling::td/span"));
        String taxAsString = tax.getText();
        double taxAsDouble = convertStringToDouble(stringReplace(taxAsString));
        //System.out.println("taxa ca double: "+ taxAsDouble);
        return taxAsDouble;
    }

    public double getSubtotal() {
        WebElement subtotal = getDriver().findElement(By.xpath("//tr/td[contains(text(),'Subtotal')]/following-sibling::td/span"));
        double subtotalAsDouble = convertStringToDouble(stringReplace(subtotal.getText()));
        //System.out.println("subtotalul ca double: "+ subtotalAsDouble);
        return subtotalAsDouble;
    }



    public String getNrOfProductsFromCart() {
        return nrOfProductsFromCart.getText();
    }

    public WebElementFacade getProceedToCheckoutButton() {
        return proceedToCheckoutButton;
    }

    //for order review from checkout

    public Cart getTotalPricesForOrderReview(List<CartProduct> products) {
        Cart cart = new Cart();
        cart.setSubtotal(getTheSumOfSubtotals(products));
        cart.setTax(0);
        cart.setGrandTotal(cart.getSubtotal());
        return cart;
    }

    public void clickOnWebElem(WebElement element) {
        element.click();
    }

    public WebElement getAccount() {
        return account;
    }

    public String getMyCartText() {
        return myCart.getText();
    }

    //pt nr de item-uri de la Accout->My Account(x items)
    public int sumOfQtys(List<CartProduct> products) {
        int sum = 0;
        for (CartProduct prod : products) {
            int qty = prod.getQty();
            sum = sum + qty;

        }
        return sum;
    }

    public String convertIntToString(int nr) {
        return String.valueOf(nr);
    }

    //remove product from cart
    public void removeProductFromAddedProdList(String name, List<CartProduct> products) {
        int poz = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                poz = i;
            }
        }
        products.remove(poz);
    }

    public void deleteProductFromCart(String name) {
        List<WebElementFacade> productFromCart = getProductList();
        for (WebElementFacade prod : productFromCart) {
            String productName = prod.findElement(By.cssSelector(" .product-name>a")).getText();

            if (productName.equals(name)) {
                prod.findElement(By.cssSelector("td[class='a-center product-cart-remove last']>a")).click();

                break;
            }
            withTimeoutOf(Duration.ofSeconds(20));
        }
    }

    public void modifyProductQtyFromCart(String name, int value) {
        List<WebElementFacade> productFromCart = getProductList();
        for (WebElementFacade prod : productFromCart) {
            String productName = prod.findElement(By.cssSelector(".product-name>a")).getText();
            if (productName.toLowerCase().contentEquals(name.toLowerCase())) {
                prod.findElement(By.cssSelector("td[class='product-cart-actions']>input")).clear();
                prod.findElement(By.cssSelector("td[class='product-cart-actions']>input")).sendKeys(Integer.toString(value));
                prod.findElement(By.cssSelector("button[class='button btn-update']")).click();

            }

        }

    }

    public void modifyProductQty(String name, String value, List<CartProduct> products) {
        for (CartProduct prod : products) {
            String productName = prod.getName();
            if (productName.equals(name)) {
                prod.setQty(Integer.parseInt(value));
                int qty = prod.getQty();
                double price = prod.getPrice();
                prod.setSubtotal();
                //modifica si subtotalul
                //prod.setSubtotal();
                break;
            }

        }
    }

    public Cart getCartDetails() {
        Cart cart = new Cart();
        return cart;
    }
    //minicart
    public WebElement getMiniCart(){
        return miniCart;
    }
    public List<CartProduct> getMiniCartRecentlyAddedProd(){
        List<CartProduct> miniCartProducts=new ArrayList<>();
        List<WebElement> miniCartProductsUi=miniCartRecentlyAddedProd;
        //System.out.println("size is:"+miniCartProductsUi.size());
        for(WebElement prod:miniCartProductsUi){
            String name=prod.findElement(By.cssSelector(".product-name")).getText();
            //System.out.println(name);
            String qty=prod.findElement(By.cssSelector(".qty-wrapper input")).getAttribute("value");
            //System.out.println(qty);
            double doubleQty=convertStringToDouble(qty);
            String price=prod.findElement(By.cssSelector(".price")).getText();
            // System.out.println(price);
            double doublePrice=convertStringToDouble(stringReplace(price));
            CartProduct cartProduct=new CartProduct();
            cartProduct.setName(name);
            cartProduct.setPrice(doublePrice);
            int qtyAsint=Integer.parseInt(qty);
            cartProduct.setQty(qtyAsint);
            cartProduct.setSubtotal();
            miniCartProducts.add(cartProduct);
        }
        return miniCartProducts;
    }

}
