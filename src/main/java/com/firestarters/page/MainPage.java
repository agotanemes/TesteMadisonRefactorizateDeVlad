package com.firestarters.page;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage{
	
	@FindBy(css = " .nav-5.parent > a")
	private WebElementFacade saleHeaderOption;

	@FindBy(css = "li[class=\"level0 nav-2 parent\"]")
	private WebElementFacade menHeaderOption;

	public void clickOnMenHeaderOption() {
		menHeaderOption.click();
	}

	public void clickOnSaleHeaderOption() {
		saleHeaderOption.click();
	}

}
