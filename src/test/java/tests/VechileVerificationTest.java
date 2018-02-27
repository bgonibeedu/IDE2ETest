package tests;

import static org.junit.Assert.*;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.HomePage;
import pages.NavPage;
import pages.VechileConfirmPage;
import webUtility.FileDetails;  
import webUtility.ReadFiles;
import webUtility.VechileDetails;
import webUtility.FilesOperations;


import webUtility.BaseTest;

public class VechileVerificationTest {
	private WebDriver newdriver;
	 private HomePage homepage= new HomePage();
	  private NavPage navPage= new NavPage();
	  private VechileConfirmPage vechileConfirmPage= new VechileConfirmPage();
	  private FilesOperations filesOperations= new FilesOperations();
	  private static List<VechileDetails> listallVechileDetails= new ArrayList<>();

	  @BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	  @After
		public void tearDown() throws Exception {
			newdriver.close();
		}

	@Test
	public void test() throws InterruptedException, IOException{

		
		ReadFiles readFiles = new ReadFiles();
		String[] filetypes= {"xlsx"};
		String dir_location= System.getProperty("user.dir")+ "\\src\\test\\resources\\TestData\\";	
		
		
		// find and load the data from xlsx file 
	    List<FileDetails> filesDetails;
		try {
			filesDetails = readFiles.getFilesDetailsFromPath(filetypes, dir_location);
	        assertTrue(filesDetails.size() != 0);
			
			for(int i = 0; i < filesDetails.size(); i++) {
				List<VechileDetails> vechileDetailsFromAFile = filesOperations.getVechileDetailsFromExcellFile(filesDetails.get(i).getFilePath());
				for(int j = 0; j < vechileDetailsFromAFile.size(); j++) {
					this.listallVechileDetails.add(vechileDetailsFromAFile.get(j));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String exp_Colour ;
		String exp_registrationNumber ;
		String exp_Make ;

		newdriver= BaseTest.getInstance().sharedDriver();
		
		Thread.sleep(5000);
		this.homepage = PageFactory.initElements(newdriver, HomePage.class);
		this.navPage= PageFactory.initElements(newdriver, NavPage.class);
		this.vechileConfirmPage= PageFactory.initElements(newdriver, VechileConfirmPage.class);
		
 // search and verify Vechile details make and colour
		
		for(int i = 0; i < this.listallVechileDetails.size(); i++) {

			
			 exp_Colour = this.listallVechileDetails.get(i).getColour().trim();
			 exp_registrationNumber = this.listallVechileDetails.get(i).getRegistrationNumber();
			 exp_Make = this.listallVechileDetails.get(i).getMake().trim();
			 Thread.sleep(5000);
			checkVechileDetails(exp_registrationNumber);
			
			if ((this.vechileConfirmPage.getlabel_Reg_number().trim()).equalsIgnoreCase(exp_registrationNumber))
			{
				//Assert to check the expected values with values found in the application site.
		//		try {
					assertTrue("Failed: Vehicle make  matched",(this.vechileConfirmPage.getText_make().trim()).contains(exp_Make));
					assertTrue("Failed: Vehicle color matched",(this.vechileConfirmPage.getTextcolour().trim()).contains(exp_Colour));
			//	} catch (Exception e) {
			//		System.out.println(e);
			//	}
			} 
			else {
				System.out.println("Vehicle make not matched, Please check manually");
			}
			
			this.vechileConfirmPage.getmatch_yes();
			this.vechileConfirmPage.clickContinue_Button();
			
		}
	      
	}
	
	private void checkVechileDetails(String registrationNumber) throws InterruptedException {

		this.homepage.click_start_button();
		this.navPage.RegistrationNumber(registrationNumber);
		this.navPage.submitContinueButton();
	}
	
	

}
