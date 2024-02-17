package mavenFirstProj.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mavenFirstProj.TestComponents.baseTest;
import mavenFirstProj.pageobjects.ConfirmationPage;
import mavenFirstProj.pageobjects.OrderPage;
import mavenFirstProj.pageobjects.cartPage;
import mavenFirstProj.pageobjects.checkoutPage;
import mavenFirstProj.pageobjects.productCatalog;

public class submitOrderTest extends baseTest {
	// String productName = "zara coat 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		// login with email and password
		productCatalog pt = landingpage.LoginApplication(input.get("email"), input.get("password"));

		pt.addProductToCart(input.get("productName"));
		// it will return null if no such product is found
		cartPage cartpage = pt.gotoCart();

		boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		// click on checkout button
		checkoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmpage = checkoutpage.submitOrder();

		// driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();
		String confirmMessage = confirmpage.getConfirmationMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// TO VERIFY THAT ZARA COAT 3 IS DISPLAYING ON ORDER PAGE
	@Test(dataProvider = "getData", groups = { "Purchase" }, dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest(HashMap<String, String> input) {
		productCatalog pt = landingpage.LoginApplication(input.get("email"), input.get("password"));
		OrderPage orderspage = pt.gotoOrdersPage();
		Assert.assertTrue(orderspage.verifyOrderDisplay(input.get("productName")));

	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		String filePath = "\\src\\test\\java\\mavenFirstProj\\data\\purchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}

//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "anshika@gmail.com");
//map.put("password","Iamking@000");
//map.put("productName","ZARA COAT 3");
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "monali.patil@rsa.com");
//map1.put("password","Asdf@1234*");
//map1.put("productName","ADIDAS ORIGINAL");
