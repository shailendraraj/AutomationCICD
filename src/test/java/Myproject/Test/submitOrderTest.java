package Myproject.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Myproject.Testcomponents.BaseTest;
import Myproject.pageobjects.CartPage;
import Myproject.pageobjects.Landingpage;
import Myproject.pageobjects.OrderPage;
import Myproject.pageobjects.ProductCatalogue;
import Myproject.pageobjects.checkOutPage;
import Myproject.pageobjects.conformationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class submitOrderTest extends BaseTest {
	String product_name = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap <String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		

		// Page Object Model
		// Landing Page

		ProductCatalogue productCatalogue = LandingPage.loginApplication(input.get("email"), input.get("password"));

		// Product Catalogue
		List<WebElement> products = productCatalogue.getproductlist();
		productCatalogue.addProductToCart(input.get("product_name"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product_name"));
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectcountry("India");

		conformationPage conformationPage = checkOutPage.submitOrder();
		String conformation_msg = conformationPage.getConformationMessage();

		Assert.assertTrue(conformation_msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = LandingPage.loginApplication("testdoe123@gmail.com", "Jhondoe@123");
		OrderPage orderpage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(product_name));
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png" ;
	}
	
	// extend reports--
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap <String,String> map = new HashMap<String,String>();
//		map.put("email", "testdoe123@gmail.com");
//		map.put("password", "Jhondoe@123");
//		map.put("product_name", "ZARA COAT 3");
		
//		HashMap <String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty1234@gmail.com");
//		map1.put("password", "Jha@1234");
//		map1.put("product_name", "ADIDAS ORIGINAL");
		
		List<HashMap<String , String >> data = getjsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//Myproject//data//PurchaseOrder.json");
		return new Object[] [] {{data.get(0)},{data.get(1)}};
		
		
	}

			
	// To Display "ZARA COAT 3" is display in order page

}
