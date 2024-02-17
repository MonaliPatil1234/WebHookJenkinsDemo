package mavenFirstProj.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mavenFirstProj.pageobjects.OrderPage;
import mavenFirstProj.pageobjects.cartPage;

public class AbstractComponent {
	WebDriver driver;
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementsToAppear(List<WebElement> webEle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(webEle));
	}
	public void waitForWebElementToAppear(WebElement webEle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webEle));
		}

	public void waitForElementToDisappear(WebElement ele) {
		//here, thread.sleep(1000); can be used instead of below 2 lines as it'll speed up the //execution from clicking on Add to cart button onwards as it again waits to appear the same element and delays the execution of script
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public cartPage gotoCart()
	{
		cartBtn.click();
		cartPage cartpage = new cartPage(driver);
		return cartpage;

	}
	public OrderPage gotoOrdersPage()
	{
		orderHeader.click();
		OrderPage orderpageObj = new OrderPage(driver);
		return orderpageObj;

	}

	}


