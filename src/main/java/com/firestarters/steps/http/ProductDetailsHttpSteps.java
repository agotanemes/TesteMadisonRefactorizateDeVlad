package com.firestarters.steps.http;

import com.firestarters.models.CartProduct;
import net.thucydides.core.annotations.Step;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.JsoupUtils;
import com.firestarters.tools.utils.SerenitySessionUtils;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailsHttpSteps extends AbstractHttpSteps {
    private static final long serialVersionUID = 1L;

    public CartProduct getDetailedProductInstanceFromPage(String html) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setName(JsoupUtils.extractElementTextFromHtml(html, ".product-name h1").toUpperCase());
        cartProduct.setPrice(Double.parseDouble(JsoupUtils.extractElementTextFromHtml(html, ".price-box .price").replaceAll("[^0-9.]+", "")));
        return cartProduct;
    }

    @Step
    public void addDetailedProductToCart(int quantity, String color, String size) {
        //luam pagina care contine detalii despre produs ca String
        String detailedProductHtml = SerenitySessionUtils.getFromSession(SerenityKeyConstants.HTML_RESPONSE_PRODUCT_DETAILS);
        //key-urile sunt defapt parametrii de care avem nevoie in request si valorile le luam din pagina de product details care e convertita in string
        Map<String, String> params = new HashMap<String, String>();
        params.put("product", JsoupUtils.extractElementAttributeFromHtml(detailedProductHtml, ".no-display input[name='product']", "value"));
        params.put("form_key", JsoupUtils.extractElementAttributeFromHtml(detailedProductHtml, "input[name='form_key']", "value"));
        params.put("qty", String.valueOf(quantity));
        //facem replace de option pentru ca pe noi din id ne itereseaza doar numarul nu option+nr
        params.put("super_attribute[92]", JsoupUtils.extractElementAttributeFromHtml(detailedProductHtml, ".option-" + color, "id").replace("option", ""));
        params.put("super_attribute[180]", JsoupUtils.extractElementAttributeFromHtml(detailedProductHtml, ".option-" + size, "id").replace("option", ""));
        String addProductToCartPath = JsoupUtils.extractElementAttributeFromHtml(detailedProductHtml, ".product-essential form[action]", "action").replace(
                Constants.BASE_URI, "");

        postRequest(addProductToCartPath, params);

        CartProduct cartProduct = getDetailedProductInstanceFromPage(detailedProductHtml);
        cartProduct.setQty(quantity);
        cartProduct.setColor(color);
        cartProduct.setSize(size);
        cartProduct.setSubtotal(cartProduct.getQty() * cartProduct.getPrice());
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.CART_PRODUCTS_LIST, cartProduct);
    }
}
