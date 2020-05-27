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
        Response wishlistPage = getRequest(HttpUrlConstants.OPEN_WISHLIST);
        Elements wishlistItems = JsoupUtils.extractElementsAttributesFromHtml(wishlistPage.asString(), ".clean-table tbody>tr");
        String formKey = JsoupUtils.extractElementAttributeFromHtml(wishlistPage.asString(), "input[name='form_key']", "value");
        if(!wishlistItems.isEmpty()){
            for (Element wishlistItem : wishlistItems) {
                String id=wishlistItem.attr("id").replace("item_","");
                String url="wishlist/index/remove/item/"+id+"/form_key/"+formKey+"/ HTTP/1.1";
                getRequest(url);

            }
        }

    }

}
