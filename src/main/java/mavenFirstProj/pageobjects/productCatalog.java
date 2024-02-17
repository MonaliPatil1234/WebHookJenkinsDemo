package mavenFirstProj.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mavenFirstProj.AbstractComponents.AbstractComponent;

public class productCatalog extends AbstractComponent {
	WebDriver driver;

	public productCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// PageFactory

	@FindBy(css = ".mb-3")
	private List<WebElement> products;

	private By productsBy = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastMsg = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	private WebElement spinner;

	public List<WebElement> getproductslist() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getproductslist().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;

	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);

	}

}
