package testSteps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.NavPage;
import pages.VechileConfirmPage;
import webUtility.BaseTest;
import webUtility.FileDetails;
import webUtility.FilesOperations;
import webUtility.ReadFiles;
import webUtility.VechileDetails;

public class VechileVerifications{

	
	private WebDriver newdriver;
	 private HomePage homepage= new HomePage();
	  private NavPage navPage= new NavPage();
	  private VechileConfirmPage vechileConfirmPage= new VechileConfirmPage();
	  private FilesOperations filesOperations= new FilesOperations();
	  private static List<VechileDetails> listallVechileDetails= new ArrayList<>();

		
	@Given("^I search for the file with \"([^\"]*)\" extension$")
	public void i_search_for_the_file_from_path_with_extension(String filetypes) throws Throwable {
		
		newdriver=BaseTest.getInstance().sharedDriver();
		
		
		Thread.sleep(5000);
		ReadFiles readFiles = new ReadFiles();
		String[] filetypeslist= {filetypes};
		String dir_location= System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\";	
		
	    List<FileDetails> filesDetails;
		try {
			filesDetails = readFiles.getFilesDetailsFromPath(filetypeslist, dir_location);
	        assertTrue(filesDetails.size() != 0);
			
			for(int i = 0; i < filesDetails.size(); i++) {
				List<VechileDetails> vechileDetailsFromAFile = filesOperations.getVechileDetailsFromExcellFile(filesDetails.get(i).getFilePath());
				for(int j = 0; j < vechileDetailsFromAFile.size(); j++) {
					this.listallVechileDetails.add(vechileDetailsFromAFile.get(j));
				}
			}
			this.vechileConfirmPage.getmatch_yes();
			this.vechileConfirmPage.clickContinue_Button();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}


	@When("^I compare the vechile registraion details$")
	public void iCompareTheVechileRegistraionDetails() throws Throwable {

		String exp_Colour ;
		String exp_registrationNumber ;
		String exp_Make ;
		
		this.homepage = PageFactory.initElements(newdriver, HomePage.class);
		this.navPage= PageFactory.initElements(newdriver, NavPage.class);
		this.vechileConfirmPage= PageFactory.initElements(newdriver, VechileConfirmPage.class);
 
		for(int i = 0; i < this.listallVechileDetails.size(); i++) {

			
			 exp_Colour = this.listallVechileDetails.get(i).getColour();
			 exp_registrationNumber = this.listallVechileDetails.get(i).getRegistrationNumber();
			 exp_Make = this.listallVechileDetails.get(i).getMake();
			 Thread.sleep(500);
			 checkVechileDetails(exp_registrationNumber);
			
				
				if ((this.vechileConfirmPage.getlabel_Reg_number().trim()).equalsIgnoreCase(exp_registrationNumber))
				{
					//Assert to check the expected values with values found in the application site.
					try {
						assertTrue("Failed: Vehicle make  matched",(this.vechileConfirmPage.getText_make().trim()).contains(exp_Make));
						assertTrue("Failed: Vehicle color matched",(this.vechileConfirmPage.getTextcolour().trim()).contains(exp_Colour));
					} catch (Exception e) {
						System.out.println(e);
					}
				} 
				else {
					System.out.println("Vehicle make not matched, Please check manually");
				}
			
	
		}
	}

	@Then("^The vechile details should match$")
	public void the_vechile_details_should_match() throws Throwable {
	   
	}

	  @After
		public void tearDown() throws Exception {
			newdriver.close();
		}
		private void checkVechileDetails(String registrationNumber) throws InterruptedException {

			this.homepage.click_start_button();
			this.navPage.RegistrationNumber(registrationNumber);
			this.navPage.submitContinueButton();
		}
		
}
