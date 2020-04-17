package test;

import com.firestarters.models.Cart;
import com.firestarters.steps.CartPageSteps;
import com.firestarters.steps.HomepageSteps;
import com.firestarters.steps.ProductDetailsSteps;
import com.firestarters.steps.ProductPageSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;

@RunWith(SerenityRunner.class)
public class Test001VerifyCart extends BaseTest {
    @Steps
    private HomepageSteps homepageSteps;
    @Steps
    private ProductPageSteps productPageSteps;
    @Steps
    private ProductDetailsSteps productDetailsSteps;
    @Steps
    private CartPageSteps cartPageSteps;

    @Test
    public void test001VerifyCart() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        String firstProductName = "ELIZABETH KNIT TOP";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(firstProductName);
        productDetailsSteps.addDetailedProductToCart(2);
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getAccount());
        cartPageSteps.verifyMyAccountMyCartNrOfItems();

        String secondProductName = "LAFAYETTE CONVERTIBLE DRESS";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(secondProductName);
        productDetailsSteps.addDetailedProductToCart(2);
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getAccount());
        cartPageSteps.verifyMyAccountMyCartNrOfItems();

        String thirdProductName = "TORI TANK";
        homepageSteps.clickOnSubcategoryOfACategory("New Arrivals", "Women");
        productPageSteps.openProduct(thirdProductName);
        productDetailsSteps.addDetailedProductToCart(1);
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getAccount());
        cartPageSteps.verifyMyAccountMyCartNrOfItems();

        cartPageSteps.modifyProductQuantityFromCart(secondProductName, 4);
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getAccount());
        cartPageSteps.verifyMyAccountMyCartNrOfItems();

        cartPageSteps.removeProductFromCart(firstProductName);
        cartPageSteps.verifyCartDetails();
        cartPageSteps.clickOnWebElem(cartPageSteps.getAccount());
        cartPageSteps.verifyMyAccountMyCartNrOfItems();

        //assertul e false pentru ca in header la Cart(0) am tot timpul asa,indiferent de cate produse am in cart-deci e bug
        cartPageSteps.verifyNrOfProductsFromHeaderCart();
        //minicart
        cartPageSteps.clickOnWebElem(cartPageSteps.getMiniCart());
        cartPageSteps.checkIfFoundRecentlyAddedProdInCartList();

        //cartPageSteps.clickOnWebElem(cartPageSteps.getMiniCart());
        cartPageSteps.modifyProductQtyFromMiniCart("LAFAYETTE CONVERTIBLE DRESS","5");
        webdriver.navigate().refresh();
        cartPageSteps.clickOnWebElem(cartPageSteps.getMiniCart());
        cartPageSteps.checkIfFoundRecentlyAddedProdInCartList();
        cartPageSteps.verifyCartDetails();








    }
    //Hello
}
