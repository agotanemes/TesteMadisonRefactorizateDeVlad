package com.firestarters.steps.ui;

import com.firestarters.page.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;


public class LoginSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    private LoginPage loginPage;

    @Step
    public void login() {
        loginPage.login();
    }
    @Step
    public void loginWithNewRegisteredUser(){
        loginPage.loginWithNewRegisteredUser();
    }
}
