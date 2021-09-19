package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BasePage {
	
	protected WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,20);
		AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this);
		
	}
	
	
	public void open(String url) {
		driver.get(url);
	}
	
	public void waitForAttributeToBe(WebElement el,String attribute,String value) {
		
		wait.until(ExpectedConditions.attributeToBe(el, attribute, value));
	}
	
	public void waitForNumberofElementsToBe(By el,int value) {

		wait.until(ExpectedConditions.numberOfElementsToBe(el, value));
	}
	
	public void waitForInvisibility(WebElement el) {

		wait.until(ExpectedConditions.invisibilityOf(el));
	}
	
	public void waitForVisibility(WebElement el) {

		wait.until(ExpectedConditions.visibilityOf(el));
	}
	
	

}
