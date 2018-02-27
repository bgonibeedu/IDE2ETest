package webUtility;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {
	
	public static WebDriver driver;
	public String URL="https://www.gov.uk/get-vehicle-information-from-dvla";
	static BaseTest baseClass;

	 
	public  BaseTest() {}
	public static BaseTest getInstance() {
		if (baseClass == null) {
			baseClass = new BaseTest();
		}
		return baseClass;
	}
	
	public WebDriver sharedDriver() {
		if (driver == null) {
			try {
				

				String exePath = System.getProperty("user.dir")
						+ "/chrome/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", exePath);
				driver = new ChromeDriver();
				driver.get("https://www.gov.uk/get-vehicle-information-from-dvla");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		return driver;
	}
	
	public String getUrl()
	{
		return URL;
	}

}