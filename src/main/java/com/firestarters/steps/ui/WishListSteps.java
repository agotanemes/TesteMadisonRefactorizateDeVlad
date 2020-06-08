package com.firestarters.steps.ui;

import com.firestarters.models.Cart;
import com.firestarters.models.CartProduct;
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
        Cart expectedWishList = new Cart((List<CartProduct>) SerenitySessionUtils.getFromSession(SerenityKeyConstants.WISHLIST_PRODUCTS_LIST));
        Cart actualWishList= new Cart();
        actualWishList.setCartProducts(wishListPage.getProductsFromWishList());
        for(int i=0;i<expectedWishList.getCartProducts().size();i++){
            Assert.assertTrue(actualWishList.getCartProducts().get(i).getName().equals(expectedWishList.getCartProducts().get(i).getName()));

        }
    }
}
