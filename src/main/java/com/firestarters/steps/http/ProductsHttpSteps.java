package com.firestarters.steps.http;

import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.JsoupUtils;
import com.firestarters.tools.utils.SerenitySessionUtils;

public class ProductsHttpSteps extends AbstractHttpSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void openProduct(String name) {
        //luam produsele de pe sesiune pentru categoria si subcategoria alese-vezi pas anterior getProductsFromASubcategory("New Arrivals", "Women");
        String productsPage = SerenitySessionUtils.getFromSession(SerenityKeyConstants.HTML_RESPONSE_PRODUCTS);
        //ne intereseaza path-ul produsului cu numele Elizabeth Knit Top
        //in lista de produse cautam produsul cu acest nume si ii luam href-ul si inlocuim BASE_URI-ul cu nimic
        //href: http://qa2.dev.evozon.com/women/new-arrivals/elizabeth-knit-top-490.html
        String productPath = JsoupUtils.extractElementAttributeFromHtml(productsPage, "a[title='" + name + "']", "href").replace(Constants.BASE_URI, "");
        //facem un request la pagina cu href-ul extras
        Response productDetailsPage = getRequest(productPath);
        // iarasi salvam pe sesiune tot html=ul produsului selectat, pentru a putea selecta apoi marimea si culoarea
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.HTML_RESPONSE_PRODUCT_DETAILS, productDetailsPage.asString());
    }

}
