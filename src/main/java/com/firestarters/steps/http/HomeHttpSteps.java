package com.firestarters.steps.http;

import com.firestarters.models.Product;
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
        String addProductToWishlistPath = JsoupUtils.extractElementAttributeFromHtml(productsPage, "a[title='" + name + "']+div .link-wishlist", "href")
                .replace(Constants.BASE_URI, "");
        System.out.println("path "+ addProductToWishlistPath);
        getRequest(addProductToWishlistPath);
        Product wishListProd= new Product();
        wishListProd.setName(name);
        wishListProd.setQty(1);
        wishListProd.setPrice(Double.parseDouble(JsoupUtils.extractElementTextFromHtml(productsPage, "a[title='" + name + "']+.product-info .price").replaceAll(
                "[^0-9.]+", "")));
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.WISHLIST_PRODUCTS_LIST, wishListProd);
    }
}
