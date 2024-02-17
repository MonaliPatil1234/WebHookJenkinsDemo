package mavenFirstProj.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import mavenFirstProj.pageobjects.LandingPage;

public class OrdersPageProductNameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		// login with email and password
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("monali.patil@rsa.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdf@1234*");
		// click on login button
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
		
		List<WebElement> ls = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
		 ls.stream().forEach(product -> System.out.println(product.getText()));
		 
		 boolean match = ls.stream()
					.anyMatch(product -> product.getText().equalsIgnoreCase("zara coat 3"));
					
		 System.out.println("Value of match is "+match);
		 // get all the products webelements into a list of web elements
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// convert the list to java streams and apply filter to get web element with
		// name zara coat 3
		//WebElement prod = products.stream()
			//	.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				//.orElse(null);
		// it will return null if no such product is found
		// now click on add to cart button of the same found web element
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

			
				

	}

}
