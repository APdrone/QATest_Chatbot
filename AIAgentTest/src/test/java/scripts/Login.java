package scripts;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import objectRepository.HomePage;
import objectRepository.PopupPage;



public class Login {
	WebDriver driver;
	WebDriverWait wait;
	HomePage home;
	PopupPage popup;
	String url="https://c6.avaamo.com/web_channels/444588bc-92fe-477f-87c1-88a92946346a/demo.html?theme=avm-messenger&banner=true&demo=true&banner_text=%20&banner_title=This%20is%20how%20the%20chat%20agent%20shows%20up";

	
	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("edge")  String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			 options.addArguments("incognito");
			 driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			 driver=new  EdgeDriver();
		}
		
		  wait=new WebDriverWait(driver,20);
		  home=new HomePage(driver);
		   popup=new PopupPage(driver);
		
	}
	
	@Test(priority = 0)

	public void welcomeScreen(){

		String actual=home.getStartMessage(url);
		
		Assert.assertEquals(actual, "Hello and welcome to IRA agent");
		
		home.waitForChatPopup();
		popup.waitForMainResponse();
		popup.enterText("Hello");
		
		String actualGreetingMsg=popup.waitForGreetingResponse();
		
		Assert.assertEquals(actualGreetingMsg, "Hi there, what can I help you with today?");

	}
	
	@Test(priority = 1)
	public void MenuOptions(){

		
	WebElement switchMenu=driver.findElement(By.xpath("//*[@aria-label='Switch to chat agent menu']"));
		
		
		switchMenu.click();
		
		WebElement startOverBtn=driver.findElement(By.cssSelector(".botMenu__menu a[aria-label*='Start']"));
		
		startOverBtn.click();

		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]//div[contains(text(),'Hi! Welcome to IFFCO-Tokio General Insurance. ')]"), 1));
		
		driver.findElement(By.cssSelector("div.attachment-message .default_card_link a[aria-label*='Download Moto']")).click();
		
		
		WebElement responsewait=driver.findElement(By.xpath("//div[starts-with(@id,'message-') and contains(@aria-label,'Download Motor Policy')]//following-sibling::div[contains(@class,'not-mine')]"));
		

		wait.until(ExpectedConditions.invisibilityOf(responsewait));
		
		WebElement response=
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[starts-with(@id,'message-') and contains(@aria-label,'Download Motor Policy')]//following-sibling::div[contains(@class,'not-mine')]//div[@class='default_card_link']/a"))));
				
				String actualLink=response.getAttribute("href");
				
				Assert.assertEquals(actualLink, "https://www.iffcotokio.co.in/portal/private-car/policy-download");
		
		
	}
	
	
	@Test(priority = 2)
	public void verifyFormSubmission(){
		
		WebElement inputField=driver.findElement(By.cssSelector("textarea#queryTextbox"));
		
		
		inputField.sendKeys("Test Bot");
		
		WebElement sendBtn=driver.findElement(By.xpath("//*[text()=\"Send\"]"));
		
		sendBtn.click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]//div[contains(text(),'Fill the below form')]"), 1));
		
		
		WebElement input=driver.findElement(By.cssSelector("input[id^='single']"));
		
		input.sendKeys("Roger Federer");
		
		WebElement address=driver.findElement(By.cssSelector("textarea[id^='data_capture']"));
		
		address.sendKeys("41/21 Rosewood Apartment New Jersey");
		
		WebElement maleRadioBtn=driver.findElement(By.xpath("//span[@aria-label='Male']"));
		
		maleRadioBtn.click();
		
		WebElement selectBox=driver.findElement(By.xpath("//input[contains(@id,'picklist')]"));
		
		selectBox.sendKeys("ver");
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".picklist li"), 1));
		

		driver.findElement(By.cssSelector(".picklist li")).click();
		
		driver.findElement(By.xpath("//input[@type='radio' and @value='5']//following::label[@aria-label='rating 5'] ")).click();
		
		WebElement submitBtn=driver.findElement(By.xpath("//button[@aria-label='Submit']"));
		
		submitBtn.click();
		
		wait.until(ExpectedConditions.textToBe(By.xpath("//button[@aria-label='Submit']"), "Submitted successfully"));
		
		
		String BtnText=submitBtn.getText();
		
		Assert.assertEquals(BtnText, "Submitted successfully");
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]//div[contains(text(),'Hi! Welcome to IFFCO-Tokio General Insurance. ')]"), 2));
		
		
		
		
	}
	
	
	@Test(priority = 3)
	public void verifyNewTest(){
		
		WebElement inputField=driver.findElement(By.cssSelector("textarea#queryTextbox"));
		
		
		inputField.sendKeys("New Test");
		
		WebElement sendBtn=driver.findElement(By.xpath("//*[text()=\"Send\"]"));
		
		sendBtn.click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]//div[contains(text(),'Test')]"), 1));
		

		
		
		
		WebElement googleLink=driver.findElement(By.xpath("//div[@class='default_card attachments']//div[@class='default_card_link']/a[text()='Google']"));
		
		googleLink.click();
		
		WebElement closeGooglePopup=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='close webview popup']")));
		
		closeGooglePopup.click();

		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//section[@id='messages-list']//child::div[contains(@class,'not-mine')]//div[contains(text(),'Test')]"), 1));

		
		WebElement callLink=driver.findElement(By.xpath("//div[@class='default_card attachments']//div[@class='default_card_link']/a[text()='Call']"));
		
		
		wait.until(ExpectedConditions.attributeContains(callLink, "href", "tel"));
	
		
		String callLinkRef=callLink.getAttribute("href");
		
		Assert.assertEquals(callLinkRef, "tel:1234567890");

	}
	
	@AfterClass()
	public void tearDown() {
		driver.close();
	}
	
	

}
