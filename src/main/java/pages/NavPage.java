package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavPage {

	@FindBy(id = "Vrm")
	private WebElement reg_number;
	
	@FindBy(name = "Continue")
	private WebElement continue_Button;
	
	public WebElement getRegistrationNumber() {
		return reg_number;
	}

	public WebElement getContinueButton() {
		return continue_Button;
	}

	public void submitContinueButton() {
		continue_Button.click();
	}

	public void RegistrationNumber(String value) {
		reg_number.sendKeys(value);
	}

	
}
