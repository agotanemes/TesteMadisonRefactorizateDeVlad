package com.firestarters.page;

import com.firestarters.models.Product;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class WishListPage extends GeneralPage {
    @FindBy(css=".clean-table tbody>tr")
    private List<WebElementFacade> wishListProducts;

    public List<Product> getProductsFromWishList(){
        List<Product> products= new ArrayList<>();
        for (WebElementFacade product :wishListProducts ){
            Product wishListProd = new Product();
            wishListProd.setName(product.findElement(By.cssSelector(".product-image")).getAttribute("title"));
            wishListProd.setPrice(Double.parseDouble(product.findElement(By.cssSelector(".regular-price")).getText().replaceAll("[^0-9.]+", "")));
            wishListProd.setQty(Integer.parseInt(product.findElement(By.cssSelector(".input-text.qty")).getAttribute("value")));
            if (product.getAttribute("innerHTML").contains("View Details")) {
                wishListProd.setColor(product.findElement(By.cssSelector(".item-options dl dt:nth-child(1)+dd")).getAttribute("innerHTML").trim());
                wishListProd.setSize(product.findElement(By.cssSelector(".item-options dl dt:nth-child(3)+dd")).getAttribute("innerHTML").trim());
            }
            products.add(wishListProd);

        }
        return products;
    }
}
