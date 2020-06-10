package com.firestarters.page;

import com.firestarters.models.Cart;
import com.firestarters.models.Product;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.firestarters.tools.utils.Utils.*;

@DefaultUrl("http://qa2.dev.evozon.com/checkout/cart/")
public class CartPage extends AbstractPage {

    @FindBy(css = "#shopping-cart-table > tbody tr")
    private List<WebElementFacade> cartProductsList;
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
    @FindBy(css = ".count")
    private WebElement nrOfProductsFromCart;

    @FindBy(css = ".skip-account>span:nth-child(2)")
    private WebElement account;
    @FindBy(css = ".top-link-cart ")
    private WebElement myCart;
    //micart from header
    @FindBy(css = ".header-minicart span:nth-child(2)")
    private WebElement miniCart;
    @FindBy(css = "#cart-sidebar>li .product-details")
    private List<WebElement> miniCartRecentlyAddedProd;

    public int getNumberOfElementsFromCartProductsList() {

        return cartProductsList.size();
    }

    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
    }

    public List<WebElementFacade> getCartProductsList() {
        return cartProductsList;
    }

   public WebElement getTotalPrice() {
        return totalPrice;
   }

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
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (WebElementFacade product : productList) {
            Product cartProduct = new Product();
            cartProduct
                    .setPrice(Double.parseDouble(product.findElement(By.cssSelector("td[class='product-cart-price']")).getText().replaceAll("[^0-9.]+", "")));
            cartProduct.setQty(Integer.parseInt(product.findElement(By.cssSelector("td[class='product-cart-actions']>input")).getAttribute("value")));
            cartProduct.setName(product.findElement(By.cssSelector(" .product-name>a")).getText());
            cartProduct.setColor(product.findElement(By.cssSelector("td.product-cart-info dd:nth-child(2)")).getText());
            cartProduct.setSize(product.findElement(By.cssSelector(" dd:nth-child(4)")).getText());
            cartProduct.setSubtotal(Double.parseDouble(product.findElement(By.cssSelector("td[class='product-cart-total'] span[class='price']")).getText()
                    .replaceAll("[^0-9.]+", "")));
            products.add(cartProduct);
        }
        return products;
    }
    public double getTheSumOfSubtotals(List<Product> producs) {
        double sum = 0;
        for (Product p : producs) {
            sum = sum + p.getSubtotal();
        }
        return sum;
    }

    public double totalPriceAsDouble() {
        return extractNumberFromString(getTotalPrice().getText());
    }

    public double getTax() {
        try {
            return Double.parseDouble(getDriver().findElement(By.xpath("//tr/td[contains(text(),'Tax')]/following-sibling::td/span")).getText()
                    .replaceAll("[^0-9.]+", ""));
        } catch (NoSuchElementException e) {
            return 0;
        }
    }


    public double getSubtotal() {
        return Double.parseDouble(getDriver().findElement(By.xpath("//tr/td[contains(text(),'Subtotal')]/following-sibling::td/span")).getText()
                .replaceAll("[^0-9.]+", ""));
    }



    public String getNrOfProductsFromCart() {
        return nrOfProductsFromCart.getText();
    }

    public WebElementFacade getProceedToCheckoutButton() {
        return proceedToCheckoutButton;
    }

    public Cart getTotalPricesForOrderReview(List<Product> products) {
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
    public int sumOfQtys(List<Product> products) {
        int sum = 0;
        for (Product prod : products) {
            int qty = prod.getQty();
            sum = sum + qty;

        }
        return sum;
    }

    public String convertIntToString(int nr) {
        return String.valueOf(nr);
    }

    //remove product from cart
    public void removeProductFromAddedProdList(String name, List<Product> products) {
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
            System.out.println("prod name:"+productName);
            if (productName.toLowerCase().contentEquals(name.toLowerCase())) {
                prod.findElement(By.cssSelector("td[class='product-cart-actions']>input")).clear();
                prod.findElement(By.cssSelector("td[class='product-cart-actions']>input")).sendKeys(Integer.toString(value));
                prod.findElement(By.cssSelector("button[class='button btn-update']")).click();
                break;
            }

        }

    }

    public void modifyProductQty(String name, String value, List<Product> products) {
        for (Product prod : products) {
            String productName = prod.getName();
            if (productName.equals(name)) {
                prod.setQty(Integer.parseInt(value));
                int qty = prod.getQty();
                double price = prod.getPrice();
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
    public List<Product> getMiniCartRecentlyAddedProd(){
        List<Product> miniProducts =new ArrayList<>();
        List<WebElement> miniCartProductsUi=miniCartRecentlyAddedProd;
        System.out.println("size is:"+miniCartProductsUi.size());
        for(WebElement prod:miniCartProductsUi){
            String name=prod.findElement(By.cssSelector(".product-name")).getText();
            //System.out.println(name);
            String qty=prod.findElement(By.cssSelector(".qty-wrapper input")).getAttribute("value");
            //System.out.println(qty);
            double doubleQty=convertStringToDouble(qty);
            String price=prod.findElement(By.cssSelector(".price")).getText();
            // System.out.println(price);
            double doublePrice=convertStringToDouble(stringReplace(price));
            Product product =new Product();
            product.setName(name);
            product.setPrice(doublePrice);
            int qtyAsint=Integer.parseInt(qty);
            product.setQty(qtyAsint);
            //product.setSubtotal();
            miniProducts.add(product);
        }
        return miniProducts;
    }
    public void modifyProductQtyFromMiniCart(String name,String qty){
        List<WebElement> miniCartProductsUi=miniCartRecentlyAddedProd;
        for(WebElement prod:miniCartProductsUi){
            String prodName=prod.findElement(By.cssSelector(".product-name")).getText();
            if(prodName.equals(name)){
                //prod.findElement(By.cssSelector(".qty-wrapper>td>input")).clear();
                WebElement inputfield=prod.findElement(By.cssSelector(".qty-wrapper>td>input"));
                inputfield.sendKeys(Keys.chord(Keys.CONTROL, "a"), qty);
                //withTimeoutOf(Duration.ofSeconds(30));
                //prod.findElement(By.cssSelector(".qty-wrapper input")).sendKeys(qty);
                clickOnWebElem(prod.findElement(By.cssSelector("button")));
                break;
            }
        }

    }
    public void removeMiniCartProduct(String name){
        List<WebElement> miniCartProductsUi=miniCartRecentlyAddedProd;
        System.out.println("mini cart added prod:"+ miniCartProductsUi.size());
        for(WebElement prod:miniCartProductsUi) {
            String prodName=prod.findElement(By.cssSelector(".product-name")).getText();
            if(prodName.equals(name)){
                System.out.println("intra in porcaria asta de if");
                clickOnWebElem(prod.findElement(By.cssSelector("a[title='Remove This Item']")));
                getDriver().switchTo().alert().accept();
                waitABit(5000);
                break;
            }
        }
        waitABit(5000);
    }
    public double getTotalPriceAsDouble() {
        return Double.parseDouble(totalPrice.getText().replaceAll("[^0-9.]+", ""));
    }


}
