package test;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.firestarters.steps.http.*;
import com.firestarters.steps.ui.CartPageSteps;
import com.firestarters.steps.ui.HeaderSteps;
import com.firestarters.steps.ui.LoginSteps;
import test.BaseTest;

@RunWith(SerenityRunner.class)
public class TestHttpCalls extends BaseTest {

    @Steps
    private HomeHttpSteps homeHttpSteps;
    @Steps
    private ProductsHttpSteps productsHttpSteps;
    @Steps
    private LoginHttpSteps loginHttpSteps;
    @Steps
    private CartHttpSteps cartHttpSteps;
    @Steps
    private ProductDetailsHttpSteps productDetailsHttpSteps;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private CartPageSteps cartSteps;
    @Steps
    private HeaderSteps headerSteps;

    @Before
    public void cartSetup() {
        loginHttpSteps.login();
        cartHttpSteps.emptyCart();
        homeHttpSteps.getProductsFromASubcategory("New Arrivals", "Women");
        productsHttpSteps.openProduct("Elizabeth Knit Top");
        productDetailsHttpSteps.addDetailedProductToCart(2, "Red", "S");
        homeHttpSteps.getProductsFromASubcategory("New Arrivals", "Women");
        productsHttpSteps.openProduct("Lafayette Convertible Dress");
        productDetailsHttpSteps.addDetailedProductToCart(2, "Blue", "10");
    }

    @Test
    public void AddProductToCart() {
        loginSteps.login();
        headerSteps.openCart();
        cartSteps.verifyCartDetails(0.0);
    }

}
