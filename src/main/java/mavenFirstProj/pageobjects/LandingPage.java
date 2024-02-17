package mavenFirstProj.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenFirstProj.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement passwordEle;

	@FindBy(id = "login")
	private WebElement submit;
	// invalid email or password
	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMsg;

	// .ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
//class="ng-tns-c4-9 toast-message ng-star-inserted"
	public productCatalog LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		productCatalog pc = new productCatalog(driver);
		return pc;

	}

	public void goTo() {
		driver.get("https://www.rahulshettyacademy.com/client");

	}

	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
