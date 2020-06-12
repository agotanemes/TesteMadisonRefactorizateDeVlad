package com.firestarters.steps.http;

import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.utils.JsoupUtils;
import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.utils.JsoupUtils;

import java.util.HashMap;
import java.util.Map;

public class CartHttpSteps extends AbstractHttpSteps {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Step
    public void emptyCart() {
        //TODO nu mai pune commenturi in cod, e informatie inutila si face codul nelizibil
        Response cartPage = getRequest(HttpUrlConstants.OPEN_CART);
        //cantitatile produselor din cart(elem din cart)
        Elements cartItems = JsoupUtils.extractElementsAttributesFromHtml(cartPage.asString(), ".product-cart-actions input");
        //daca cart-ul nu e gol
        if (!cartItems.isEmpty()) {
            //se ia formkey-ul cartului
            String formKey = JsoupUtils.extractElementAttributeFromHtml(cartPage.asString(), "input[name='form_key']", "value");
            Map<String, String> params = new HashMap<String, String>();
            //cheia e de fapt numele elementului, care e un button, iar valoarea este valoarea elementului
            //asa ar arata selectorul: button[name='update_cart_action'][value='empty_cart']
            params.put("update_cart_action", "empty_cart");
            //exact asa e si la form_key
            params.put("form_key", formKey);
            for (Element cartItem : cartItems) {
                params.put(cartItem.attr("name"), cartItem.attr("value"));
            }
            postRequest(HttpUrlConstants.UPDATE_CART, params);
        }
    }
}
