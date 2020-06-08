package test;

import com.firestarters.steps.http.*;
import com.firestarters.steps.ui.HeaderSteps;
import com.firestarters.steps.ui.LoginSteps;
import com.firestarters.steps.ui.WishListSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class WishListTestHttpCalls extends BaseTest {
    @Steps
    private LoginHttpSteps loginHttpSteps;
    @Steps
    private HomeHttpSteps homeHttpSteps;
    @Steps
    private ProductDetailsHttpSteps productDetailsHttpSteps;
    @Steps
    private ProductsHttpSteps productsHttpSteps;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private WishListHttpSteps wishListHttpSteps;
    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private WishListSteps wishListSteps;
    @Before
    public void wishListSetup() {
        loginHttpSteps.login();
        wishListHttpSteps.emptyWishList();
        homeHttpSteps.getProductsFromASubcategory("New Arrivals", "Women");
        homeHttpSteps.addProductToWishList("Elizabeth Knit Top");
        homeHttpSteps.getProductsFromASubcategory("New Arrivals", "Women");
        homeHttpSteps.addProductToWishList("Lafayette Convertible Dress");


    }
    @Test
    public void addProductToWishlist(){
        loginSteps.login();
        headerSteps.openWishList();
        wishListSteps.verifyWishListDetails();

    }

}
