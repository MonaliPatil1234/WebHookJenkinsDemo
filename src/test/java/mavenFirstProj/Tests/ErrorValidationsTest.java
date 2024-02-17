package mavenFirstProj.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import mavenFirstProj.TestComponents.baseTest;
import mavenFirstProj.pageobjects.cartPage;
import mavenFirstProj.pageobjects.productCatalog;
@SuppressWarnings({ })
public class ErrorValidationsTest extends baseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";

		// login with email and password
	  landingpage.LoginApplication("monali.patil@rsa.com", "Asdf@1234*");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMsg());
	}
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=mavenFirstProj.TestComponents.Retry.class)
	public void productnameErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 33";
		 productCatalog pt =  landingpage.LoginApplication("monali.patil@rsa.com", "Asd@1234*");
		pt.addProductToCart(productName);
		// it will return null if no such product is found
		cartPage cartpage = pt.gotoCart();

		boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertFalse(match);
		// click on checkout button
		//checkoutPage checkoutpage = cartpage.gotoCheckout();
		//checkoutpage.selectCountry("india");
		//ConfirmationPage confirmpage = checkoutpage.submitOrder();

		// driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();
		//String confirmMessage = confirmpage.getConfirmationMsg();
		//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
