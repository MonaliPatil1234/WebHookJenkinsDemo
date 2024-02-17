package mavenFirstProj.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mavenFirstProj.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {
	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[placeholder = 'Select Country']")
	private WebElement countryNameField;

	String countryName = "India";

	private By results = By.cssSelector(".ta-results");

	@FindBy(css = ".action__submit")
	private WebElement submit;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;

	public void selectCountry(String countryname) {
		Actions a = new Actions(driver);
		a.sendKeys(countryNameField, countryName).build().perform();

		waitForElementToAppear(results);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		jse.executeScript("arguments[0].click()", submit);
		// submit.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;

	}

	public void checkout(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(results));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,100)");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

		driver.findElement(By.cssSelector(".action__submit")).click();

	}

}
