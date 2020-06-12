package com.firestarters.page;

import com.firestarters.models.User;
import com.firestarters.tools.constants.Constants;
import com.firestarters.tools.constants.HttpUrlConstants;
import com.firestarters.tools.constants.SerenityKeyConstants;
import com.firestarters.tools.utils.SerenitySessionUtils;


public class LoginPage extends GeneralPage {

    public void login() {
        getDriver().navigate().to(Constants.BASE_URI + HttpUrlConstants.LOGIN_GET);
        getDriver().manage().window().maximize();
        typeInInputWithTitle("Email Address", Constants.EMAIL);
        typeInInputWithTitle("Password", Constants.PASS);
        clickOnWebElemWithText("Login");
    }
    public void loginWithNewRegisteredUser(){
        getDriver().navigate().to(Constants.BASE_URI + HttpUrlConstants.LOGIN_GET);
        getDriver().manage().window().maximize();
        User user= SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER_FOR_REGISTER);
        typeInInputWithTitle("Email Address", user.getEmail());
        typeInInputWithTitle("Password", user.getPass());
        clickOnWebElemWithText("Login");

    }
}
