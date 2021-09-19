package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class PopupPage extends BasePage {
	
	WebDriver driver;
	public PopupPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}



	
	@FindBy(xpath = "//button[@title='Close Menu']")
	WebElement closeMenu;
	
	@FindBy(css="textarea#queryTextbox")
	WebElement inputField;
	
	@FindBy(xpath ="//*[text()='Send']")
	WebElement sendBtn;
	
	@FindBy(xpath="//div[starts-with(@id,'message-') and contains(@aria-label,'Hello')]//following-sibling::div[contains(@class,'not-mine')]")
	WebElement responseGreeting;
	
	@FindBy(xpath ="//div[starts-with(@id,'message-') and contains(@aria-label,'Hello')]//following-sibling::div[contains(@class,'not-mine')]//div[@class='message-wrap']/p")
	WebElement responseGreetingMsg;

	
	By mainResponse=By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]");
	


	public void waitForMainResponse() {
		
		this.waitForNumberofElementsToBe(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]"), 1);
	}

	public void enterText(String val) {
		closeMenu.click();
		inputField.sendKeys(val);
		sendBtn.click();
	}

	public String waitForGreetingResponse() {
	//	this.waitForInvisibility(responseGreeting);
		this.waitForVisibility(responseGreetingMsg);
		

		
		return responseGreetingMsg.getText();
	}
	
	
	
	
	

}
