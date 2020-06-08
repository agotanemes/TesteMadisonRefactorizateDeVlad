package com.firestarters.page;

import com.firestarters.models.CartProduct;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends GeneralPage {
    @FindBy(css=".clean-table tbody>tr")
    private List<WebElementFacade> wishListProducts;

    public List<CartProduct> getProductsFromWishList(){
        List<CartProduct> products= new ArrayList<>();
        for (WebElementFacade product :wishListProducts ){
            CartProduct wishListProd = new CartProduct();
            wishListProd.setName(product.findElement(By.cssSelector(".product-image")).getAttribute("title"));
            products.add(wishListProd);
        }
        return products;
    }
}
