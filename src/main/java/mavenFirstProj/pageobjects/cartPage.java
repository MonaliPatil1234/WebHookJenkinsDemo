package mavenFirstProj.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import mavenFirstProj.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css = ".totalRow button")
	private WebElement checkoutElement;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream()
				.anyMatch(carProduct -> carProduct.getText().equalsIgnoreCase(productName));

		return match;

	}

	public checkoutPage gotoCheckout() {
		
		checkoutElement.click();
		return new checkoutPage(driver);
	}

}
