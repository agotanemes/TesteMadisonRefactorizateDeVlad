package com.firestarters.steps.http;

import com.firestarters.models.CartProduct;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.utils.JsoupUtils;
import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;

public class HomeHttpSteps extends AbstractHttpSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void getProductsFromASubcategory(String subcategory, String category) {
        //raspunsul primit e un html
        Response productsPage = getRequest(category + "/" + subcategory + ".html");
        //html-ul respectiv il salvezi pe sesoune ca string
        //la cheia "products html" ii asociezi toata pagina de produse ca si String
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.HTML_RESPONSE_PRODUCTS, productsPage.asString());
    }
    @Step
    public void addProductToWishList(String name){
        //luam toata pagina ce contine lista de produse
        String productsPage = SerenitySessionUtils.getFromSession(SerenityKeyConstants.HTML_RESPONSE_PRODUCTS);
        String addProductToWishlistPath = JsoupUtils.extractElementAttributeFromHtml(productsPage, "a[title='a[title='"+name+"']"+".product-info .actions .link-wishlist", "href").replace(Constants.BASE_URI, "");
        getRequest(addProductToWishlistPath);
        CartProduct wishListProd= new CartProduct();
        wishListProd.setName(name);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.WISHLIST_PRODUCTS_LIST, wishListProd);
    }
}
