package mavenFirstProj.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import mavenFirstProj.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css = ".totalRow button")
	private WebElement checkoutElement;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> ProductNames;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyOrderDisplay(String productName) {
		// waitForWebElementsToAppear(ProductNames);

		boolean match = ProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		System.out.println("List of products ordered is "+ProductNames);
System.out.println("Value of Match is "+match);
		return match;

	}

	public checkoutPage gotoCheckout() {

		checkoutElement.click();
		return new checkoutPage(driver);
	}

}
