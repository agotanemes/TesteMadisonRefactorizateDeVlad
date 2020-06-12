package test;

import com.firestarters.steps.http.LoginHttpSteps;
import com.firestarters.steps.http.RegisterHttpSteps;
import com.firestarters.steps.ui.LoginSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RegisterTestHttpCalls extends BaseTest {
    @Steps
    private RegisterHttpSteps registerHttpSteps;
    @Steps
    private LoginSteps loginSteps;

    @Before
    public void registerSetup() {
        registerHttpSteps.register();
    }

    @Test
    public void registerTest() {
        loginSteps.loginWithNewRegisteredUser();
        //TODO musai si un pas de verificare, ca sa aiba sens testul
    }

}
