package mavenFirstProj.stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mavenFirstProj.TestComponents.baseTest;
import mavenFirstProj.pageobjects.ConfirmationPage;
import mavenFirstProj.pageobjects.LandingPage;
import mavenFirstProj.pageobjects.cartPage;
import mavenFirstProj.pageobjects.checkoutPage;
import mavenFirstProj.pageobjects.productCatalog;

public class stepDefinitionImpl extends baseTest {
	public LandingPage lp;
	productCatalog pt;
	ConfirmationPage confirmpage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_ecom_page() throws IOException {
		lp = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void loggedIn_with_uname_password(String username, String password) {
		pt = landingpage.LoginApplication(username, password);
	}

	@When("^I add product (.+) from cart$")
	public void I_add_Product_to_cart(String productname) {
		pt.addProductToCart(productname);
	}
	@When("^Checkout (.+) and submit order$")
	public void checkout_submit_order(String productname) {
		cartPage cartpage = pt.gotoCart();
		boolean match = cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		// click on checkout button
		checkoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.selectCountry("india");
		 confirmpage = checkoutpage.submitOrder();
	}
	@Then("{string} should be displayed on ConfirmationPage")
	public void msg_displayed_confirmationPage(String string) {
		String confirmMessage = confirmpage.getConfirmationMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message should be displayed.")
	public void error_message_displayed(String string1)
	{
		Assert.assertEquals(string1, landingpage.getErrorMsg());
		driver.close();
	}
}
