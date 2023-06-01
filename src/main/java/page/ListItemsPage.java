package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class ListItemsPage extends BasePage{
	
	WebDriver driver;
	public ListItemsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.CSS ,using="input[name='categorydata']") WebElement ADD_CATEGORY_ELEMENT ;
	@FindBy(how = How.CSS ,using="input[value='Add category']") WebElement ADD_CATEGORY_BUTTON_ELEMENT ;
	@FindBy(how = How.XPATH ,using="//span[contains(text(),'Vir232323')]") WebElement ADD_CATEGORY_ITEM_NAME_ELEMENT ;
	@FindBy(how = How.XPATH ,using="//span[1][text()='Vir2323']") WebElement DUPLICATE_ADD_CATEGORY_ITEM_NAME_ELEMENT ;
	
	By dueMonthDropdown = By.xpath("//select[@name='due_month']");
	By removeItemButton = By.cssSelector("input[value='Remove']");
	
	String beforeDropdownMonthName ="//select[3]/option[";
	String afterDropdownMonthName ="]";
	
	
	public void enterCategoryName(String categoryName) {
		ADD_CATEGORY_ELEMENT.sendKeys(categoryName+generateRandomNum(99));
		//driver.findElement(addCategoryField).sendKeys(categoryName+generateRandomNum(99));
	}
	public void clickAddCategoryButton() {
		ADD_CATEGORY_BUTTON_ELEMENT.click();
		//driver.findElement(addCategoryButton).click();
	}
	
	
	public void validateAddedCategoryIsAddedOrNot(String categoryName) {
		
		String addedCategoryItemNameText = ADD_CATEGORY_ITEM_NAME_ELEMENT.getText();
		if (addedCategoryItemNameText.contains(categoryName)) {
			System.out.println("Added Category Item Is Visible");
		}
			else {System.out.println("Added Category Item Does Not Exist");			
			}
		}
	
	public void enterDuplicateCategoryName(String duplicateCategoryName) {
		ADD_CATEGORY_ELEMENT.sendKeys(duplicateCategoryName);
	}
	
	public void validateDuplicateCategoryPage() {
		boolean statusDuplicateCategoryPageTitle = DUPLICATE_ADD_CATEGORY_ITEM_NAME_ELEMENT.isDisplayed();
		if (statusDuplicateCategoryPageTitle != true) {
			System.out.println("Page Cannot Be Found");
		}
			else {
				System.out.print("The category you want to add already exists: ");
				System.out.println(DUPLICATE_ADD_CATEGORY_ITEM_NAME_ELEMENT.getText());
			}	
		}

	
	public void selectDueMonthDropdownVisibility() {
	for (int i=1;i<=13;i++) {
		String selectedMonthName = driver.findElement(By.xpath(beforeDropdownMonthName+i+afterDropdownMonthName)).getText();
		
		System.out.println(selectedMonthName);
		}
	}
	
	public void selectDueMonthDropdownVisibilitymain() {
		String[] arr = {"None","Jan","Feb","Mar", "Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		WebElement dropDown = driver.findElement(By.xpath("//select[@name='due_month']"));
		Select sel = new Select(dropDown);
		List<WebElement> monthOptions = sel.getOptions();
		System.out.println(monthOptions);
		
//		for (WebElement mo : monthOptions) {
//			for (int i=0; i < arr.length; i++) {
//				if (mo.getText().contains(arr[i])) {
//					System.out.println("month dropdown has all months");
//					break;
//				}
//			}
//		}
	}
	
	public String getInsertedCategoryName() {
		return insertedCategoryName;
	}
	
	static String insertedCategoryName;
	public void insertedCategoryName(String categoryName) {
		insertedCategoryName = categoryName+generateRandomNum(999);
		ADD_CATEGORY_ELEMENT.sendKeys(insertedCategoryName);
	}
}


