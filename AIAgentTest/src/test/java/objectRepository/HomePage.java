package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css = "div.avaamo__icon")
	WebElement popupIcon;
	
	@FindBy(css="div#avaamo__popup")
	WebElement popupBody;
	
	@FindBy(css=".welcome-message")
	WebElement popupMessage;
	
	@FindBy(css=".get-started-link")
	WebElement getStartedBtn;
	

	public String getStartMessage(String url) {
		this.open(url);
		popupIcon.click();
		this.waitForAttributeToBe(popupBody, "class", "avaamo__popup animated avmFadeIn");
		return popupMessage.getText();
	}

	public void waitForChatPopup() {
		getStartedBtn.click();
		this.waitForAttributeToBe(popupBody, "class", "avaamo__popup animated avmFadeIn");
		driver.switchTo().frame("avaamoIframe");
	}

	
	
}
