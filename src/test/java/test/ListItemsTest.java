package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.ListItemsPage;
import util.BrowserFactory;

public class ListItemsTest {
	WebDriver driver;
	ListItemsPage listItemsPage;
	String categoryName;
		
	@BeforeMethod
	public void initDriver() {	
		driver = BrowserFactory.init();
		listItemsPage = PageFactory.initElements(driver, ListItemsPage.class);
	}	

	
	// Test 1: Validate a user is able to add a category and once the category is added it should display.

	@Test (priority=1)
	@Parameters({"categoryName"})
	public void validateAddCategoryItemIsVisibleOrNot(String categoryName) throws Exception {		
		
		
		listItemsPage.enterCategoryName(categoryName);
		listItemsPage.clickAddCategoryButton();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Vir232323')]")));
		
		System.out.println("This validation is from Test1: ");
		listItemsPage.validateAddedCategoryIsAddedOrNot(categoryName);	
	}
	
	
	//Test 2: Validate a user is not able to add a duplicated category. 
	//If it does then the following message will 
	//display: "The category you want to add already exists: <duplicated category name>."

	@Test (priority=2)
	@Parameters({"duplicateCategoryName"})
	public void validateCategoryCannotBeDuplicated(String duplicateCategoryName) throws Exception {	
		try {
			Thread.sleep(3000);
		listItemsPage.enterDuplicateCategoryName(duplicateCategoryName);
		listItemsPage.clickAddCategoryButton();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Category Item Cannot Be Added Again. This Category Name Already Exists");		
		}
		System.out.println("This validation is from Test2: ");
		
		listItemsPage.validateDuplicateCategoryPage();	
	}
	
	
	//Test 3: Validate the month drop down has all the months (jan, feb, mar ...) 
	//in the Due Date dropdown section.

	@Test (priority=3)
	public void validateMonthDropDownOptions() throws Exception {	
		
		// listItemsPage.selectDueMonthDropdownVisibilitymain();
		 listItemsPage.selectDueMonthDropdownVisibility();	
		 System.out.println("This validation is from Test3: ");
	}

	@AfterMethod
	public void tearDown() {
			driver.close();
			driver.quit();
	}
}
