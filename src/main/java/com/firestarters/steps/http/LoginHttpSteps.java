package com.firestarters.steps.http;

import com.jayway.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.utils.JsoupUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginHttpSteps extends AbstractHttpSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void login() {

        Response loginPage = getRequest(HttpUrlConstants.LOGIN_GET);
        extraHeaders.put("Cookie", loginPage.getHeader("Set-Cookie"));
        String formKey = JsoupUtils.extractElementAttributeFromHtml(loginPage.asString(), "input[name='form_key']", "value");

        Map<String, String> params = new HashMap<String, String>();
        params.put("login[username]", Constants.EMAIL);
        params.put("login[password]", Constants.PASS);
        params.put("form_key", formKey);
        Response loginResponse = postRequest(HttpUrlConstants.LOGIN_POST, params);
        //ia primul Set-Cookie din Headers-ul din Response-ul de la reqestul de POST de mai sus
        extraHeaders.put("Cookie", loginResponse.getHeaders().getList("Set-Cookie").get(0).getValue());
    }
}
