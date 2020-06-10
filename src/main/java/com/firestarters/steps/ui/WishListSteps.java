package com.firestarters.steps.ui;

import com.firestarters.models.Cart;
import com.firestarters.models.Product;
import com.firestarters.models.Wishlist;
import com.firestarters.page.WishListPage;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;

import java.util.List;

public class WishListSteps extends ScenarioSteps {
    private WishListPage wishListPage;
    private static final long serialVersionUID = 1L;

    @Step
    public void verifyWishListDetails(){
        Wishlist expectedWishList = new Wishlist((List<Product>) SerenitySessionUtils.getFromSession(SerenityKeyConstants.WISHLIST_PRODUCTS_LIST));
        Wishlist actualWishList= new Wishlist();
        actualWishList.setWishlistProducts(wishListPage.getProductsFromWishList());
        System.out.println("Expected wishlist is: " + expectedWishList.toString());
        System.out.println("Actual wishlist is: " + actualWishList.toString());
        Assert.assertTrue("Cart details are not as expected!", expectedWishList.equals(actualWishList));
        }
    }

