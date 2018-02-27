package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(css = ".button")
	private WebElement startNow;
	
	public WebElement getstartNow() {
		return startNow;
	}

	public void click_start_button() {
		startNow.click();
	}
}
