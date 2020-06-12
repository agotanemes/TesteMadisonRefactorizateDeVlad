package com.firestarters.steps.http;

import com.firestarters.models.User;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.JsoupUtils;
import com.firestarters.tools.utils.SerenitySessionUtils;
import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.apache.commons.collections4.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

import static com.firestarters.factory.UserFactory.getUserForRegister;

public class RegisterHttpSteps extends AbstractHttpSteps {
    private static final long serialVersionUID = 1L;

    @Step
    //TODO un nume mai sugestiv, e prea general asa
    public void register() {
        Response registerPage = getRequest(HttpUrlConstants.REGISTER_GET);
        extraHeaders.put("Cookie", registerPage.getHeader("Set-Cookie"));
        String formKey = JsoupUtils.extractElementAttributeFromHtml(registerPage.asString(), "input[name='form_key']", "value");
        Map<String, String> params = new HashMap<String, String>();
        User user = getUserForRegister();
        params.put("firstname", user.getFirstName());
        params.put("lastname", user.getLastName());
        params.put("email", user.getEmail());
        params.put("password", user.getPass());
        params.put("confirmation", user.getConfirmPass());
        params.put("form_key", formKey);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.USER_FOR_REGISTER, user);
        Response registerResponse = postRequest(HttpUrlConstants.REGISTER_POST, params);
        extraHeaders.put("Cookie", registerResponse.getHeaders().getList("Set-Cookie").get(0).getValue());
    }
}
