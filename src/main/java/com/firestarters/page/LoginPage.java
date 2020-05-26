package com.firestarters.page;

import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.HttpUrlConstants;


public class LoginPage extends GeneralPage {

    public void login() {
        getDriver().navigate().to(Constants.BASE_URI + HttpUrlConstants.LOGIN_GET);
        getDriver().manage().window().maximize();
        typeInInputWithTitle("Email Address", Constants.EMAIL);
        typeInInputWithTitle("Password", Constants.PASS);
        clickOnWebElemWithText("Login");
    }
}
