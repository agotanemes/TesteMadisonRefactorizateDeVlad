package com.firestarters.steps.http;

import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.utils.JsoupUtils;
import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WishListHttpSteps extends AbstractHttpSteps {
    @Step
    public void emptyWishList(){
        //intram pe pagina de wishlist
        Response wishlistPage = getRequest(HttpUrlConstants.OPEN_WISHLIST);
        //luam toate produsele din wishlist
        Elements wishlistItems = JsoupUtils.extractElementsAttributesFromHtml(wishlistPage.asString(), ".clean-table tbody>tr");
        //luam si formkey-ul din raspuns
        String formKey = JsoupUtils.extractElementAttributeFromHtml(wishlistPage.asString(), "input[name='form_key']", "value");
        //daca lista de produse nu e goala
        if(!wishlistItems.isEmpty()){
            //luam produsele pe rand
            for (Element wishlistItem : wishlistItems) {
                //luam id fiecarui produs, doar nr nu si item_nr
                String id=wishlistItem.attr("id").replace("item_","");
                //pregatim url-ul spre cxare ne redirecteaza incazul in care facem un remove
                String url="wishlist/index/remove/item/"+id+"/form_key/"+formKey+"/ HTTP/1.1";
                //facem un get de acel url
                getRequest(url);

            }
        }

    }

}
