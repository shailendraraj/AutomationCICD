package Myproject.Test;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import Myproject.Testcomponents.BaseTest;
import Myproject.Testcomponents.Retry;
import Myproject.pageobjects.CartPage;
import Myproject.pageobjects.ProductCatalogue;
import Myproject.pageobjects.checkOutPage;
import Myproject.pageobjects.conformationPage;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String product_name = "ZARA COAT 3";

		// Page Object Model
		// Landing Page

		LandingPage.loginApplication("testdoe1234@gmail.com", "Jhondoe@123");
		
		//div[@aria-label='Incorrect email or password.']
		
		Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());
	}
	
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String product_name = "ZARA COAT 3";

		// Page Object Model
		// Landing Page

		ProductCatalogue productCatalogue = LandingPage.loginApplication("testdoe123@gmail.com", "Jhondoe@123");

		// Product Catalogue
		List<WebElement> products = productCatalogue.getproductlist();
		productCatalogue.addProductToCart(product_name);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		

	}

}
